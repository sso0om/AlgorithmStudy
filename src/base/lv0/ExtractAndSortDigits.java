package base.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 문자열 정렬하기(1) - 프로그래머스 120850
 *
 * 문제 유형 : sort, 숫자 문자열
 */
public class ExtractAndSortDigits {

    // 1. chars() - 추천
    // - 문자열 바로 int 스트림 처리 => 중간 배열/객체 없어 메모리 낭비 줄어듦
    // 정규식 X, 박싱/언박싱 X, => 성능 향상
    private int[] solution1(String my_string) {
        return my_string.chars()            // Unicode 코드값(int) stream ex) a -> 97
                .filter(Character::isDigit) // 숫자 범위 필터링 [0-9]
                .map(ch -> ch - '0')    // 유니코드 -> 숫자 ex) '1' -> 1
                .sorted()
                .toArray();
    }



    // 2. replaceAll(영문, “”).split(”) + for문
    public int[] solution2(String my_string) {
        String[] arr = my_string.replaceAll("[a-z]+", "").split("");

        int[] answer = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            answer[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(answer);

        return answer;
    }



    // 3. replaceAll(영문, “”).split(”) + stream
    // - 전체적으로 불필요한 객체 생성이 많음
    // - replaceAll : 정규식 처리 비용, 상대적 느림
    // - split("") : 배열이 불필요하게 커짐, 성능 낮음
    // - 박싱/언박싱 및 예외 위험
    public int[] solution3(String my_string) {
        String[] arr = my_string.replaceAll("[a-z]+", "").split(""); // 영문 -> "" => 숫자만

        return Arrays.stream(arr)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }




    // 4. split(””) + stream.. matches("\\d")
    // - 전체적으로 불필요한 객체 생성이 많음
    // - split("") : 배열이 불필요하게 커짐, 성능 낮음
    // - matches("\\d") : 내부 정규식 처리
    // - 박싱/언박싱 비용
    public int[] solution4(String my_string) {
        return Arrays.stream(my_string.split("")) // 한 문자 배열
                .filter(e -> e.matches("\\d"))    // 숫자만
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }
}
