package sort.lv1;

import java.util.Arrays;

/** K번째수 - 프로그래머스 42748
 *
 * 문제 유형 : 정렬, Arrays
 */
public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i = 0; i < commands.length; i++) {
            // i ~ j 번째만 포함한 배열
            int[] subArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(subArr);
            // k 번째 수
            answer[i] = subArr[commands[i][2] - 1];
        }

        return answer;
    }
}
