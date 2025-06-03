package base.lv0;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 숨어있는 숫자의 덧셈(2)
 *
 * 문제 유형 : Regex, Stream, 누적 숫자 방식
 */
public class SumOfStrNum {

    // 1. split(영문) + Stream
    public int solution1(String my_string) {
        return Arrays.stream(my_string.split("[A-Za-z]+"))
                .filter(e -> !e.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    // 2. Character.isDigit + 누적 숫자 방식
    //- 시간복잡도 : O(n), 공간복잡도 : O(1)
    //- 정규식 X, split() X, Integer.parseInt() 호출 X → 문자열, 배열 안 만듦, 덜 무거움
    //- 누적 숫자 방식 : `num = num * 10 + (ch - '0')`
    public int solution2(String my_string) {
        int sum = 0;
        int num = 0;

        for (char ch : my_string.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (num != 0) {
                sum += num;
                num = 0;
            }
        }
        sum += num;
        return sum;
    }
}
