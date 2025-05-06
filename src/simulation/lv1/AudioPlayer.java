package simulation.lv1;

/** 동영상 재생기
 *
 * 문제 유형 : 시뮬레이션, time
 */
public class AudioPlayer {

    private String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int now = toTotalSeconds(pos);
        int opS = toTotalSeconds(op_start);
        int opE = toTotalSeconds(op_end);
        int len = toTotalSeconds(video_len);
        now = skipOpeningIfInRange(opS, now, opE);

        for (String cmd : commands) {
            if (cmd.equals("prev")) { // 10초 전으로 이동
                now = Math.max(0, now - 10);
            } else { // 10초 후로 이동
                now = Math.min(len, now + 10);
            }
            now = skipOpeningIfInRange(opS, now, opE);
        }
        return toStrTime(now);
    }

    // 오프닝 건너뛰기
    private int skipOpeningIfInRange(int opS, int now, int opE) {
        // 현 시점이 오프닝 위치면 건너뛰기
        if (opS <= now && now <= opE) {
            now = opE;
        }
        return now;
    }

    // "분:초" -> 초
    private int toTotalSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        return minutes * 60 + seconds;
    }

    // 초 -> "분:초"
    private String toStrTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
    }
}
