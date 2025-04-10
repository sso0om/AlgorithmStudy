package math.lv0;

/** 삼각형의 완성 조건 (2)
 *
 * 문제 유형 : 삼각형 공식
 */
public class SideOfTriangle {

    // 1. 공식 이용
    private int solution1(int[] sides) { // 주어진 변 a, b
        int minSide = Math.min(sides[0], sides[1]); // a, b 중 가장 짧은 변
        return minSide * 2 - 1;
    }



    // 2. c가 가장 긴 변일 때 +  a 또는 b가 가장 긴 변일 때 - 1
    private int solution2(int[] sides) { // 주어진 변 a, b
        int maxSide = Math.max(sides[0], sides[1]); // a, b 중 가장 긴 변
        int minSide = Math.min(sides[0], sides[1]); // a, b 중 가장 짧은 변

        // c가 가장 긴 변일 때 : max(a, b) <= c < a + b
        int cnt = maxSide + minSide - maxSide;

        // a 또는 b가 가장 긴 변일 때 : c < max(a, b) < min(a,b) + c
        //                       => max(a,b) - min(a,b) < c < max(a,b)
        cnt += minSide - 1; // cnt += max(a,b) - (max(a,b) - min(a, b)) - 1(중복제거)
        return cnt;
    }
}
