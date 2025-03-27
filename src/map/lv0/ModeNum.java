package map.lv0;

import java.util.HashMap;
import java.util.Map;

/** 최빈값 구하기
 *
 * 문제 유형 : 최빈값, Map(num, cnt)
 */
public class ModeNum {

    // Map(num, cnt) 사용
    private int solutionByMap(int[] array) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        int maxCnt = 0, maxNum = -1;

        for (int num : array) {
            // 개수 : (val 혹은 기본값) + 1
            int cnt = cntMap.getOrDefault(num, 0) + 1;

            // 최빈값 보다 클 경우 숫자를 최대값으로 지정
            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxNum = num;
            } else if (cnt == maxCnt) {
                maxNum = -1; // 최빈값 여러개 일 경우 -1
            }
            cntMap.put(num, cnt); // 값 설정
        }
        return maxNum;
    }
}
