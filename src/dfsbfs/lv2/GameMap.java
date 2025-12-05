package dfsbfs.lv2;

import java.util.ArrayDeque;
import java.util.Queue;

/** 게임 맵 최단거리 - 프로그래머스 1844
 *
 * 문제 유형 : BFS, 최단거리 BFS
 */
public class GameMap {

    public int solution(int[][] maps) {
        int m = maps.length;
        int n = maps[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};

        q.offer(new int[]{0, 0, 1}); // 시작 위치
        visited[0][0] = true;

        while(!q.isEmpty()) {
            // 현재 위치
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            // m, n 위치에 도착 시 이동거리 리턴
            if(x == m - 1 && y == n - 1) return dist;

            // 현재 위치 기준 사방향 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 밖, 벽, 이미 방문한 경우 제외
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(maps[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;               // 방문 체크
                q.offer(new int[]{nx, ny, dist + 1}); // 다음 위치와 누적 거리 저장
            }
        }

        // 도착할 수 없는 경우 -1 리턴
        return -1;
    }
}
