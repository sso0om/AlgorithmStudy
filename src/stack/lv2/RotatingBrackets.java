package stack.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/** 괄호 회전하기 - 프로그래머스 76502
 *
 * 문제 유형 : 괄호 유효성 검사, 완전탐색 (스택), 회전 문자열
 */
public class RotatingBrackets {

    // stack + 원형 배열 공식
    public int solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // 닫는 괄호 기준 페어
        Map<Character, Character> pair = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
        );
        int len = s.length();
        int cnt = 0;

        // 한 칸씩 회전
        for(int i = 0; i < len; i++) {
            boolean valid = true;

            // 회전한 문자열 체크
            for (int j = 0; j < len; j++) {
                // 원형 배열 공식 (start + offset) % len
                char ch = s.charAt((i + j) % len);

                // 여는 괄호 스택에 푸시
                if(!pair.containsKey(ch)) {
                    stack.push(ch);
                    continue;
                }

                // 닫는 괄호 - 여는 괄호가 없거나 페어가 아닐 때 => 유효하지 않은 문자열
                if(stack.isEmpty() || stack.pop() != pair.get(ch)) {
                    valid = false;
                    break;
                }
            }

            // 올바른 괄호 문자열인 경우 카운트
            if(valid && stack.isEmpty()) {
                cnt++;
            }
        }
        return cnt;
    }
}
