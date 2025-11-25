package string.lv2;

import java.util.ArrayList;
import java.util.List;

/** 파일명 정렬 - 프로그래머스 17686
 *
 * 문제 유형 : String, 파싱, 정렬
 */
public class FilesSort {
    // static class
    static class FileInfo {
        int idx;
        String head;
        int number;
        String original;

        public FileInfo(int idx, String head, int number, String original) {
            this.idx = idx;
            this.head = head;
            this.number = number;
            this.original = original;
        }
    }

    // 파싱해서 구조화
    // 정렬 기준 명확히 적용
    // 비교 기준이 여러 개면 → 객체로 묶음
    public String[] solution1(String[] files) {
        List<FileInfo> list = new ArrayList<>();

        for(int i = 0; i < files.length; i++) {
            list.add(parse(files[i], i));
        }

        // 커스텀 정렬 : head -> number -> 입력순
        list.sort((a, b) -> {
            // 1. head 기준 대소문자 상관없이 문자열 오름차순
            int headCompare = a.head.toLowerCase().compareTo(b.head.toLowerCase());
            if(headCompare != 0) {
                return headCompare;
            }

            // 2. number 기준 숫자 오름차순
            int numberCompare = Integer.compare(a.number, b.number);
            if(numberCompare != 0) {
                return numberCompare;
            }

            // 3. 입력된 순서(files 인덱스 순서)
            return Integer.compare(a.idx, b.idx);
        });

        // 정렬된 문자열 배열로 변환
        String[] answer = new String[files.length];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).original;
        }

        return answer;
    }

    // 파싱
    private FileInfo parse(String file, int idx) {
        int i = 0;

        // head : 숫자가 아닌 문자열 (숫자 직전까지의 문자열)
        while(i < file.length() && !Character.isDigit(file.charAt(i))) {
            i++;
        }
        String head = file.substring(0, i);
        int start = i; // 숫자 시작점

        // number : file의 첫 숫자부터 연속되는 5개 이하의 숫자
        while(i < file.length() && Character.isDigit(file.charAt(i)) && i - start < 5) {
            i++;
        }
        int number = Integer.parseInt(file.substring(start, i));

        return new FileInfo(idx, head, number, file);
    }
}
