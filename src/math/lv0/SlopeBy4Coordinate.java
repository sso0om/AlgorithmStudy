package math.lv0;

/** 평행
 *
 * 문제 유형 : 기울기
 */
public class SlopeBy4Coordinate {

    private int solution(int[][] dots) {
        // 조합 1. (0-1), (2-3)
        // 조합 2. (0-2), (1-3)
        // 조합 3. (0-3), (1-2)
        return isParallel(dots[0], dots[1], dots[2], dots[3]) ||
                isParallel(dots[0], dots[2], dots[1], dots[3]) ||
                isParallel(dots[1], dots[2], dots[0], dots[3]) ? 1 : 0;
    }

    // 기울기 비율 동일한지 확인
    private boolean isParallel(int[] a, int[] b, int[] c, int[] d) {
        // (y2 - y1) / (x2 - x1) == (y4 - y3) / (x4 - x3) -> 0 나누기 문제, 부동소수점 문제 발생 가능
        // => (y2 - y1) * (x4 - x3) == (y4 - y3) * (x2 - x1)
        return (b[1] - a[1]) * (d[0] - c[0]) == (d[1] - c[1]) * (b[0] - a[0]);
    }
}
