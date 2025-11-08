package map.lv2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 뉴스 클러스터링 - 프로그래머스 17677
 *
 * 문제 유형 : 문자열 처리, 해시(Map), 집합 연산, 구현(시뮬레이션)
 */
public class Clustering {

    public int solution(String str1, String str2) {
        // Map<원소, 개수>
        Map<String, Integer> map1 = getStrMap(str1);
        Map<String, Integer> map2 = getStrMap(str2);

        // 모든 원소 set
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        int kyo = 0;
        int union = 0;
        int base = 65536;

        // 교집합
        for(String key : allKeys) {
            int cnt1 = map1. getOrDefault(key, 0);
            int cnt2 = map2. getOrDefault(key, 0);

            kyo += Math.min(cnt1, cnt2);   // 교집합: min >= 0 (둘 중 하나라도 없으면 0)
            union += Math.max(cnt1, cnt2); // 합집합: max >= 1
        }
        // 모두 공집합일 경우: 65536
        if(union == 0) return base;

        // 자카드 = 교집합 / 합집합 * 65536
        double jacard = (double) kyo / union;
        return (int)(jacard * base);
    }

    // n-gram 멀티셋
    private Map<String, Integer> getStrMap(String str) {
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2).toLowerCase();
            if(sub.matches("[a-z]{2}")) { // 소문자 2글자
                map.put(sub, map.getOrDefault(sub, 0) + 1);
            }
        }
        return map;
    }
}
