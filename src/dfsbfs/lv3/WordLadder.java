package dfsbfs.lv3;

import java.util.ArrayDeque;
import java.util.Queue;

/** 단어 변환 - 프로그래머스 43163
 *
 * 문제 유형 : BFS, 레벨 단위(depth), 최단거리
 */
public class WordLadder {

    // 1. BFS + depth
    public int solution1(String begin, String target, String[] words) {
        Queue<String> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        q.offer(begin);
        int depth = 0; // 변환 횟수

        while (!q.isEmpty()) {
            int size = q.size(); // 현재 레벨 단어 갯수

            // 현재 레벨 탐색
            for (int i = 0; i < size; i++) {
                String cur = q.poll();

                // 목표 도착
                if (cur.equals(target)) return depth;

                // 다음 단어 탐색
                for (int j = 0; j < words.length; j++) {
                    if (visited[j]) continue;

                    // 한 글자만 다른지 확인
                    if (canConvert1(cur, words[j])) {
                        visited[j] = true;
                        q.offer(words[j]);
                    }
                }
            }

            depth++; // 레벨 증가 = 변환 횟수 증가
        }

        return 0; // 못 찾으면 0
    }

    // 한 글자만 다른지 체크
    private boolean canConvert1(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }

        return diff == 1;
    }



    // 2. BFS + Node
    public int solution2(String begin, String target, String[] words) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        q.offer(new Node(begin, 0)); // 시작 단어 + 거리 0

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 목표 도착
            if (cur.word.equals(target)) return cur.dist;

            // 다음 단어 탐색
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;

                if (canConvert2(cur.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Node(words[i], cur.dist + 1)); // 거리 +1
                }
            }
        }

        return 0;
    }

    static class Node {
        String word;
        int dist;

        Node(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    // 한 글자 차이 체크
    private boolean canConvert2(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }

        return diff == 1;
    }
}
