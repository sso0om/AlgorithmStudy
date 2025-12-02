package dfsbfs.lv2;

/** 타겟 넘버 - 프로그래머스 43165
 *
 * 문제 유형 : DFS, 분기 DFS
 */
public class TargetNumber {

    int cnt = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return cnt;
    }

    private void dfs(int[] numbers, int target, int idx, int sum) {
        if(idx == numbers.length) { // 전체 계산 종료 시 target값인 경우 cnt++
            if(sum == target) cnt++;
            return;
        }

        dfs(numbers, target, idx + 1, sum + numbers[idx]); // + 선택
        dfs(numbers, target, idx + 1, sum - numbers[idx]); // - 선택
    }
}
