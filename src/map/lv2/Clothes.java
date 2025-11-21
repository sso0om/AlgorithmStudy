package map.lv2;

import java.util.HashMap;
import java.util.Map;

/** 의상 - 프로그래머스 42578
 *
 * 문제 유형 : 해시, Map, 카테고리, 곱하기
 */
public class Clothes {

    // 그룹별 개수 세기 (Map)
    // (k+1) 곱하기
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();

        for(String[] cloth: clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }

        for(int cnt : map.values()) {
            answer *= (cnt + 1); // 반드시 하나 이상 착용
        }

        return answer - 1; // 아무것도 안 입는 경우 제거
    }
}
