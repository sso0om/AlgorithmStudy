package binarysearch.lv3;

import java.util.Arrays;

/** 입국심사 - 프로그래머스 43238
 *
 * 문제 유형 : 이분탐색
 */
public class Immigration {

    // 1. 이분탐색
    public long solution(int n, int[] times) {
        // left : 1
        // right : 가장 느린 사람이 모두 맡았을 때 = 가장 심사 오래 걸리는 시간
        long left = 1;
        long right = (long) Arrays.stream(times).max().orElse(0) * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int t : times) {
                cnt += mid / t;
                if (cnt >= n) break; // 오버플로우 + 불필요 계산 방지
            }

            // mid 시간 동안 처리 가능한 사람 수가 n 보다 클 경우
            if (cnt >= n) {
                answer = mid;      // 가능 → 답 후보 저장
                right = mid - 1;   // 더 줄이기
            } else {
                left = mid + 1;    // mid로는 n명 못 채움 → 시간 부족 → 시간 늘림
            }
        }

        return answer;
    }
}
