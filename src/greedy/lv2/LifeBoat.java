package greedy.lv2;

import java.util.Arrays;

/** 구명보트 - 프로그래머스 42885
 *
 * 문제 유형 : Greedy, two pointer
 */
public class LifeBoat {

    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int begin = 0;
        int end = people.length - 1;
        int cnt = 0;

        // 두 포인터가 교차할 때까지 반복
        while(begin <= end) {
            // 가장 가벼운 + 가장 무거운 사람이 같이 탈 수 있는 경우
            if (people[begin] + people[end] <= limit) {
                begin++;
            }

            end--; // 같이 탔든 혼자 탔든 무거운 사람은 무조건 처리
            cnt++; // 보트는 항상 1대 사용
        }

        return cnt;
    }
}
