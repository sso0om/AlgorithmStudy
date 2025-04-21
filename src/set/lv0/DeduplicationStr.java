package set.lv0;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/** 중복된 문자 제거
 *
 * 문제 유형 : 중복 제거, 순서유지, HashSet 탐색용
 */
public class DeduplicationStr {

    // 1. Set 탐색용 + StringBuilder 순서 유지 - 가장 효율적
    // 시간 복잡도 :O(n)
    private String solution1(String my_string) {
        StringBuilder sb = new StringBuilder();
        Set<Character> seen = new HashSet<>();  // 탐색용

        for (char ch : my_string.toCharArray()) {
            if (!seen.contains(ch)) { // set.contains - O(1) => O(n)
                seen.add(ch);
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // 2. Set + Stream
    // 시간 복잡도 :O(n)
    private String solution2(String my_string) {
        Set<Character> seen = new HashSet<>();  // 탐색용

        return my_string.chars()                // String → IntStream (Unicode int)
                .mapToObj(c -> (char) c)        // IntStream → Stream<Character>
                .filter(seen::add)              // add() : 중복 시 false 반환 => 중복 제외 filter
                .map(String::valueOf)           // Stream<Character> → Stream<String>
                .collect(Collectors.joining()); // Stream<String> → String
    }
}
