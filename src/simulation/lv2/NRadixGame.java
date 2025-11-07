package simulation.lv2;

/** n진수 게임 - 프로그래머스 17687
 *
 * 문제 유형 : 문자열 시뮬레이션, 인덱스 계산 문제
 */
public class NRadixGame {

    // 방법1. 전체 문자열 생성 후 처리
    // 문자열로 펼쳐서 단순화한 버전
    public String solution1(int n, int t, int m, int p) {
        StringBuilder game = new StringBuilder();
        int number = 0;

        // n진수 문자열 생성
        while(game.length() < m * t) {
            // n진수 변환 및 A~F 처리
            String cur = Integer.toString(number++, n).toUpperCase();
            game.append(cur);
        }

        StringBuilder answer = new StringBuilder();
        int turn = 0;

        // p번째 사람 인덱스 계산
        for(int i = p - 1; i < game.length(); i += m) {
            if(turn == t) break;
            answer.append(game.charAt(i));
            turn++;
        }

        return answer.toString();
    }


    // 방법2. 필요한 만큼 즉시 처리 - 정석 풀이
    // 문자 하나씩 발화 시뮬레이션
    // 불필요한 문자열 생성 없음
    public String solution2(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int number = 0;
        int cnt = 0;

        // n진수 변환 및 A~F 처리
        while(answer.length() < t) {
            String cur = Integer.toString(number++, n).toUpperCase();

            // n진수의 모든 자릿수 처리
            for(int i = 0; i < cur.length(); i++) {
                if(cnt % m == p - 1) {
                    answer.append(cur.charAt(i));
                    if(answer.length() == t) break;
                }
                cnt++;
            }
        }

        return answer.toString();
    }
}
