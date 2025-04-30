package stack.lv1;

import java.util.*;
import java.util.stream.IntStream;

/** 같은 숫자는 싫어 - 프로그래머스
 *
 * 문제 유형 : stack 형태, 중복 제거, 순서 유지 (ArrayList, IntStream, Deque)
 */
public class ExceptSameNum {

    // 1. list
    // - 비동기, 가장 직관적이고 가독성 좋음. 불필요한 자료구조 없이 int preNum 하나로 비교
    // 내부적 배열 기반 = 삽입, 삭제 빠름, 순차적 데이터 처리 적합
    // 시간 복잡도 : O(n)
    private int[] solution1(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int preNum = -1;

        // O(n)
        for (int num : arr) {
            // 이전 값과 다를 시 add
            if (preNum != num) {
                list.add(num); // O(1)
                preNum = num;
            }
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }





    // 2. IntStream
    // - 간결하고 함수형 코드
    // - Stream 내부적으로 박싱, 언박싱, 인라인 배열 복사 비용
    // 시간 복잡도 : O(n)
    private int[] solution2(int[] arr) {
        return IntStream.range(0, arr.length)               // 정수 스트림 생성 : O(n)
                .filter(i -> i == 0 || arr[i] != arr[i -1]) // 중복 제거 조건 검사 : O(n)
                .map(i -> arr[i])                           // 인덱스 -> 원소 : O(n)
                .toArray();                                 // 스트림 -> 배열 : O(n)
    }





    // 3. Deque
    // - stack 보다 빠르고 비동기화. ArrayDeque 배열 기반이라 LinkedList 보다 훨씬 빠름
    // - 이 문제에서 Deque는 오버스펙임
    // 시간 복잡도 : O(n)
    private int[] solution3(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int num : arr) {
            // 이전 값과 다를 시 add
            if (deque.isEmpty() || deque.peekLast() != num) {
                deque.addLast(num);
            }
        }

        return deque.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }





    // 4. stack
    // - 내부적 vector 기반, 동기화 적용 = 성능 느릴 수 있음. pop 거꾸로 빼야 하므로 불필요한 작업
    // 시간 복잡도 : O(n), 실제로는 살짝 비효율
    private int[] solution4(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            if (!stack.isEmpty() && stack.peek() == num) {
                continue;
            }
            stack.push(num);
        }

        return stack.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
