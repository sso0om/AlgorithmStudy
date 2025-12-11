package heap.lv3;

import java.util.Comparator;
import java.util.PriorityQueue;

/** 디스크 컨트롤러 - 프로그래머스 42627
 *
 * 문제 유형 : Simulation, Heap, PriorityQueue, Comparator
 */
public class DiskController {

    // 1. 시뮬레이션 + 우선 순위 큐
    class Work {
        int no;    // 작업 번호
        int start; // 요청 시각
        int time;  // 소요 시간

        public Work(int no, int start, int time) {
            this.no = no;
            this.start = start;
            this.time = time;
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;

        // 아직 도착하지 않은 작업들
        // 요청 시각(start) 빠른 순으로 관리
        PriorityQueue<Work> jobsPq = new PriorityQueue<>(
            Comparator.comparingInt((Work w) -> w.start)
        );

        // 이미 도착해서 대기 중인 작업들
        // 소요 시간(time) 짧은 순 -> 요청 시각(start) 빠른 순 -> 번호(no) 작은 순
        PriorityQueue<Work> watingPq = new PriorityQueue<>(
            Comparator.comparingInt((Work w) -> w.time)
                .thenComparingInt(w -> w.start)
                .thenComparingInt(w -> w.no)
        );

        // jobsPq 세팅
        for(int i = 0; i < jobs.length; i++) {
            int[] job = jobs[i];
            jobsPq.offer(new Work(i, job[0], job[1]));
        }

        int sec = 0;          // 현재 시간
        int totalEndTime = 0; // 각 작업의 반환 시간 누적합

        // 아직 처리 안 한 작업이 하나라도 남아 있으면 계속 진행
        while(!jobsPq.isEmpty() || !watingPq.isEmpty()) {

            // 현재 시간(sec)까지 도착한 작업들을 모두 대기 큐로 이동
            while(!jobsPq.isEmpty() && jobsPq.peek().start <= sec) {
                watingPq.offer(jobsPq.poll());
            }

            // 대기 중인 작업이 있으면 우선순위 가장 높은 작업 1개만 처리
            if(!watingPq.isEmpty()) {
                Work cur = watingPq.poll();
                sec += cur.time;                 // 현재 작업 실행
                totalEndTime += sec - cur.start; // 반환 시간 = 완료 시각 - 요청 시각
            } else {
                // 현재 시간(sec)에 할 일이 없으면 시간을 다음 작업 요청 시각으로 점프
                sec = jobsPq.peek().start;
            }
        }

        // 평균 반환 시간
        return totalEndTime / jobs.length;
    }
}
