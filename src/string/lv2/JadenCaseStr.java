package string.lv2;

/** JadenCase 문자열 만들기 - 프로그래머스 12951
 *
 * 문제 유형 : 문자열 처리, 조건부 대문자/소문자 변환
 */
public class JadenCaseStr {

    // 방법1. flag + char 순회 - 추천
    // 시간 복잡도: O(n) - 문자열을 한 번만 순회
    // 공백 기준으로 상태(flag) 관리
    // 숫자는 toUpperCase / toLowerCase 해도 변화 없음
    public String solution1(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isStart = true;

        for(char ch : s.toCharArray()) {
            if(ch == ' ') {
                isStart = true;
            } else if(isStart) {
                ch = Character.toUpperCase(ch);
                isStart = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            sb.append(ch);
        }
        return sb.toString();
    }


    // 방법2. split(” “) - 비추
    // 공백 개수 보존이 안 돼서 split 풀이는 비추천
    public String solution2(String s) {
        String[] words = s.split(" ", -1);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) { // 단어 일 때
                sb.append(Character.toUpperCase(word.charAt(0))); // 첫단어 - 대문자
                sb.append(word.substring(1).toLowerCase()); // 이후 - 소문자
            }
            // 마지막 단어 뒤에는 공백을 추가하지 않도록 막는 역할
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }


    // 방법3. split(””) + flag - 비추
    public String solution3(String s) {
        String answer = "";
        String[] sp = s.toLowerCase().split("");
        boolean flag = true;

        for(String ss : sp) {
            answer += flag ? ss.toUpperCase() : ss;
            flag = ss.equals(" ") ? true : false;
        }

        return answer;
    }
}
