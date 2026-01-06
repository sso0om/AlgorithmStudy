package exhaustive.lv2;

/** 프렌즈4블록 - 프로그래머스 17679
 *
 * 문제 유형 : 완전탐색, 구현, 시뮬레이션, 이차원 배열
 */
public class Friends4Block {

    // 오른쪽, 대각선(오른쪽 아래), 아래
    int[] dr = {0, 1, 1};
    int[] dc = {1, 1, 0};

    public int solution(int m, int n, String[] board) {
        int cnt = 0;
        char[][] map = new char[m][n];

        for(int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            int removeCnt = removeBlock(m, n, map);

            if(removeCnt == 0) break;

            dropBlock(m, n, map);

            cnt += removeCnt;
        }
        return cnt;
    }

    // 제거할 4블록 찾기
    private boolean[][] getRemoveBoard(char[][] map) {
        int m = map.length;
        int n = map[0].length;

        boolean[][] removeBoard = new boolean[m][n];

        // 2*2블록이므로 행-1, 열-1 범위까지만 탐색
        for(int r = 0; r < m - 1; r++) {
            for(int c = 0; c < n - 1; c++) {
                // 빈칸이 아닌 경우만 탐색
                if(map[r][c] == '.') continue;

                boolean isFour = true;

                // 현재 위치 기준 '오른쪽, 대각선(오른쪽 아래), 아래'가 같은 블록인지 탐색
                for(int i = 0; i < dr.length; i++) {
                    int curR = r + dr[i];
                    int curC = c + dc[i];

                    if(map[curR][curC] != map[r][c]) {
                        isFour = false;
                        break;
                    }
                }

                // 2*2블록이 모두 같은 블록일 경우 removeBoard에 표시
                if(isFour) {
                    removeBoard[r][c] = true;

                    for(int i = 0; i < dr.length; i++) {
                        int curR = r + dr[i];
                        int curC = c + dc[i];

                        removeBoard[curR][curC] = true;
                    }
                }
            }
        }
        return removeBoard;
    }

    // 블록 제거하기 ('.' => 빈칸)
    private int removeBlock(int m, int n, char[][] map) {
        boolean[][] removeBoard = getRemoveBoard(map);
        int removeCnt = 0;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(removeBoard[r][c]) {
                    removeCnt++;
                    map[r][c] = '.';
                }
            }
        }
        return removeCnt;
    }

    // 블록 아래로 떨어뜨리기 (빈칸 없애기)
    private void dropBlock(int m, int n, char[][] map) {
        // 컬럼별 하단부터 탐색
        for(int c = 0; c < n; c++) {
            // 쌓는 행
            int writeRow = m - 1;

            for(int r = m - 1; r >= 0; r--) {
                // 빈칸이 아니라면 writeRow에 쌓기, 현재 행 빈칸으로 처리
                if(map[r][c] != '.') {
                    map[writeRow][c] = map[r][c];

                    if(writeRow != r) {
                        map[r][c] = '.';
                    }
                    writeRow--;
                }
            }
        }
    }
}
