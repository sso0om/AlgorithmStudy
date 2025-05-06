package simulation.lv1;

/** 붕대 감기
 *
 * 문제 유형 : 시뮬레이션
 */
public class Bandage {

    // 1. 시뮬레이션 (1초 단위 검사)
    // 시간 복잡도 : O(T + N)
    // - 가장 직관적
    // - 매초마다 회복 또는 공격 여부 판단, 연속 회복 시간 추적
    private int solution1(int[] bandage, int health, int[][] attacks) {
        int hp = health;
        int seqTime = 0;
        int idx = 0;
        int endTime = attacks[attacks.length - 1][0];

        // 공격 횟수 N ≤ 100, 최대 시간 T ≤ 1000 : O(T + N)
        // 1초 단위 검사
        for (int time = 1; time <= endTime; time++) {
            // 공격 시점일 경우
            if (idx < attacks.length && attacks[idx][0] == time) {
                hp -= attacks[idx][1]; // 공격
                // 죽었을 시 -1 리턴
                if (hp <= 0) {
                    return -1;
                }
                seqTime = 0; // 연속 시간 초기화
                idx++;
            } else { // 회복
                hp += bandage[1]; // hp + 초당 회복량
                seqTime++;
                // 시전 시간 만큼 연속 회복 시 추가 회복
                if (seqTime == bandage[0]) {
                    hp += bandage[2];
                    seqTime = 0;
                }
                hp = Math.min(hp, health); // hp <= health
            }
        }
        return hp;
    }





    // 2. 공격 단위 검사
    // 시간 복잡도 : O(n)
    // - 빠름
    // - 두 공격 사이 시간만큼 회복 계산, 필요한 회복/추가회복 계산 후 바로 공격 처리
    private int solution2(int[] bandage, int health, int[][] attacks) {
        int hp = health;
        int prev = 0;

        // 공격 단위 검사 : O(n)
        for (int[] attack : attacks) {
            int time = attack[0] - prev - 1; // 공격간 시간 간격
            int seqTime = time / bandage[0]; // 추가 할당량 개수

            hp = Math.min(health, hp + (time * bandage[1]));    // 회복
            hp = Math.min(health, hp + (seqTime * bandage[2])); // 추가 할당량
            hp -= attack[1]; // 공격

            // 죽었을 시 -1 리턴
            if (hp <= 0) {
                return -1;
            }
            prev = attack[0];
        }
        return hp;
    }
}
