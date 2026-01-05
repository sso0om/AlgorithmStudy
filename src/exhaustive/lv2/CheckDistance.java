package exhaustive.lv2;

/** 거리두기 확인하기 - 프로그래머스 81302
 *
 * 문제 유형 : 완전탐색(브루트 포스), 구현, 이차원 배열
 */
public class CheckDistance {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int t = 0; t < places.length; t++) {
            String[] place = places[t];
            answer[t] = check(place) ? 1 : 0;
        }

        return answer;
    }

    private boolean check(String[] place) {

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                // P 기준 1~3 위치 검사
                if (place[r].charAt(c) != 'P') continue;

                // 1. 상하좌우 (거리 1)
                int[] dr1 = {-1, 1, 0, 0};
                int[] dc1 = {0, 0, -1, 1};

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr1[i];
                    int nc = c + dc1[i];

                    // 직선 거리 1 위치에 P가 있을 경우 실패
                    if (inRange(nr, nc) && place[nr].charAt(nc) == 'P') {
                        return false;
                    }
                }

                // 2. 직선 거리 2
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr1[i] * 2;
                    int nc = c + dc1[i] * 2;

                    // 직선 거리 2 위치에 P가 있고, 1 위치에 파티션이 아닌 경우 실패
                    if (inRange(nr, nc) && place[nr].charAt(nc) == 'P') {
                        int mr = r + dr1[i];
                        int mc = c + dc1[i];

                        if (place[mr].charAt(mc) != 'X') {
                            return false;
                        }
                    }
                }

                // 3. 대각선
                int[] dr2 = {-1, -1, 1, 1};
                int[] dc2 = {-1, 1, -1, 1};

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr2[i];
                    int nc = c + dc2[i];

                    // 대각선 위치에 P가 있고, 하나라도 파티션이 아닌 경우 실패
                    if (inRange(nr, nc) && place[nr].charAt(nc) == 'P') {
                        if (place[r].charAt(nc) != 'X' || place[nr].charAt(c) != 'X') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}
