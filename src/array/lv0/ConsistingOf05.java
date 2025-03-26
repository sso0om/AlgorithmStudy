package array.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 배열 만들기2 - 프로그래머스 181921
 *
 * 문제 유형 : 2진수, 0n으로 이루어진 수
 */
public class ConsistingOf05 {

    // 방법 1. char[] 변환 후  ‘0’, ‘5’ 체크
    private int[] solution1(int l, int r) {
        List<Integer> list = new ArrayList<>();

        // 1. 범위 팀섹
        for (int num = l; num <= r; num++) {
            // 2. 0과 5로 이루어진 경우 add
            if (isValid(num)) {
                list.add(num);
            }
        }
        // 3-1. 없을 시 -1 반환
        if (list.isEmpty()) {
            list.add(-1);
        }
        // 3-2. 0과 5로 이루어진 수 반환
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    // 방법 2. 이진수 변환을 활용한 0과 5로 이루어진 수
    private boolean isValid(int num) {
        String strNum = String.valueOf(num);

        // 1. char[] 변환 후 모든 자릿수의 숫자 확인
        for (char ch : strNum.toCharArray()) {
            // 2. 각 자릿수의 숫자가 0, 5가 아닌 경우 false
            if (ch != 0 && ch != 5) {
                return false;
            }
        }
        // 3. 0과 5로만 이루어진 수 true
        return true;
    }

    // 2진법 활용
    private int[] solution2(int l, int r) {
        List<Integer> list = new ArrayList<>();

        // 1. 범위에 맞는 2진수 범위 1 ~ 63 -> 1(2) ~ 111111(2)
        for (int i = 1; i < 64; i++) {
            // 2. 0과 1로 이루어진 2진수로 전환
            int num = Integer.parseInt(Integer.toBinaryString(i));

            // 3. 2진수 * 5 : 0과 5로 이루어진 수
            num *= 5;

            // 4. 범위에 해당하는 0과 5로 이루어진 수
            if (l <= num && num <= r) {
                list.add(num);
            }
        }
        // 5-1. 없을 시 -1 반환
        if (list.isEmpty()) {
            return new int[] {-1};
        }
        // 5-2. 0과 5로 이루어진 수 반환
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
