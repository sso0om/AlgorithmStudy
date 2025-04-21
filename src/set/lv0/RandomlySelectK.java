package set.lv0;

import java.util.*;

/** 무작위로 K개의 수 뽑기
 *
 * 문제 유형 : 순서 유지 + HashSet 탐색용, Arrays
 */
public class RandomlySelectK {

    // 1. Set 탐색용 + List 순서 유지
    private int[] solution1(int[] arr, int k) {
        Set<Integer> seen = new HashSet<>();    // 탐색용
        List<Integer> list = new ArrayList<>(); // 저장용

        for (int num : arr) {
            // set.contains - O(1) => O(n)
            if (!seen.contains(num)) {
                seen.add(num);
                list.add(num);
                
                // 길이가 k일 경우 종료
                if (list.size() == k) {
                    break;
                }
            }
        }
        int[] answer = new int[k];

        // 길이가 부족할 경우 -1 채운 배열 생성
        for (int i = 0; i < k; i++) {
            answer[i] = i < list.size() ? list.get(i) : -1;
        }
        return answer;
    }

    // 2. Arrays
    private int[] solution2(int[] arr, int k) {
        // 중복 제거, 개수 제한
        int[] distinct = Arrays.stream(arr)
                .distinct()
                .limit(k)
                .toArray();

        // 길이가 부족할 경우 -1로 채운 새 배열 생성
        if (distinct.length < k) {
            int[] result = new int[k];

            System.arraycopy(distinct, 0, result, 0, distinct.length);
            Arrays.fill(result, distinct.length, k, -1);

            return result;
        }

        return distinct;
    }
}
