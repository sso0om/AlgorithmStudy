package simulation.lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChatsRoom {
    class Log {
        String uid;
        String cmd;

        public Log(String uid, String cmd) {
            this.uid = uid;
            this.cmd = cmd;
        }
    }

    public String[] solution(String[] record) {
        // Map<uid, name>
        Map<String, String> nameMap = new HashMap<>();
        List<Log> logs = new ArrayList<>();

        // Log(uid, cmd) 쌓기
        for(int i = 0; i < record.length; i++) {
            String[] cmds = record[i].split(" ");
            String cmd = cmds[0];
            String uid = cmds[1];

            if(cmd.equals("Enter")) {          // Enter : uid-이름 추가/변경, 로그 추가
                nameMap.put(uid, cmds[2]);
                logs.add(new Log(uid, cmd));
            } else if(cmd.equals("Leave")) {   // Leave : 로그 추가
                logs.add(new Log(uid, cmd));
            } else {                           // Change : 이름 변경
                nameMap.put(uid, cmds[2]);
            }
        }

        String[] answer = new String[logs.size()];
        int idx = 0;

        // 최종 이름 기준 로그 출력
        for(Log log : logs) {
            String cmd = log.cmd.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.";
            answer[idx++] = nameMap.get(log.uid) + cmd;
        }

        return answer;
    }
}
