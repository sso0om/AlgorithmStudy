package math.lv2;

/** 카펫 - 프로그래머스 77885
 *
 * 문제 유형 : 수학적 규칙 + 이진수 패턴 문제
 */
public class Bits2 {

    // 1. 비트 연산 없는 풀이
    // 짝수 규칙 : +1 하면 비트 1개만 바뀜
    // 홀수 규칙 : **오른쪽에서 처음 나오는 0을 1로 바꾸고, 바로 오른쪽 1을 0으로 변경
    public long[] solution1(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            long number = numbers[i];

            // 짝수 ---
            if(number % 2 == 0) {
                answer[i] = number + 1; // 짝수n + 1 은 항상 1비트 큰 수
                continue;
            }

            // 홀수 ---
            // 10진수 -> 2진수 char 배열로 변경
            char[] bits = ("0" + Long.toBinaryString(number)).toCharArray();

            // 오른쪽에서 처음 나오는 01 -> 10 으로 변경
            for(int j = bits.length - 1; j > 0; j--) {
                if(bits[j] == '1' && bits[j - 1] == '0') {
                    bits[j] = '0';
                    bits[j - 1] = '1';
                    break;
                }
            }
            // 2진수 char 배열 -> 2진수 long
            answer[i] = Long.parseLong(new String(bits), 2);
        }
        return answer;
    }


    // 방법2. 비트 연산 - GPT 추천 답압
    // 홀수 공식 : f(x) = x + 1 + ((x ^ (x + 1)) >> 2)
    public long[] solution2(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            long number = numbers[i];

            if(number % 2 == 0) {
                answer[i] = number + 1;
            } else {
                answer[i] = number + 1 + ((number ^ (number + 1)) >> 2);
            }
        }
        return answer;
    }
}
