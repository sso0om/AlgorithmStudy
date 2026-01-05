package exhaustive.lv1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 신고 결과 받기 - 프로그래머스 92334
 *
 * 문제 유형 : 구현, Hash, Map, Set
 */
public class ReportMail {

    public int[] solution(String[] id_list, String[] report, int k) {
        // Map<사람, 인덱스>
        Map<String, Integer> idxMap = new HashMap<>();

        for(int i = 0; i < id_list.length; i++) {
            idxMap.put(id_list[i], i);
        }

        // Map<신고당한사람, 신고자들>
        Map<String, Set<String>> reportMap = new HashMap<>();

        for(String r : report) {
            String[] parts = r.split(" ");
            reportMap.computeIfAbsent(parts[1], key -> new HashSet<>()).add(parts[0]);
        }

        int[] answer = new int[id_list.length];

        // k번 이상 신고된 사람
        for(Set<String> reporters : reportMap.values()) {
            if(reporters.size() >= k) {
                // 신고자들의 처리 결과 메일 개수 + 1
                for(String reporter : reporters) {
                    answer[idxMap.get(reporter)]++;
                }
            }
        }

        return answer;
    }
}
