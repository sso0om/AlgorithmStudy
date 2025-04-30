package set.lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** 완주하지 못한 선수
 *
 * 문제 유형 : Hash, HashMap, Sort, 중복
 */
public class NonCompleteRun {

    // 1. Map<Man, Cnt>
    // 총 시간 복잡도 : O(n)
    // - 효율적 (Hash 기반), 대규모 입력에서도 안정적 작동
    private String solution1(String[] participant, String[] completion) {
        Map<String, Integer> manCnt = new HashMap<>();

        // 시간 복잡도 : O(n)
        for (String man : participant) {
            manCnt.put(man, manCnt.getOrDefault(man, 0) + 1);
        }

        // 시간 복잡도 : O(n-1)
        for (String man : completion) {
            if (manCnt.get(man) == 1) {
                manCnt.remove(man);
            } else {
                manCnt.put(man, manCnt.get(man) - 1);
            }
        }

        // 시간 복잡도 : O(1) - key 하나만 있음
        return manCnt.keySet().iterator().next();
    }



    // 2. 정렬 후 비교 Arrays.sort()
    // 시간 복잡도 : O(N log N)
    // - 코드 간단, 대량 데이터에서는 정렬이 부담, 해시맵 방식 보다 성능 떨어질 수 있음
    private String solution2(String[] participant, String[] completion) {
        // O(N log N)
        Arrays.sort(participant);
        Arrays.sort(completion);

        // O(N)
        for (int i = 0; i < completion.length; i++) {
            if (participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[participant.length - 1];
    }
}
