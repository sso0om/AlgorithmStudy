package heap.lv2;

import java.util.PriorityQueue;

/** 더 맵게 - 프로그래머스 42626
 *
 * 문제 유형 : Min Heap, PriorityQueue
 */
public class Scoville {

    public int solution(int[] scoville, int K) {
        // 최소 힙 (가장 작은 값이 먼저 나옴)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;

        // 모든 스코빌 지수를 힙에 삽입
        for (int s : scoville) {
            pq.offer(s);
        }

        // 가장 작은 값이 K 이상이 될 때까지 반복
        // (최소 2개는 있어야 섞을 수 있음)
        while (pq.size() > 1 && pq.peek() < K) {
            int first = pq.poll();
            int second = pq.poll();

            // 최솟값 2 섞기
            int mixed = first + second * 2;

            // 크기 상관없이 다시 힙에 삽입
            pq.offer(mixed);
            count++;
        }

        // 최종적으로 가장 작은 값이 K 이상이면 성공
        // 아니면 모든 음식 K 이상으로 만드는 게 불가능
        return pq.peek() != null && pq.peek() >= K ? count : -1;
    }
}
