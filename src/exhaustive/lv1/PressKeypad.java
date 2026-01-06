package exhaustive.lv1;

/** 키패드 누르기 - 프로그래머스 67256
 *
 * 문제 유형 : 구현, 시뮬레이션
 */
public class PressKeypad {

    public String solution(int[] numbers, String hand) {
        // 0~9 키패드 좌표
        int[][] pos = {
            {3, 1},
            {0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1}, {2, 2},
        };

        // 왼손, 오른손 처음 시작 위치(*, #)
        int[] l = {3, 0};
        int[] r = {3, 2};

        StringBuilder sb = new StringBuilder();

        for(int num : numbers) {
            // 숫자의 좌표
            int[] numPos = pos[num];

            if(numPos[1] == 0) { // 왼쪽 열 1, 4, 7인 경우 왼손 처리
                l = numPos;
                sb.append("L");
            } else if(numPos[1] == 2) { // 오른쪽 열 3, 6, 9인 경우 오른손 처리
                r = numPos;
                sb.append("R");
            } else { // 가운데 열인 경우 가까운 손으로 처리. 같으면 hand 기준
                // 현재 숫자 위치와 왼손, 오른손 위치의 맨허튼 거리
                int dl = Math.abs(numPos[0] - l[0]) + Math.abs(numPos[1] - l[1]);
                int dr = Math.abs(numPos[0] - r[0]) + Math.abs(numPos[1] - r[1]);

                if(dl < dr || (dl == dr && hand.equals("left"))) {
                    l = numPos;
                    sb.append("L");
                } else {
                    r = numPos;
                    sb.append("R");
                }
            }
        }

        return sb.toString();
    }
}
