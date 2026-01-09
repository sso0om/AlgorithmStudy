package simulation.lv2;

/** 방금그곡 - 프로그래머스 17683
 *
 * 문제 유형 : 구현, 문자열 파싱
 */
public class ThatSong {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxSec = 0;

        m = convert(m);

        for(String musicInfo : musicinfos) {
            String[] parts = musicInfo.split(",");

            int sec = toSec(parts[1]) - toSec(parts[0]);
            String music = convert(parts[3]);

            music = music.repeat((sec + music.length() - 1) / music.length())
                .substring(0, sec);

            if(music.contains(m) && maxSec < sec) {
                answer = parts[2];
                maxSec = sec;
            }
        }

        return answer;
    }

    private int toSec(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60
            + Integer.parseInt(parts[1]);
    }

    private String convert(String str) {
        return str.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a");
    }
}
