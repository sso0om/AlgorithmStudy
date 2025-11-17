package stack.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/** 주식 가격 - 프로그래머스 42584
 *
 * 문제 유형 : Stack, Deque, 단조 스택
 */
public class StockPrice {

    // 1. 단순 이중 for
    // 시간 복잡도 :  O(n²)
    // 입력 크기가 커지면 느려짐
    public int[] solution1(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i = 0; i < prices.length; i++) {
            int prev = prices[i];
            int cnt = 0;

            for(int j = i + 1; j < prices.length; j++) {
                cnt++;
                if(prev > prices[j]) break;
            }
            answer[i] = cnt;
        }
        return answer;
    }


    // 2. Stack - 추천
    // 시간 복잡도 :  O(n)
    // 스택에는 아직 가격이 떨어지지 않은 인덱스들이 들어 있음
    public int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>(); // 주식 안 떨어진 인덱스 stack

        for(int i = 0; i < prices.length; i++) {
            // 주식 가격 하락 시
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i); // 현재 인덱스 추가
        }

        // 끝까지 가격이 떨어지지 않은 경우 처리
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - 1 - idx;
        }

        return answer;
    }
}
