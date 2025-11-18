package bruteforce.lv2;

/** 문자열 압축 - 프로그래머스 60057
 *
 * 문제 유형 : 완전탐색(브루트 포스), String
 */

public class StringCompression {

    public int solution(String s) {
        int min = s.length(); // 최소 길이 초기화 (원래 문자열 길이)

        // 압축 단위: 1부터 문자열 길이 절반까지
        for (int i = 1; i <= s.length() / 2; i++) {
            min = Math.min(min, getShortLen(s, i));
        }

        return min;
    }

    private int getShortLen(String s, int div) {
        String pre = s.substring(0, div);
        int loop = 1; // 반복 횟수
        int cnt = 0;
        int len = s.length();

        for (int i = div; i < len; i += div) {
            int end = Math.min(i + div, len);
            String cur = s.substring(i, end);

            if (pre.equals(cur)) {
                loop++; // 반복 증가
            } else {
                // 반복 끝났을 때 길이 누적
                cnt += pre.length(); // 문자의 길이
                if (loop > 1) cnt += String.valueOf(loop).length(); // 반복 숫자 길이
                pre = cur;
                loop = 1;
            }
        }

        // 마지막 반복 처리
        cnt += pre.length();
        if (loop > 1) cnt += String.valueOf(loop).length();

        return cnt;
    }

}
