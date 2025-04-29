package sort.lv1;

import java.util.Arrays;

/** 문자열 내 마음대로 정렬하기
 *
 * 문제 유형 : sort, comparator
 */
public class SortStrArrByIdx {

    // 1. Arrays.sort(strings, comparator)
    // - String[] 직접 변경, 빠르고 간단하지만 원본 손상
    // 시간 복잡도 : O(n log n)
    //           - Arrays.sort() : TimSort 알고리즘(병합 정렬 + 삽입 정렬)
    //           - n : String[] length
    private String[] solution1(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) -> {
            // n 인덱스의 문자가 같을 시 오름차순
            if (s1.charAt(n) == s2.charAt(n)) {
                return s1.compareTo(s2);
            } else {
                // n 인덱스의 문자 기준 오름차순
                return Character.compare(s1.charAt(n), s2.charAt(n));
            }
        });
        return strings;
    }

    // 2. Arrays.stream(strings).sort(comparator)
    // - 원본 유지, 새로운 String[], 함수형 스타일
    // 시간 복잡도 : O(n log n)
    //           - Arrays.stream() : O(n)
    //           - sorted() : O(n log n)
    //           - toArray() : O(n)
    private String[] solution2(String[] strings, int n) {
        return Arrays.stream(strings)
                .sorted((s1, s2) -> {
                    // n 인덱스의 문자가 같을 시 오름차순
                    if (s1.charAt(n) == s2.charAt(n)) {
                        return s1.compareTo(s2);
                    } else {
                        // n 인덱스의 문자 기준 오름차순
                        return Character.compare(s1.charAt(n), s2.charAt(n));
                    }
                })
                .toArray(String[]::new);
    }
}
