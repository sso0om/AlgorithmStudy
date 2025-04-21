package set.lv0;

import java.util.*;
import java.util.stream.Collectors;

/** 배열의 원소 삭제하기 - 프로그래머스 181844
 *
 * 문제 유형 : 순서 유지, Arrays filter, HashSet 탐색용
 */
public class DeleteArrayElement {

    // 1. deleteSet + Arrays.stream.filter
    // HashSet : 빠른 포함 여부 확인(contains) = 효율적인 필터링 성능
    // Arrays.stream.filter : 순서 유지 - arr 순서대로 순회
    // 시간 복잡도 : O(n + m)
    private int[] solution1(int[] arr, int[] delete_list) {
        Set<Integer> deleteSet = Arrays.stream(delete_list)
                .boxed()
                .collect(Collectors.toSet());

        return Arrays.stream(arr)
                .filter(num -> !deleteSet.contains(num)) // set.contains - O(1) => O(n)
                .toArray();
    }



    // 2. deleteSet + for문
    // 시간 복잡도 : O(n + m)
    private int[] solution2(int[] arr, int[] delete_list) {
        Set<Integer> deleteSet = Arrays.stream(delete_list)
                .boxed()
                .collect(Collectors.toSet());

        // arr 순회, delete_list 에 없는 값만 추가
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (!deleteSet.contains(num)) {  // set.contains - O(1) => O(n)
                list.add(num);
            }
        }

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }



    // 3. for문 + List.remove
    // 시간 복잡도 : O(n x m)
    private int[] solution9(int[] arr, int[] delete_list) {
        List<Integer> numList = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList());

        for (int delete_num : delete_list) {
            numList.remove(Integer.valueOf(delete_num)); // list.remove - O(n) => O(n x m)
        }
        return numList.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
