package queue.lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 기능 개발 - 프로그래머스 42586
 *
 * 문제 유형 : Queue, 구현
 */
public class FeatureDeployment {

    // 1. Queue + while문
    // 시간 복잡도 : O(n)
    // 완료일 큐에 넣고 배포 단위로 poll
    public int[] solution1(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < progresses.length; i++) {
            queue.offer((100 - progresses[i] + speeds[i] - 1) / speeds[i]);
        }

        List<Integer> answer = new ArrayList<>();

        while(!queue.isEmpty()) {
            int releaseDay = queue.poll();
            int cnt = 1;

            // 배포 포함
            while(!queue.isEmpty() && releaseDay >= queue.peek()) {
                queue.poll();
                cnt++;
            }
            answer.add(cnt); // 배포
        }

        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }


    // 2. List + 단일 for문 - 추천
    // 시간 복잡도 : O(n)
    // 가독성 + 불필요한 자료구조 제거
    // 완료일 계산 후 바로 그룹화
    public int[] solution2(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        int releaseDay= 0;

        for(int i = 0; i < progresses.length; i++) {
            int day = (100 - progresses[i] + speeds[i] - 1) / speeds[i];

            if(releaseDay < day) {   // 그룹 시작할 때
                if(releaseDay > 0) {
                    list.add(cnt);   // 이전 그룹 배포 (초기 X, 이전 그룹 O)
                }
                releaseDay = day;    // 새로운 그룹 기준 설정
                cnt = 1;
            } else {
                cnt++;
            }
        }
        list.add(cnt); // 마지막 그룹 배포

        return list.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}
