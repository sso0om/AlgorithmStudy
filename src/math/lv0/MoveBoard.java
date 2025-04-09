package math.lv0;

/** 캐릭터의 좌표 - 프로그래머스 120861
 *
 * 문제 유형 : 좌표 이동
 */
public class MoveBoard {

    private int[] solution(String[] keyinput, int[] board) {
        int x = 0, y = 0; // 시작 위치
        int xLimit = board[0] / 2; // board 가로 크기 제한
        int yLimit = board[1] / 2; // board 세로 크기 제한

        for (String key : keyinput) {
            switch (key) {
                case "up" : if(y < yLimit) y++; break;    // (0, 1) 이동. 가로+ 크기 제한
                case "down" : if(y > -yLimit) y--; break; // (0, -1) 이동. 가로- 크기 제한
                case "left" : if(x > -xLimit) x--; break; // (-1, 0) 이동. 세로- 크기 제한
                case "right" : if(x < xLimit) x++; break; // (1, 0) 이동. 세로+ 크기 제한
            }
        }
        return new int[] {x, y}; // 현 위치
    }
}
