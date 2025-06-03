package array.lv0;

import java.util.*;
import java.util.stream.IntStream;

/** 전국 대회 선발 고사
 * 문제 유형 : int[] rankByIdx, map, IntStream, rank
 */
public class AttendeeRanking3 {

    // 1. int[] rankByIdx - 추천
    //- 시간 복잡도: O(N)
    //- 유지보수성은 낮으나 빠르고 최적화된 방식
    public int solution1(int[] rank, boolean[] attendance) {
        int[] rankByIdx = new int[rank.length + 1]; // 인덱스 = 랭크
        Arrays.fill(rankByIdx, -1);

        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                rankByIdx[rank[i]] = i; // 참석자 랭크-번호
            }
        }
        Arrays.sort(rank);

        int[] highRanks = new int[3];
        int idx = 0;
        for (int i = 1; i < rankByIdx.length; i++) { // 참석자 중 1순위 ~ 3순위
            if (idx == 3) break;

            if (rankByIdx[i] != -1) {
                highRanks[idx++] = rankByIdx[i];
            }
        }
        return 10000 * highRanks[0] + 100 * highRanks[1] + highRanks[2];
    }



    // 2. Map<Integer, Integer> rankByIdx
    //- 시간 복잡도: O(N log N)
    //- 명확하고 유지보수성은 높으나 약간 장황한 방식
    //    - 데이터 가공이나 전처리가 복잡한 경우, 또는 값 → 위치 참조가 많이 필요한 상황에서 Map 사용
    public int solution2(int[] rank, boolean[] attendance) {
        Map<Integer, Integer> rankByIdx = new HashMap<>(); // Map<Rank, Index>

        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                rankByIdx.put(rank[i], i); // 참석자 랭크-번호
            }
        }
        Arrays.sort(rank);

        int[] highRanks = new int[3];
        int idx = 0;
        for (int rk : rank) { // 참석자 중 1순위 ~ 3순위
            if (idx == 3) break;
            if (rankByIdx.containsKey(rk)) {
                highRanks[idx++] = rankByIdx.get(rk);
            }
        }

        return 10000 * highRanks[0] + 100 * highRanks[1] + highRanks[2];
    }



    // 3. Stream
    //- 시간복잡도: O(N log N)
    public int solution3(int[] rank, boolean[] attendance) {
        List<Integer> top3 = IntStream.range(0, rank.length)
                .filter(i -> attendance[i])                        // 참석자
                .boxed()
                .sorted(Comparator.comparingInt(i -> rank[i])) // 랭크 기준 정렬
                .limit(3)                                     // 3명 제한
                .toList();

        return 10000 * top3.get(0) + 100 * top3.get(1) + top3.get(2);
    }
}
