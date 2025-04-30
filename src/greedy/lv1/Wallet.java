package greedy.lv1;

/** 최소 직사각형 - 프로그래머스 86491
 *
 * 문제 유형 : Greedy, Max
 */
public class Wallet {

    private int solution(int[][] sizes) {
        int maxX = 0;
        int maxY = 0;

        for (int[] size : sizes) {
            int x = Math.max(size[0], size[1]);
            int y = Math.min(size[0], size[1]);

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        return maxX * maxY;
    }
}
