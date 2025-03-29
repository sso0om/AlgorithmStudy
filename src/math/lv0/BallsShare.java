package math.lv0;

/** 구슬을 나누는 경우의 수 - 프로그래머스 120840
 *
 * 문제 유형 : 조합, 팩토리얼
 */
public class BallsShare {

    public static void main(String[] args) {
        BallsShare b = new BallsShare();
        System.out.println(b.solution(3, 2));
        System.out.println(b.solution(5, 3));
    }

    private long solution(int balls, int share) {
        return combinationBySymmetry(balls, share);
    }

    // 조합  = n! / (r! * (n-r)!)
    //      = 범위[n ~ (n-r) ~ 1] / (r! * ((n-r) ~ 1))
    //      = 범위[n ~ (n-r)] / r!
    // 방법 1. 최적화 방식 - n ~ (n-r) / r!
    private long combinationBySymmetry(int n, int r) {
        // 1Cr = 1, nCn = 1
        if (n == 1 || n == r) {
            return 1;
        }

        // 연산 줄이기 위해 대칭성 활용 : C(n, r) == C(n, n-r)
        r = Math.min(r, n - r);

        long result = 1;
        // n ~ (n-r) / r!
        for (int i = 0; i < r; i++) {
            result *= (n - i); // n*(n-1)*(n-2)*...*(n-r+1)
            result /= (i + 1); // 1*2*3*...*r
        }
        return result;
    }



    // 방법 2. 팩토리얼 방식 - 오버플로우 발생(해당 문제 통과X)
    private long combinationByFactorial(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private long factorial(int n) {
        // 0! = 1, 1! = 1
        if (n == 0 || n == 1) {
            return 1;
        }
        // 재귀 n * (n - 1)!
        return n * factorial(n - 1);
    }
}
