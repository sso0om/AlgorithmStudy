package map.lv1;

import java.util.Arrays;
import java.util.Map;

/** 공원 산책 - 프로그래머스 161990
 *
 * 문제 유형 : 좌표 이동, 장애물 검사, Map<Direct, int[]>
 */
public class WalkPark {

    public static void main(String[] args) {
        WalkPark w = new WalkPark();

        String[] a = {"SOO","OOO","OOO"};
        String[] b = {"E 2","S 2","W 1"};
        int[] arr = w.solution(a, b);
        System.out.println(Arrays.toString(arr));
    }

    // 1. map<Direct, int[]> 방향 처리 + for(1 ~ dist) 직진 경로 검사
    private int[] solution(String[] park, String[] routes) {
        int[] pos = new int[2];
        int h = park.length;
        int w = park[0].length();

        // 방향 처리
        Map<String, int[]> directs = Map.of(
                "N", new int[]{-1, 0},
                "S", new int[]{1, 0},
                "W", new int[]{0, -1},
                "E", new int[]{0, 1}
        );

        // 시작점 위치 지정
        outer: // 라벨
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 시작점 발견 시 시작점 세팅 및 이중 for문 탈출
                if (park[i].charAt(j) == 'S') {
                    pos[0] = i;
                    pos[1] = j;
                    break outer;
                }
            }
        }

        // 길 탐색 및 이동
        for (String route :routes) {
            // 이동할 방향, 위치
            String[] nextDir = route.split(" ");
            int[] direct = directs.get(nextDir[0]);
            int dist = Integer.parseInt(nextDir[1]);
            boolean canMove = true;

            // 현 위치 + 1 부터 이동 거리 만큼 직진 경로 확인
            for (int i = 1; i <= dist; i++) {
                // 이동 위치
                int nextRow = pos[0] + direct[0] * i;
                int nextCol = pos[1] + direct[1] * i;

                // 범위에서 벗어나거나 장애물이 있는 경우 false
                if (nextRow < 0 || h <= nextRow || nextCol < 0 || w <= nextCol
                        || park[nextRow].charAt(nextCol) == 'X') {
                    canMove = false;
                    break;
                }
            }
            
            // 범위 내 장애물이 없을 시 이동
            if (canMove) {
                pos[0] += direct[0] * dist;
                pos[1] += direct[1] * dist;
            }
        }
        return pos;
    }





    // 2. switch 방향 처리 + 장애물 사각형 범위 전체 검사 - 비추
    private int[] solution2(String[] park, String[] routes) {
        int[] pos = new int[2];

        // 시작 위치 지정
        for (int i = 0; i < park.length; i++) {
            if (park[i].contains("S")) {
                pos[0] = i;
                pos[1] = park[i].indexOf("S");
                break;
            }
        }

        // 길 탐색
        for (String route : routes) {
            String[] direct = route.split(" ");

            // 현재 위치
            int row = pos[0];
            int col = pos[1];

            // 방향 처리
            switch (direct[0]) {
                case "N" : row -= Integer.parseInt(direct[1]); break;
                case "S" : row += Integer.parseInt(direct[1]); break;
                case "W" : col -= Integer.parseInt(direct[1]); break;
                case "E" : col += Integer.parseInt(direct[1]); break;
            }

            // 범위내, 가는 길에 방해물이 없는 경우 이동
            if (!isOutOfRange(park, row, col) && !isBlock(pos, row, col, park)) {
                pos[0] = row;
                pos[1] = col;
            }
        }

        return pos;
    }

    // 범위 밖인지 검사
    private boolean isOutOfRange(String[] park, int row, int col) {
        return row < 0 || park.length <= row
                || col < 0 || park[0].length() <= col;
    }

    // 장애물 여부 검사 - 사각형 범위 전체 탐색 => 이동거리 외에도 검사
    private boolean isBlock(int[] pos, int row, int col, String[] park) {
        for (int i = Math.min(pos[0], row); i <= Math.max(pos[0], row); i++) {
            for (int j = Math.min(pos[1], col); j <= Math.max(pos[1], col); j++) {
                if (park[i].charAt(j) == 'X') return true;
            }
        }
        return false;
    }
}
