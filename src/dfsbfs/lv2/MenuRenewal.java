package dfsbfs.lv2;

import java.util.*;

/** 메뉴 리뉴얼 - 프로그래머스 72411
 *
 * 문제 유형 : 조합 DFS, 문자열 정렬, HashMap, counter
 */
public class MenuRenewal {

    // 1. 조합 DFS + Map

    Map<String, Integer> map; // 조합 문자열, 등장 횟수

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        // course 길이별로 반복
        for(int n : course) {
            map = new HashMap<>(); // 길이별로 map 초기화 (데이터 분리)

            // 모든 주문에 대해 조합 생성
            for(String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr); // 같은 조합을 동일하게 만들기 위해 정렬

                if(arr.length >= n) {
                    comb(arr, 0, n, ""); // 길이 n 조합 생성
                }
            }

            int max = 0;

            // 해당 길이 조합 중 최대 등장 횟수 찾기
            for(int cnt : map.values()) {
                max = Math.max(max, cnt);
            }

            // 2번 이상 등장한 조합만 후보
            if(max < 2) continue;

            // 최대값과 동일한 조합만 결과에 추가
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                if(entry.getValue() == max) {
                    answer.add(entry.getKey());
                }
            }
        }

        // 최종 결과 사전순 정렬
        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    // 조합 생성 (DFS)
    private void comb(char[] arr, int start, int n, String str) {
        // course 길이 도달 시 map 추가
        if(str.length() == n) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }

        for(int i = start; i < arr.length; i++) {
            comb(arr, i + 1, n, str + arr[i]);
        }
    }
}
