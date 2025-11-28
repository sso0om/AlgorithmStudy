package greedy.lv2;

/** 조이스틱 - 프로그래머스 42860
 *
 * 문제 유형 : Greedy
 */
public class Joystick {
    public int solution(String name) {
        int cnt = 0;
        int len = name.length();
        int move = len - 1; // 1. 좌우 이동의 최대치 (순방향으로 끝까지 가는 경우)

        // for문 : 꺽는 기준점 후보(각 인덱스) 전부 검사 (실제 이동 경로 시뮬레시션 X)
        for(int i = 0; i < len; i++) {
            char ch = name.charAt(i);

            // 상하 조작: 'A'에서 해당 문자까지의 최소 거리
            cnt += Math.min(ch - 'A', 'Z' - ch + 1);

            // 좌우 조작: 다음 'A'가 아닌 문자가 나올 때까지의 위치 확인
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // 2. 현재 위치까지 왔다가 다시 뒤로 돌아가서 왼쪽 끝으로 가기
            move = Math.min(move, i * 2 + len - next);

            // 3. 처음부터 뒤로 가서 왼쪽 끝을 먼저 처리하고 다시 현재로 오기
            move = Math.min(move, (len - next) * 2 + i);
        }

        // 상하 조작 + 좌우 조작
        return cnt + move;
    }
}
