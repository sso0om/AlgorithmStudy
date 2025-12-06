package dfsbfs.lv3;

import java.util.ArrayDeque;
import java.util.Queue;

/** 네트워크 - 프로그래머스 43162
 *
 * 문제 유형 : DFS, Graph, BFS, 인접 행렬
 */
public class Network {

    // 1. DFS
    boolean[] visited1;

    public int solution(int n, int[][] computers) {
        visited1 = new boolean[n];
        int cnt = 0;

        // 모든 컴퓨터를 확인
        for(int i = 0; i < n; i++) {
            if(visited1[i]) continue; // 이미 어떤 네트워크에 포함된 컴퓨터

            dfs(i, computers); // 연결된 애들 전부 방문
            cnt++;             // 네트워크 1개
        }

        return cnt;
    }

    private void dfs(int computer, int[][] computers) {
        visited1[computer] = true;

        // computer 행을 따라 열 확인
        for(int i = 0; i < computers.length; i++) {
            if (computer == i) continue;                 // 자기 자신 제외
            if (computers[computer][i] == 0) continue;   // 연결 안 됨
            if (visited1[i]) continue;                    // 이미 방문

            dfs(i, computers); // 방문 안 했고 연결 됐다면 탐색
        }
    }


    // 2. BFS
    public int solution2(int n, int[][] computers) {
        boolean[] visited2 = new boolean[n];
        int networkCount = 0;

        for (int i = 0; i < n; i++) {
            if (visited2[i]) continue;

            bfs(i, computers, visited2);
            networkCount++;
        }

        return networkCount;
    }

    private void bfs(int start, int[][] computers, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next = 0; next < computers.length; next++) {
                if (current == next) continue;
                if (computers[current][next] == 0) continue;
                if (visited[next]) continue;

                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
