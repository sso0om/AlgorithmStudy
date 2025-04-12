package sort.lv0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** 특이한 정렬 - 프로그래머스 120880
 *
 * 문제 유형 : sort, comparator
 */
public class SortedByDistance {

    // Solution 비교 분석 참고

    // 1. Stream + Comparator(람다 표현식) - 정석 방식, 자바 8 이상
    // Comparator : 객체의 기본 정렬 기준이 정의되어 있지 않거나, 기본 정렬과는 다른 방식으로 정렬하고 싶을 때 주로 사용
    private int[] solution1(int[] numlist, int n) {
        return Arrays.stream(numlist)
                .boxed()                // int stream -> Integer stream
                .sorted((a, b) -> {     // sorted(comparator 함수형 인터페이스로 정렬
                    int disA = Math.abs(n - a);
                    int disB = Math.abs(n - b);
                    if (disA == disB) {
                        return b - a;   // n까지 거리가 같을 시 큰 값-작은 값으로 정렬
                    }
                    return disA - disB; // n까지 거리 가까운 순(오름차순) 정렬
                })
                .mapToInt(i -> i)       // Integer -> int
                .toArray();
    }



    // 2. Comparator(익명 클래스) - 자바 7 이하
    private int[] solution2(int[] numlist, int n) {
        Integer[] temp = new Integer[numlist.length];

        // int[] -> Integer[] 복사 (객체 배열이어야 Comparator 사용 가능)
        for (int i = 0; i < numlist.length; i++) {
            temp[i] = numlist[i];
        }

        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int disA = Math.abs(n - a);
                int disB = Math.abs(n - b);
                if (disA == disB) {
                    return b - a;   // n까지 거리가 같을 시 큰 값-작은 값으로 정렬
                }
                return disA - disB; // n까지 거리 가까운 순(오름차순) 정렬
            }
        });

        // Integer[] -> int[] 복사
        int[] answer = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            answer[i] = temp[i];
        }

        return answer;
    }



    // 3. treeMap 사용 - 일반적이지 않은 방법
    private int[] solution3(int[] numlist, int n) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int num : numlist) {
            int distance = Math.abs(n - num) * 2 - 1;
            if (map.containsKey(distance)) {
                int mapNum = map.get(distance);
                map.put(distance, Math.max(mapNum, num));
                map.put(distance + 1, Math.min(mapNum, num));
            } else {
                map.put(distance, num);
            }
        }
        return map.values()
                .stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
