package array.lv0;

/** 정수를 나선형으로 배치하기 - 프로그래머스 181832
 *
 * 문제 유형 : 나선형 순회, 좌표 이동, 방문여부
 */
public class SpiralMatrix {

    // 1. 방향 배열 + 조건 전환
    // - 방향 배열로 미리 정의, 간결하고 깔끔한 방식
    private int[][] solution1(int n) {
        int[][] spiralArray = new int[n][n];

        // 방향 → ↓ ← ↑
        int[] dRow = {0, 1, 0, -1};
        int[] dColumn = {1, 0, -1, 0};

        // 시계 방향으로 (0, 0) 부터 1로 시작
        int row = 0, column = 0;
        int num = 1, direction = 0;

        // 1 부터 n * n 까지 세팅
        while (num <= n * n) {
            spiralArray[row][column] = num++;

            // 현재 방향으로 한 칸 이동
            int posRow = row + dRow[direction];
            int posColumn = column + dColumn[direction];

            // 방향 전환 필요 시
            if (shouldTurn(n, posRow, posColumn, spiralArray)) {
                // 다음 방향으로 전환
                direction = (direction + 1) % 4;

                // 바뀐 방향으로 한 칸 이동
                posRow = row + dRow[direction];
                posColumn = column + dColumn[direction];
            }
            // 최종 이동 위치 지정
            row = posRow;
            column = posColumn;
        }
        return spiralArray ;
    }

    // 방향 전환 필요 여부 : n * n 범위를 벗어나거나, 이미 값이 존재할 경우 true
    private boolean shouldTurn(int n, int posRow, int posColumn, int[][] arr) {
        return posRow < 0 || n <= posRow || posColumn < 0 || n <= posColumn || arr[posRow][posColumn] != 0;
    }





    // 2. 경계 축소
    // - 방향마다 경계 줄여가며 탐색. 방향 명확히 구분. 규칙적 반복
    // - 조건문 많고, 방향 순환 반복되어 데이터 흐름 복잡
    private int[][] solution2(int n) {
        int[][] spiralArray = new int[n][n];

        // 방향 → ↓ ← ↑
        int[] dRow = {0, 1, 0, -1};
        int[] dColumn = {1, 0, -1, 0};

        // 시계 방향으로 (0, 0) 부터 1로 시작
        int row = 0, column = 0;
        int num = 1, direction = 0;

        // 1 부터 n * n 까지 세팅
        while (num <= n * n) {
            spiralArray[row][column] = num++;

            // 현재 방향으로 한 칸 이동
            int posRow = row + dRow[direction];
            int posColumn = column + dColumn[direction];

            // 방향 전환 필요 시
            if (shouldTurn(n, posRow, posColumn, spiralArray)) {
                // 다음 방향으로 전환
                direction = (direction + 1) % 4;

                // 바뀐 방향으로 한 칸 이동
                posRow = row + dRow[direction];
                posColumn = column + dColumn[direction];
            }
            // 최종 이동 위치 지정
            row = posRow;
            column = posColumn;
        }
        return spiralArray;
    }





    // 3. 방문 체크 배열
    // - 방향 전환 조건 더 명확하게 함
    private int[][] solution3(int n) {
        int[][] spiralArray = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        // 방향 → ↓ ← ↑
        int[] dRow = {0, 1, 0, -1};
        int[] dColumn = {1, 0, -1, 0};

        // 시계 방향으로 (0, 0) 부터 시작
        int row = 0, column = 0;
        int direction = 0;

        // 1 부터 n * n 까지 세팅
        for (int num = 1; num < n * n; num++) {
            spiralArray[row][column] = num;
            visited[row][column] = true; // 방문 체크

            // 현재 방향으로 한 칸 이동
            int posRow = row + dRow[direction];
            int posColumn = column + dColumn[direction];

            // 방향 전환 필요 시
            if (shouldTurn2(n, posRow, posColumn, visited)) {
                // 다음 방향으로 전환
                direction = (direction + 1) % 4;

                // 바뀐 방향으로 한 칸 이동
                posRow = row + dRow[direction];
                posColumn = column + dColumn[direction];
            }
            // 최종 이동 위치 지정
            row = posRow;
            column = posColumn;
        }
        return spiralArray ;
    }

    // 방향 전환 필요 여부 : n * n 범위를 벗어나거나, 방문한 전 있으면 true
    private boolean shouldTurn2(int n, int posRow, int posColumn, boolean[][] visited) {
        return posRow < 0 || n <= posRow || posColumn < 0 || n <= posColumn || visited[posRow][posColumn];
    }
}
