package simulation.lv2;

/** 괄호 변환 - 프로그래머스 60058
 *
 * 문제 유형 : String, 재귀, 구현
 */
public class ConvertBrackets {

    // 1. 통합 버전
    // 시간 복잡도 : O(n^2)
    // - u, v 분리와 u의 올바른 괄호 여부를 한 번의 순회에서 같이 처리
    // - 분리와 검증 책임이 한 메서드에 함께 있어 가독성은 상대적으로 떨어짐
    public String solution1(String p) {
        if(p.isEmpty()) return "";

        int cnt = 0;
        int idx = 0;
        boolean isCorrect = true;

        for(int i = 0; i < p.length(); i++) {
            cnt += p.charAt(i) == '(' ? 1 : -1;

            // 올바른 괄호 아닌 경우
            if(cnt < 0) {
                isCorrect = false;
            }

            // 균형 잡힌 괄호인 경우
            if(cnt == 0) {
                idx = i;
                break;
            }
        }

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        // 올바른 괄호인 경우 처리
        if(isCorrect) {
            return u + solution1(v);
        }

        // 올바른 괄호 아닌 경우 처리
        StringBuilder sb = new StringBuilder();
        sb.append("(")
            .append(solution1(v))
            .append(")");

        // u 괄호 방향 뒤집기, 양쪽 끝 제거
        for(int i = 1; i < u.length() - 1; i++) {
            char ch = u.charAt(i) == '(' ? ')' : '(';
            sb.append(ch);
        }

        return sb.toString();
    }


    // 2. 메서드 분리 버전 - 권장
    // 시간 복잡도 : O(n^2)
    // - splitIdx()와 isCorrect()로 책임이 분리되어 가독성이 좋음
    // - 전체 접근적 복잡도는 동일하고, 유지보수성과 이해 면에서는 더 좋음
    public String solution2(String p) {
        if(p.isEmpty()) return "";

        int idx = splitIdx(p);

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        if(isCorrect(u)) {
            return u + solution2(v);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(")
            .append(solution2(v))
            .append(")");

        for(int i = 1; i < u.length() - 1; i++) {
            char ch = u.charAt(i) == '(' ? ')' : '(';
            sb.append(ch);
        }

        return sb.toString();
    }

    private int splitIdx(String p) {
        int cnt = 0;

        for(int i = 0; i < p.length(); i++) {
            cnt += p.charAt(i) == '(' ? 1 : -1;

            if(cnt == 0) return i;
        }
        return -1;
    }

    private boolean isCorrect(String u) {
        int cnt = 0;

        for(int i = 0; i < u.length(); i++) {
            cnt += u.charAt(i) == '(' ? 1 : -1;

            if(cnt < 0) return false;
        }
        return true;
    }
}
