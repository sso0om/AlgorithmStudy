package simulation.lv1;

/** 유연근무제
 *
 * 문제 유형 : 시뮬레이션, time
 */
public class FlexibleWork {

    // 1. totalMinutes 계산
    // - 더 정확하고 일반적인 해결책
    // - 시각 계산은 60진법 특성 때문에 정확히 하려면 분 단위 계산이 필수
    private int solution1(int[] schedules, int[][] timelogs, int startday) {
        int cnt = 0;

        for (int i = 0; i < schedules.length; i++) {
            int deadLine = totalSeconds(schedules[i]) + 10;
            boolean isSuccess = true;

            // 일주일 출근 확인
            for (int j = 0; j < 7; j++) {
                int day = (startday + j) % 7;
                if (day == 6 || day == 0) continue; // 주말 제외

                // 평일에 지각한 경우 false
                int actual = totalSeconds(timelogs[i][j]);
                if(deadLine < actual) {
                    isSuccess = false;
                    break;
                }
            }
            // 지각 한 번도 안 한 직원
            if (isSuccess) {
                cnt++;
            }
        }
        return cnt;
    }

    // 시:분 -> 초
    private int totalSeconds(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        return hours * 60 + minutes;
    }





    // 2. hours, minutes 계산
    // - 문제 통과는 하지만 아래 이유로 비추천
    // - 정수 연산(100의 배수)을 직접 더하면 오류가 날 수 있음
    private int solution2(int[] schedules, int[][] timelogs, int startday) {
        int cnt = 0;

        for (int i = 0; i < schedules.length; i++) {
            int deadLine = sum10Seconds(schedules[i]);
            boolean isSuccess = true;

            // 일주일 출근 확인
            for (int j = 0; j < 7; j++) {
                int day = (startday + j) % 7;
                if (day == 6 || day == 0) continue; // 주말 제외

                // 평일에 지각한 경우 false
                if(deadLine < timelogs[i][j]) {
                    isSuccess = false;
                    break;
                }
            }
            // 지각 한 번도 안 한 직원
            if (isSuccess) {
                cnt++;
            }
        }

        return cnt;
    }

    // 시:분 + 10
    private int sum10Seconds(int schedule) {
        int hours = schedule / 100;
        int minutes = schedule % 100;

        if (minutes >= 50) {
            hours++;
            minutes %= 10;
        } else {
            minutes += 10;
        }

        return hours * 100 + minutes;
    }
}
