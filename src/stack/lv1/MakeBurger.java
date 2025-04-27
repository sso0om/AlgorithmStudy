package stack.lv1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** 햄버거 만들기
 *
 * 문제 유형 : int[] stack 처럼
 */
public class MakeBurger {

    // 1. int[] stack 처럼 - 특수한 경우(성능 최적화할 때만 사용)
    // 시간 복잡도 : O(n) - 최고 속도, 메모리 사용 최적화
    private int solution1(int[] ingredient) {
        int[] stack = new int[ingredient.length];
        int size = 0;
        int cnt = 0;

        for (int ig : ingredient) {
            stack[size++] = ig;

            // 햄버거 재료 연속으로 있을 경우
            if (size >= 4
                && stack[size - 4] == 1
                && stack[size - 3] == 2
                && stack[size - 2] == 3
                && stack[size - 1] == 1) {
                cnt++;     // 햄버거++
                size -= 4; // 햄버거 재료 이전 위치로 이동
            }
        }

        return cnt;
    }



    // 2. ArrayList
    // 시간 복잡도 : O(n) - 빠르고 가독성 좋음
    // add, remove 시 크기 조정 비용 있음
    private int solution2(int[] ingredient) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0;

        for (int ig : ingredient) {
            list.add(ig);

            // 햄버거 재료 연속으로 있을 경우
            int size = list.size();
            if (size >= 4
                && list.get(size - 4) == 1
                && list.get(size - 3) == 2
                && list.get(size - 2) == 3
                && list.get(size - 1) == 1) {

                // 햄버거 포장
                for (int i = 0; i < 4; i++) {
                    list.remove(list.size() - 1);
                }
                cnt++;     // 햄버거++
            }
        }

        return cnt;
    }



    // 3. Stack
    // 시간 복잡도 : O(n) - 느리고 무거워서 비추
    private int solution3(int[] ingredient) {
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;

        for (int ig : ingredient) {
            stack.push(ig);

            if (stack.size() >= 4) {
                int size = stack.size();

                // 햄버거 재료 연속으로 있을 경우
                if (stack.get(size - 4) == 1
                    && stack.get(size - 3) == 2
                    && stack.get(size - 2) == 3
                    && stack.get(size - 1) == 1) {

                    // 햄버거 포장
                    for (int i = 0; i < 4; i++) stack.pop();
                    cnt++; // 햄버거++
                }
            }
        }

        return cnt;
    }



    // 4. StringBuilder + contains
    // 시간 복잡도 : O(n^2) - 느려서 사용 불가
    private int solution4(int[] ingredient) {
        StringBuilder sb = new StringBuilder();
        String burger = "1231";
        int cnt = 0;

        // O(n^2)
        for (int ig : ingredient) {
            sb.append(ig);

            // toString : O(n), contains : O(n)
            if (ig == 1 && sb.toString().contains(burger)) {
                int len = sb.length();
                cnt++;
                sb.delete(len - burger.length(), len);
            }
        }

        return cnt;
    }
}
