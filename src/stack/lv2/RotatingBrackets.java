package stack.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/** 괄호 회전하기 - 프로그래머스 76502
 *
 * 문제 유형 : 괄호 유효성 검사, 완전탐색 (스택), 회전 문자열
 */
public class RotatingBrackets {

    // 1. Stack + substring
    // 시간 복잡도 : O(n^2)
    // - 매 회전마다 문자열 새로 생성
    // - 시간 + 메모리 낭비
    public int solution1(String s) {
        int cnt = 0;
        int len = s.length();

        for(int i = 0; i < len; i++) {
            String rotateS = s.substring(i, len) + s.substring(0, i);

            if(isValid1(rotateS)) cnt++;
        }

        return cnt;
    }

    private boolean isValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        Map<Character, Character> pair = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
        );

        for(char ch : s.toCharArray()) {
            if(pair.containsKey(ch)) {
                if(stack.peek() != pair.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }



    // 2. Stack + offset - 추천
    // 시간 복잡도 : O(n^2)
    // 실제 실행 성능은 인덱스 방식이 더 좋음
    // 회전 문자열을 직접 만들지 않고, 회전된 문자열의 문자를 읽는 방식
    public int solution2(String s) {
        int n = s.length();
        int count = 0;

        // start: 회전 시작 인덱스 (0 ~ n-1)
        for (int start = 0; start < n; start++) {
            // 해당 회전이 올바른 괄호인지 검사
            if (isValid2(s, start)) {
                count++;
            }
        }

        return count;
    }

    private boolean isValid2(String s, int start) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();

        // 닫는 괄호 -> 대응되는 여는 괄호 매핑
        Map<Character, Character> pair = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
        );

        // offset: 회전된 문자열에서 몇 번째 문자인지
        for (int offset = 0; offset < n; offset++) {
            // 원형 인덱싱으로 회전된 문자열 읽기
            char ch = s.charAt((start + offset) % n);

            // 닫는 괄호인 경우
            if (pair.containsKey(ch)) {
                // 스택이 비어있거나, 짝이 안 맞으면 실패
                if (stack.isEmpty() || stack.peek() != pair.get(ch)) {
                    return false;
                }
                // 짝이 맞으면 pop
                stack.pop();
            } else {
                // 여는 괄호인 경우 → 스택에 push
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}
