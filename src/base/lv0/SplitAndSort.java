package base.lv0;

import java.util.Arrays;

/** 문자열 잘라서 정렬하기
 *
 * 문제 유형 : sort, 숫자 문자열
 */
public class SplitAndSort {

    // 1. Stream filter
    // 가장 안전한 방법
    public String[] solution1(String myString) {
        return Arrays.stream(myString.split("x"))
                .filter(e -> !e.isEmpty())
                .sorted()
                .toArray(String[]::new);
    }



    // 2. split("[x]+") + 첫 빈 문자열 제거
    public String[] solution2(String myString) {
        String[] answer = myString.split("x+"); // 단일|연속되는 x문자 기준 배열 생성

        if(answer[0].isEmpty()) { // 문자열이 x로 시작하는 경우 공백 요소 -> 제거 필요
            answer = Arrays.copyOfRange(answer, 1, answer.length);
        }
        Arrays.sort(answer);
        return answer;
    }



    // 3. replaceAll("^x", "").split("x+")
    // - replaceAll("^x", "") : 문자열이 x로 시작하는 경우 공백으로 변환(제거)
    private String[] solution3(String myString) {
        String[] arr = myString.replaceAll("^x", "").split("x+"); // 첫 x 공백 요소 방지
        Arrays.sort(arr);
        return arr;
    }
}
