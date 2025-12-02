package dfsbfs.lv2;

/** 피로도 - 프로그래머스 87946
 *
 * 문제 유형 : DFS, 순열, cnt
 */
public class Dungeons {

    // 1. DFS + 전역 answer 방식
    // 시간 복잡도 : O(N!)
    // 돌면서 answer 갱신
    int answer1 = 0;
    boolean[] visited1;

    public int solution1(int k, int[][] dungeons) {
        visited1 = new boolean[dungeons.length];
        dfs1(k, 0, dungeons);

        return answer1;
    }

    // dfs(현재 피로도, 방문 개수, 던전들)
    private void dfs1(int k, int cnt, int[][] dungeons) {
        // 현재까지 방문한 개수 기준으로 최대값 갱신
        answer1 = Math.max(answer1, cnt);

        // 모든 던전을 순회하면서 다음 방문 가능 여부 체크
        for(int i = 0; i < dungeons.length; i++) {
            if(visited1[i]) continue;

            // 현재 피로도가 최소 요구 피로도 이상이면 입장 가능
            if(k >= dungeons[i][0]) {
                visited1[i] = true;
                dfs1(k - dungeons[i][1], cnt + 1, dungeons); // 피로도 차감, 다음 던전
                visited1[i] = false; // 백트래킹 (다른 경우 탐색 위해 원복)
            }
        }
    }


    // 2. DFS + return 방식
    // 시간 복잡도 : O(N!)
    // 값을 계산해서 반환
    boolean[] visited2;

    public int solution2(int k, int[][] dungeons) {
        visited2 = new boolean[dungeons.length];

        return dfs2(k, dungeons);
    }

    // dfs(현재 피로도, 던전들)
    private int dfs2(int k, int[][] dungeons) {
        int max = 0;

        for(int i = 0; i < dungeons.length; i++) {
            if(visited2[i]) continue;
            if(k < dungeons[i][0]) continue; // 최소 요구 피로도 이하이면 입장 불가

            visited2[i] = true;
            // 현재 던전 1개 + 이후 최대 방문 수
            max = Math.max(max, 1 + dfs2(k - dungeons[i][1], dungeons));
            visited2[i] = false; // 백트래킹 (상태 복구)
        }

        // 현재 상태에서 가능한 최대 방문 개수 반환
        return max;
    }
}
