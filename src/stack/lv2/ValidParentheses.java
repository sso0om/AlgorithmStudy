package stack.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/** 올바른 괄호 - 프로그래머스 12909
 *
 * 문제 유형 : Stack, Deque, LinkedList, 카운터
 */
public class ValidParentheses {

    // 1. LinkedList - O(N)
    boolean solution1(String s) {
        Deque<Character> stack = new LinkedList<>();

        for(char ch : s.toCharArray()) {
            if (ch == ')') {
                if(stack.isEmpty()) return false;

                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }


    // 2. ArrayDeque - O(N)
    boolean solution2(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


    // 3. 카운터 (추천) - O(N)
    // 이 문제는 자료구조가 필요 없는 단순 카운터 문제
    boolean solution3(String s) {
        int cnt = 0;

        for(char ch : s.toCharArray()) {
            if(ch == ')') {
                if(cnt == 0) return false;
                cnt--;
            } else {
                cnt++;
            }
        }

        return cnt == 0;
    }
}
