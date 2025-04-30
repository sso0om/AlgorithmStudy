package set.lv1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** 폰켓몬 - 프로그래머스 1845
 *
 * 문제 유형 : Hash, Set, IntStream, 중복 제거
 */
public class Pokemon {

    // 1. Set + for
    // 시간 복잡도 : O(N)
    private int solution1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        return Math.min(set.size(), nums.length / 2);
    }


    // 2. Set + IntStream...collect(toSet())
    // 시간 복잡도 : O(N)
    private int solution2(int[] nums) {
        Set<Integer> set = IntStream.of(nums)
                .boxed()                      // int -> Integer
                .collect(Collectors.toSet()); // IntStream -> Set

        return Math.min(set.size(), nums.length / 2);
    }


    // 3. IntStream.of().distinct()
    // 시간 복잡도 : O(N)
    private int solution3(int[] nums) {
        int typeCnt = (int) IntStream.of(nums)
                .distinct()
                .count();

        return Math.min(typeCnt, nums.length / 2);
    }

}
