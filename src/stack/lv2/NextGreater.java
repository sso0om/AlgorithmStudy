package stack.lv2;

import java.util.ArrayDeque;
import java.util.Deque;

/** 뒤에 있는 큰 수 찾기 - 프로그래머스 154539
 *
 * 문제 유형 : Stack, Deque
 */
public class NextGreater {

    // 1. 이중 for문 - 시간 초과
    // 시간 복잡도 : 최대 O(n²)
    // 각 원소마다 뒤를 끝까지 다시 탐색
    public int[] solution1(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];

        for(int i = 0; i < len; i++) {
            answer[i] = -1;

            for (int j = i + 1; j < len; j++) {
                if (numbers[j] > numbers[i]) {
                    answer[i] = numbers[j];
                    break;
                }
            }
        }

        return answer;
    }


    // 2. Stack - 추천
    // 시간 복잡도 : O(n)
    // 스택 top에 있는 값보다 크면, 그 top 원소의 "뒤에 있는 큰 수"가 지금 값으로 확정
    static class Num {
        int idx;
        int val;

        public Num(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public int[] solution2(int[] numbers) {
        int[] answer = new int[numbers.length];
        // stack  : 아직 뒤에 있는 큰 수를 못 찾은 원소
        Deque<Num> stack = new ArrayDeque<>();

        for (int i = 0; i < numbers.length; i++) {
            // 스택 top에 있는 값보다 크면, 해당 top 원소의 "뒤에 있는 큰 수" = 지금 값
            while (!stack.isEmpty() && stack.peek().val < numbers[i]) {
                Num num = stack.pop();
                answer[num.idx] = numbers[i];
            }
            stack.push(new Num(i, numbers[i]));
        }

        // "뒤에 있는 큰 수"가 없는 경우
        while (!stack.isEmpty()) {
            answer[stack.pop().idx] = -1;
        }

        return answer;
    }
}
