package greedy.lv1;

import java.util.*;

/** 체육복
 *
 * 문제 유형 : Greedy, 예외 케이스, 중복 제거(교집합 제거), contains
 */
public class GymClothes {

    // 1. Set.contains
    // 시간 복잡도 : O(n)
    private int solution1(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        // lostSet : 체육복 잃어 버린 경우
        for (int num : lost) lostSet.add(num);

        // reserveSet : 체육복, 여벌 체육복 둘 다 있는 경우
        for (int num : reserve) {
            // 예외 케이스 제거 - 체육복을 잃어버렸지만 여벌 체육복이 있는 경우
            // (lost-reserve 교집합 제거)
            if (lostSet.contains(num)) { // O(1)
                lostSet.remove(num);
            } else {
                reserveSet.add(num); // lost X, reserve O
            }
        }

        // lostSet - 앞뒤 번호에게 여벌 체육복을 빌릴 수 있는 경우 제거
        for (int num : reserveSet) {
            if (lostSet.contains(num - 1)) {
                lostSet.remove(num - 1);
            } else if (lostSet.contains(num + 1)) {
                lostSet.remove(num + 1);
            }
        }

        // 전체 인원 - 잃어버린 인원 = 체육복 있는 인원
        return n - lostSet.size();
    }





    // 2. 배열 시뮬레이션 방식
    // 시간 복잡도 : O(n)
    private int solution2(int n, int[] lost, int[] reserve) {
        int[] students = new int[n + 2]; // 경계 조건 처리 (인덱스 예외 방지)
        int cnt = n;

        // lost -1, reserve +1
        // 예외 자동 처리됨 - 체육복을 잃어버렸지만(-1) 여벌 체육복이 있는 경우(+1) = 0
        for (int num : lost) students[num]--;    // 잃어버린 경우 -1
        for (int num : reserve) students[num]++; // 여분 체육복 있는 경우 +1

        for (int i = 1; i < students.length - 1; i++) {

            // 잃어버린 경우
            if (students[i] == -1) {
                // 앞뒤 번호에게 여벌 체육복을 빌릴 수 있는 경우 제거
                if (students[i -1] == 1) {
                    students[i]++;     // 사용 처리
                    students[i - 1]--; // 해결 처리
                } else if (students[i + 1] == 1) {
                    students[i]++;     // 사용 처리
                    students[i + 1]--; // 해결 처리
                } else {
                    cnt--; // 빌리지 못한 경우
                }
            }
        }
        
        // 전체 인원 - 빌리지 못한 경우
        return cnt;
    }





    // 3. 정석 Greedy, Arrays...anyMatch() + List.contains
    // 시간 복잡도 : O(n^2)
    private int solution3(int n, int[] lost, int[] reserve) {
        List<Integer> lostList = new ArrayList<>();
        List<Integer> reserveList = new ArrayList<>();

        // 먼저 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);

        // lostList : 체육복 잃어버린 경우
        for (int l : lost) {
            boolean isDuplicate = false;

            // 예외 케이스 제거 - 체육복을 잃어버렸지만 여벌 체육복이 있는 경우
            // (lost-reserve 교집합 제거)
            for (int i = 0; i < reserve.length; i++) {
                if (reserve[i] == l) {
                    reserve[i] = -1; // 사용 처리
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                lostList.add(l);
            }
        }

        // reserveList : 체육복, 여벌 체육복 둘 다 있는 경우
        for (int r : reserve) {
            if (r != -1) {
                reserveList.add(r);
            }
        }

        // 여벌 체육복 빌려주기
        for (int i = 0; i < lostList.size(); i++) {
            int lostNum = lostList.get(i);

            for (int j = 0; j < reserveList.size(); j++) {
                int reserveNum = reserveList.get(j);

                // 앞뒤 번호에게 여벌 체육복을 빌릴 수 있는 경우 제거
                if (Math.abs(reserveNum - lostNum) == 1) {
                    reserveList.set(j, -1); // 사용 처리
                    lostList.set(i, -1);    // 해결 처리
                    break;
                }
            }
        }

        // 잃어버린 인원
        int lostCnt = 0;
        for (int num : lostList) {
            if (num != -1) lostCnt++;
        }

        // 전체 인원 - 잃어버린 인원 = 체육복 있는 인원
        return n - lostCnt;
    }
}
