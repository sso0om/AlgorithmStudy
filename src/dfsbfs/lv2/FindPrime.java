package dfsbfs.lv2;

import java.util.HashSet;
import java.util.Set;

/** 소수 찾기 - 프로그래머스 42839
 *
 * 문제 유형 : DFS, 순열, Set, Prime
 */
public class FindPrime {
    private Set<Integer> numbersSet = new HashSet<>(); // 중복 제거 위함
    private boolean[] visited; // 사용한 자리 체크

    // 1. DFS + Set
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];

        dfs(numbers, "");

        int cnt = 0;
        for(int number : numbersSet) {
            if(isPrime(number)) cnt++;
        }

        return cnt;
    }

    // numbers : 원본 숫자 문자열, current : 지금까지 만든 숫자
    private void dfs(String numbers, String current) {
        // 현재 숫자 저장
        // 하나라도 만들었으면 저장 -> 1자리 수 ~ n자리 수 모두 처리 가능
        if(!current.isEmpty()) {
            numbersSet.add(Integer.parseInt(current));
        }

        for(int i = 0; i < numbers.length(); i++) {
            if(visited[i]) continue; // 이미 쓴 숫자는 건너뜀

            // 선택
            visited[i] = true;  // 사용
            dfs(numbers, current + numbers.charAt(i)); // 현재 숫자에 하나 붙이고 탐색
            visited[i] = false; // 복구 - 다른 경우 탐색 위함
        }
    }

    private boolean isPrime(int num) {
        if(num == 1) return false;     // 1 : 소수 아님
        if(num == 2) return true;      // 2 : 유일한 짝수 소수
        if(num % 2 == 0) return false; // 3 이상 : 짝수는 전부 소수 아님

        for(int i = 3; i <= Math.sqrt(num); i += 2) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
