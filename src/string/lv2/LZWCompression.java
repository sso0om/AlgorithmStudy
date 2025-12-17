package string.lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 압축 - 프로그래머스 17684
 *
 * 문제 유형 : String, Map, 구현, 시뮬레이션, idx 계산
 */
public class LZWCompression {

    public int[] solution(String msg) {
        // 사전 Map<단어, 색인 번호>
        Map<String, Integer> idxMap = new HashMap<>();

        // A-Z 세팅
        for(int i = 0; i < 26; i++) {
            idxMap.put("" + (char)(i + 'A'), i + 1);
        }

        List<Integer> answer = new ArrayList<>();
        int len = msg.length();
        int idx = 0;
        int dic = 27; // Z 다음 색인 번호

        while(idx < len) {
            String w = "" + msg.charAt(idx);
            int nextIdx = idx + 1;

            // 사전에 존재하는 가장 긴 문자열 찾기
            while(nextIdx <= len) {
                String sub = msg.substring(idx, nextIdx);

                if(!idxMap.containsKey(sub)) break;

                w = sub;
                nextIdx++;
            }
            // 가장 긴 글자의 색인 번호 추가
            answer.add(idxMap.get(w));

            // (w + 다음 문자) 사전에 등록
            if(nextIdx <= len) {
                idxMap.put(msg.substring(idx, nextIdx), dic++);
            }

            // 처리한 문자열 길이만큼 이동
            idx += w.length();
        }

        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}
