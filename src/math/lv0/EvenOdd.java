package math.lv0;

/** 홀짝에 따라 다른 값 반환하기
 * 문제 유형 : Math, 홀수의 합 공식, 짝수의 합 공식
 */
public class EvenOdd {

    public int solution(int n) {
        return n % 2 == 0 ? sumEvenPow1(n) : sumOdd1(n);
    }

    // 1. 직접 계산
    private int sumOdd1(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }

    private int sumEvenPow1(int n) {
        int sum = 0;
        for(int i = 2; i <= n; i += 2) {
            sum += i * i;
        }
        return sum;
    }


    // 2. 수학 공식
    // - 홀수의 합 공식 : S = n^2
    private int sumOdd(int n) {
        int cnt = (n + 1) / 2; // 홀수 개수
        return cnt * cnt; // cnt 개 홀수 합
    }

    // 짝수의 합 공식 : S = n/2 * (첫번째짝수+마지막짝수)
    // 짝수의 제곱의 합 공식 : S = 2 * n(n + 1)(2n + 1)/3
    private int sumEvenPow(int n) {
        int cnt = n / 2; // 짝수 개수
        return 2 * cnt * (cnt + 1) * (2 * cnt + 1) / 3; // cnt 개 짝수의 제곱 합
    }
}
