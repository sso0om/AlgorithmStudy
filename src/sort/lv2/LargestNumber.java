package sort.lv2;

import java.util.Arrays;
import java.util.stream.Collectors;

/** 가장 큰 수 - 프로그래머스 42746
 *
 * 문제 유형 : 문자열, 정렬
 */
public class LargestNumber {

    public String solution1(int[] numbers) {
        // 1. 문자열로 변환
        String[] strs = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);

        // 2. 커스텀 Comparator - 문자 조합 내림차순
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

        // 3. 0만 있는 경우 처리
        if (strs[0].equals("0")) return "0";

        // 4. 합치기
        return String.join("", strs);
    }

    public String solution2(int[] numbers) {
        String result = Arrays.stream(numbers)
            .mapToObj(String::valueOf)                  // 문자열 변환
            .sorted((a, b) -> (b + a).compareTo(a + b)) // 문자 조합 내림차순 정렬
            .collect(Collectors.joining());             // 문자 합침

        return result.charAt(0) == '0' ? "0" : result;  // 0만 있는 경우 0 처리 및 결과 반환
    }
}
