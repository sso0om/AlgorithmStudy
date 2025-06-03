package array.lv0;

import java.util.Arrays;
import java.util.stream.IntStream;

/** 수열과 구간 쿼리2
 *
 * 문제 유형 : Array, min, IntStream
 */
public class SequenceAndQueries {

    // 1. for-loop
    //- 가독성 좋고 디버깅 쉬움
    public int[] solution1(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int min = Integer.MAX_VALUE;

            for (int j = query[0]; j <= query[1]; j++) { // 유효 범위 s ≤ i ≤ e
                if (query[2] < arr[j]) {
                    min = Math.min(min, arr[j]); // k 보다 큰 최소값
                }
            }
            if (min == Integer.MAX_VALUE) { // 존재하지 않을 경우
                min = -1;
            }
            answer[i] = min;
        }
        return answer;
    }



    // 2. Stream
    public int[] solution2(int[] arr, int[][] queries) {
        return Arrays.stream(queries)                                             // 각 요소 int[]
                .mapToInt(query -> IntStream.rangeClosed(query[0], query[1]) // 각 요소 int
                        .map(i -> arr[i])              // arr배열 값
                        .filter(num -> query[2] < num) // k 보다 큰 수
                        .min()
                        .orElse(-1)).toArray();
    }
}
