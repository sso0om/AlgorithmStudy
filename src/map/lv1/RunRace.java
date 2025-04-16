package map.lv1;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** 달리기 경주
 *
 * 문제 유형 : Map<Player, Index> + players[idx] swap
 */
public class RunRace {

    // Map<Player, Index> + players[idx] swap
    // - 값을 기반으로 인덱스를 찾거나, 위치 바꾸는 작업 반복될 경우 Map<val, idx>로 관리
    // - Map.get(val) : 즉시 조회 O(1)
    // - 전체 시간복잡도(n: 선수 수, m: 호출 수) : O(n + m)
    private String[] solution1(String[] players, String[] callings) {
        // Map<Player, Index>
        Map<String, Integer> playerWithIdx = new HashMap<>();

        // 초기 맵 생성(n: 선수 수) : O(n)
        for (int i = 0; i < players.length; i++) {
            playerWithIdx.put(players[i], i);
        }

        // 호출 처리(m: 호출 수) : get, swap, map 갱신 - O(1) per call -> 총 O(m)
        for (String calling : callings) {
            // 호명된 선수의 현재 랭크
            int idx = playerWithIdx.get(calling);

            // 1등은 더 앞으로 갈 수 없으므로 2등부터 swap 가능
            if (idx > 0) {
                String frontPlayer = players[idx - 1];

                // 순서 swap
                players[idx - 1] = calling;
                players[idx] = frontPlayer;

                // 랭크 변경
                playerWithIdx.put(calling, idx - 1);
                playerWithIdx.put(frontPlayer, idx);
            }
        }
        return players;
    }





    // List swap - 시간 초과
    // - list.indexOf(val) : 순차 조회 O(n)
    // 전체 시간복잡도(n: 선수 수, m: 호출 수) : O(n X m)
    private String[] solution_overTime(String[] players, String[] callings) {
        List<String> ranks = Arrays.stream(players).collect(Collectors.toList());

        // 호출 : O(m)
        for (String calling : callings) {
            // 선수 list.indexOf(val) : O(n)
            int beforeRank = ranks.indexOf(calling);
            ranks.set(beforeRank, ranks.get(beforeRank - 1));
            ranks.set(beforeRank - 1, calling);
        }
        return ranks.toArray(String[]::new);
    }

}
