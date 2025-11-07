package map.lv2;

import java.util.*;

/** 튜플 - 프로그래머스 64065
 *
 * 문제 유형 : 해시(Map) / 빈도 집계, 문자열 파싱
 */
public class Tuple {

    // 방법1. List<Set> + 차집합 - 비추
    // - 자료구조 중첩, 사고 과정 복잡
    public int[] solution1(String s) {
        List<Set<Integer>> tuples = new ArrayList<>();
        Set<Integer> tuple = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        // 문자열 파싱 ---
        // 가장 바깥의 {} 제외, 내부 튜플{} 조회
        for(int i = 1; i < s.length() - 1; i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) { // 숫자 StringBuilder에 넣기
                sb.append(ch);
            } else if(ch == '}' || ch == ',') { //
                if(sb.length() > 0) { // 빈 값이 아닌 숫자일 때 튜플 원소 추가
                    tuple.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0); // StringBuilder 초기화
                }
                if(ch == '}') {
                    tuples.add(tuple);
                    tuple = new HashSet<>();
                }
            }
        }
        tuples.sort(Comparator.comparingInt(Set::size));

        int[] answer = new int[tuples.size()];
        Set<Integer> prev = new HashSet<>();
        int i = 0;

        //
        for(Set<Integer> cur : tuples) {
            cur.removeAll(prev);
            int val = cur.iterator().next();
            answer[i++] = val;
            prev.add(val);
        }
        return answer;
    }


    // 방법2. Split + Set - 빠른 코테용
    // - 문제 난이도 낮고 입력 크기 작을 때 사용
    // - 단점: 정규식 / split 남발
    public int[] solution(String s) {
        String[] tuples = s.replace("{{", "")
            .replace("}}", "")
            .split("\\},\\{");

        Arrays.sort(tuples, Comparator.comparingInt(String::length));

        int[] answer = new int[tuples.length];
        Set<Integer> prev = new HashSet<>();
        int i = 0;

        for(String tuple : tuples) {
            for(String num : tuple.split(",")) {
                int val = Integer.parseInt(num);
                if(prev.add(val)) {
                    answer[i++] = val;
                    break;
                }
            }
        }
        return answer;
    }


    // 방법3. Map<원소, 등장 횟수> - 추천
    // 장점: 자료구조 단순 (`Map` 하나), 예외 케이스 거의 없음
    // Map<원소, 등장 횟수>
    // a1: n번, a2: n - 1번, ….. an: 1번
    // entrySet → value 내림차순 → key만 뽑아 배열
    public int[] solution3(String s) {
        Map<Integer, Integer> countMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                if (!sb.isEmpty()) {
                    int num = Integer.parseInt(sb.toString());
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1); // Map<원소, 등장 횟수>
                    sb.setLength(0);
                }
            }
        }

        return countMap.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue()) // value(횟수) 내림차순
            .mapToInt(Map.Entry::getKey) // key(원소) 추출
            .toArray();
    }
}
