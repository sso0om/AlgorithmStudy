package array.lv0;

/** 이차원 배열 대각선 순회하기 - 프로그래머스 181829
 *
 * 문제 유형 : 대각선 순회, 좌표 이동
 */
public class DiagonalTraverse {

    // 2차원 배열 대각선 순회하기
    private int solution(int[][] board, int k) {
        int rowLen = board.length;
        int colLen = board[0].length;
        int sum = 0;

        // 대각선은 인덱스의 합이 동일함 : i + j == d
        // 대각선 (0, 0) ↘ (rowLen - 1, colLen - 1) 순회
        for (int d = 0; d <= k; d++) {
            int row = d < rowLen ? d : rowLen - 1;
            int col = d - row;

            // 대각선 내부 (1, 0) ↗ (0, 1) 순회
            while (0 <= row && col < colLen) {
                sum += board[row][col];
                row--;
                col++;
            }
        }
        return sum;
    }
}
