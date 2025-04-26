package math.lv1;

/** 기사단원의 무기 - 프로그래머스 136798
 *
 * 문제 유형 : 약수
 */
public class DivisorsCnt {

    // 1. 약수의 배수 for문 (약수 누적)
    // 시간 복잡도 : 	O(number × log(number))
    // - 메모리 약간 더 씀, 훨씬 빠름
    private int solution1(int number, int limit, int power) {
        int[] divisorsCnt = new int[number + 1];

        // 1 ~ number
        for (int div = 1; div <= number; div++) {
            // n = div, 2div, 3div ..
            // n = 약수의 배수, n의 약수 cnt++
            for (int n = div; n <= number; n += div) {
                divisorsCnt[n]++;
            }
        }

        int total = 0;
        for (int i = 1; i < number; i++) {
            // 공격력 제한 수치 초과일 경우 power, 이하일 경우 약수 개수
            total += (divisorsCnt[i] > limit) ? power : divisorsCnt[i];
        }

        return total;
    }



    // 2. 각 약수의 개수 for문 (√N 탐색)
    // 시간 복잡도 : O(number × √number)
    // - 코드 간단, 느릴 수 있음
    private int solution2(int number, int limit, int power) {
        int total = 0;

        for (int i = 1; i <= number; i++) {
            int weapon = getDivisorCnt(number); // 공격력 수치 = 약수 개수

            // 공격력 제한 수치 초과일 경우 지정값, 이하일 경우 약수 개수
            total += (weapon > limit) ? power : weapon;
        }
        return total;
    }

    // 약수 개수
    private int getDivisorCnt(int number) {
        int cnt = 0;
        double sqrt = Math.sqrt(number);

        for (int i = 1; i <= sqrt; i++) {
            // 약수
            if (number % i == 0) {
                cnt++;

                // n == i * (n / i)
                if (number != sqrt) cnt++;
            }
        }

        return cnt;
    }
}
