package set.lv0;

import java.util.*;

/** 배열 유사도
 *
 * 문제 유형 : 중복 개수, HashSet, list.retainAll
 */
public class SimilarityOfArrays {

    // 1. HashSet
    // 시간 복잡도 : O(m)
    // - 하나의 입력이 '고정'이고 다른 하나만 '변하는 값'일 경우 O(n)으로 단순화 가능
    // - 정확한 복잡도 표기 : O(n + m)
    private int solution1(String[] s1, String[] s2) {
        Set<String> set = new HashSet<>(Arrays.asList(s1)); // 시간 복잡도 : O(n)
        int cnt = 0;

        // 시간 복잡도 : O(m)
        for (String str : s2) {
            if (set.contains(str)) { // 시간 복잡도 : set.contains() - O(1)
                cnt++;
            }
        }
        return cnt;
    }



    // 2. list.retainAll(list2)
    // 시간 복잡도 : O(n^2)
    private int solution2(String[] s1, String[] s2) {
        List<String> list = new ArrayList<>(Arrays.asList(s1)); // s1 - 가변 리스트 변환

        // retainAll 내부 contains() 사용
        // - list.contains 시간 복잡도 : O(n)
        // => retainAll 시간 복잡도 : O(n^2)
        list.retainAll(Arrays.asList(s2)); // s1, s2의 교집합만 남김

        return list.size(); // 교집합(중복)의 개수 리턴
    }
    
    

    // 3. 이중 for문
    // 시간 복잡도 : O(n^2) → 배열 길이가 커질수록 비효율적
    private int solution3(String[] s1, String[] s2) {
        int cnt = 0;

        for (String str : s1) {
            for (String str2 : s2) {
                if (str.equals(str2)) cnt++;
            }
        }
        return cnt;
    }
}
