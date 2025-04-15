package sort.lv0;

import java.util.*;

/** 등수 매기기 - 프로그래머스 120882
 *
 * 문제 유형 : rank, sort, int[총점][인덱스], Map<총점, 등수>
 */
public class ScoreAverageSort {

    // 1. 정렬 + 인덱스 추적 방식
    // - 일반적인 등수 계산 문제 사용 추천
    private int[] solution1(int[][] score) {
        int n = score.length;
        int[][] sumWithIdx = new int[n][2];

        // averageWithIdx[총점][인덱스]
        for (int i = 0; i < n; i++) {
            int avg = score[i][0] + score[i][1];
            sumWithIdx[i][0] = avg;
            sumWithIdx[i][1] = i;
        }
        // 총점 내림차순
        Arrays.sort(sumWithIdx, (a, b) -> Double.compare(b[0], a[0]));

        int[] ranks = new int[n];

        // 인덱스 기준 랭크 부여
        for (int i = 0; i < n; i++) {
            int originIdx = sumWithIdx[i][1];

            // 이전 총점과 같으면 동일 등수 부여
            if (i > 0 && sumWithIdx[i][0] == sumWithIdx[i - 1][0]) {
                ranks[originIdx] = ranks[sumWithIdx[i - 1][1]];
            } else {
                // 새로운 총점일 경우 현재 순위
                ranks[originIdx] = i + 1;
            }
        }
        return ranks;
    }



    // 2. Map<총점, 등수>
    // - 값-등수 빠른 참조 필요, 중복 점수 많을 경우 사용 추천
    private int[] solution2(int[][] score) {
        int n = score.length;
        List<Integer> sumList = new ArrayList<>();

        // 총점 리스트
        for (int[] student : score) {
            sumList.add(student[0] + student[1]);
        }

        // 성적 총점 내림차순
        List<Integer> sortedList = new ArrayList<>(sumList);
        sortedList.sort(Comparator.reverseOrder());

        // 총점, 등수 매핑
        Map<Integer, Integer> scoreToRank = new HashMap<>();
        int rank = 1;
        for (int sum  : sortedList) {
            if (!scoreToRank.containsKey(sum)) {
                scoreToRank.put(sum, rank);
            }
            rank++;
        }

        // 총점 기준 등수 부여
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = sumList.get(i);
            ranks[i] = scoreToRank.get(sum);
        }
        return ranks;
    }
}
