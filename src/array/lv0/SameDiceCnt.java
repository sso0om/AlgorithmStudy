package array.lv0;

import java.util.Arrays;

/** 주사위 게임 3 - 프로그래머스 181916
 *
 * 문제 유형 : sort, 주사위 게임
 */
public class SameDiceCnt {

    // 정렬을 이용한 풀이
    private int solution(int a, int b, int c, int d) {
        // 오름차순 정렬
        int[] dice = {a, b, c, d};
        Arrays.sort(dice);

        // 모두 같은 수 {7, 7, 7, 7}
        if (dice[0] == dice[3]) {
            return 1111 * dice[0];
        }

        // 같은 수 3개 {7, 7, 7, 9} || {7, 9, 9, 9}
        if (dice[0] == dice[2] || dice[1] == dice[3]) {
            int d3 = (dice[0] == dice[2]) ? dice[0] : dice[3]; // 같은 수
            int d1 = (dice[0] == dice[2]) ? dice[3] : dice[0]; // 다른 수
            return (int) Math.pow((10 * d3 + d1), 2);
        }

        // 같은 수 2개, 2개 {7, 7, 9, 9}
        if (dice[0] == dice[1] && dice[2] == dice[3]) {
            return (dice[0] + dice[2]) * Math.abs(dice[0] - dice[2]);
        }

        // 같은 수 2개, 각각 다른 수
        if (dice[0] == dice[1]) { // {7, 7, 8, 9}
            return dice[2] * dice[3];
        }
        if (dice[1] == dice[2]) { // {7, 8, 8, 9}
            return dice[0] * dice[3];
        }
        if (dice[2] == dice[3]) { // {7, 8, 9, 9}
            return dice[0] * dice[1];
        }

        // 모두 다른 수 {6, 7, 8, 9}
        return dice[0];
    }
}
