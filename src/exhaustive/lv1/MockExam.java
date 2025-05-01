package exhaustive.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/** 모의고사 - 프로그래머스 42840
 *
 * 문제 유형 : 완전 탐색
 */
public class MockExam {

    // 1. loop + Stream 결과 추출
    // 시간 복잡도 : O(n)
    // - 매우 빠름, 깔끔함, 성능 준수
    // - 성능 우선일 경우
    private int[] solution1(int[] answers) {
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] scores = new int[3];

        // 수포자별 점수 계산
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < patterns.length; j++) {
                int[] p = patterns[j];
                // 정답일 경우
                if (p[i % p.length] == answers[i]) {
                    scores[j]++;
                }
            }
        }
        // 최고 점수
        int max = Arrays.stream(scores)
                .max()
                .getAsInt();

        // 최고 점수인 수포자 배열 리턴
        return IntStream.range(0, scores.length)
                .filter(i -> scores[i] == max)
                .map(i -> i + 1)
                .toArray();
    }

    
    
    
    
    // 2. Stream 기반 점수 계산
    // 시간 복잡도 : O(n)
    // - 가장 선언적, 다소 느림, 오버헤드 있음
    // - 가독성 + 유지보수 (성능 차이 중요하지 않을 경우)
    private int[] solution2(int[] answers) {
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        // 수포자별 점수 계산
        int[] scores = IntStream.range(0, patterns.length)
                .map(i -> {
                    int[] pattern = patterns[i];
                    return (int) IntStream.range(0, answers.length)
                            .filter(j -> answers[j] == pattern[j % pattern.length]) // 정답일 경우
                            .count();
                })
                .toArray();

        // 최고 점수
        int max = Arrays.stream(scores)
                .max()
                .getAsInt();

        // 최고 점수인 수포자 배열 리턴
        return IntStream.range(0, scores.length)
                .filter(i -> scores[i] == max)
                .map(i -> i + 1)
                .toArray();
    }





    // 3. loop + List
    // 시간 복잡도 : O(n)
    // - 가장 빠름, 고전적 방식, 박싱 있음
    // - 절대적인 최속 구현 필요 시 사용
    private int[] solution3(int[] answers) {
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] cnt = new int[3];

        // 수포자별 점수 계산
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < patterns.length; j++) {
                int[] p = patterns[j];
                // 정답일 경우
                if (p[i % p.length] == answers[i]) {
                    cnt[j]++;
                }
            }
        }

        // 최고 점수
        int max = 0;
        for (int c : cnt) {
            if (max < c) {
                max = c;
            }
        }

        // 최고 점수인 수포자 리스트
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
            if (max == cnt[i]) {
                list.add(i + 1);
            }
        }

        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
