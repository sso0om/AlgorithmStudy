package exhaustive.lv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 개인정보 수집 유효기간 - 프로그래머스 150370
 *
 * 문제 유형 : 구현, String parsing, Date
 */
public class ExpirationPeriod {

    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();

        for(String term : terms) {
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }

        int baseDays = toTotalDay(today);

        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int days = toTotalDay(privacy[0]);
            days += (termMap.get(privacy[1]) * 28) - 1;

            if(baseDays > days) {
                answer.add(i + 1);
            }
        }

        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }

    private int toTotalDay(String dayStr) {
        String[] arr = dayStr.split("\\.");

        return Integer.parseInt(arr[0]) * 12 * 28  // 연 * 12 * 28
            + Integer.parseInt(arr[1]) * 28        // 월 * 28
            + Integer.parseInt(arr[2]);            // 일
    }
}
