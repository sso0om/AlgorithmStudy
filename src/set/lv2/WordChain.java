package set.lv2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 영어 끝말잇기 - 프로그래머스 12981
 *
 * 문제 유형 : 구현 + HashSet, 배열과 인덱스 계산 + 조건 체크
 */
public class WordChain {

    // 방법1. set
    public int[] solution1(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> used = new HashSet<>();
        char pre = 0;

        for(int i = 0; i < words.length; i++) {
            String word = words[i];

            if(i > 0 && word.charAt(0) != pre) {
                return new int[]{(i % n) + 1, i / n + 1};
            }
            if(!used.add(word)) {
                return new int[]{(i % n) + 1, i / n + 1};
            }
            pre = word.charAt(word.length() - 1);
        }
        return answer;
    }


    // 방법2. Map - 비추
    public int[] solution2(int n, String[] words) {
        int[] answer = new int[2];
        Map<String, Integer> map = new HashMap<>();
        char pre = words[0].charAt(0);

        for(int i = 0; i < words.length; i++) {
            String word = words[i];

            // 끝말잇기가 틀렸거나 이미 말했던 단어인 경우 탈락
            if(pre != word.charAt(0) || map.containsKey(word)) {
                answer[0] = i % n + 1; // 사람 번호
                answer[1] = i / n + 1; // 차례(턴)
                return answer;
            }
            map.put(word, i);
            pre = word.charAt(word.length() - 1);
        }
        return answer;
    }
}
