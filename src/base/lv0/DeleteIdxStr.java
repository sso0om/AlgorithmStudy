package base.lv0;

import java.util.Arrays;

/** 글자 지우기 - 프로그래머스 181900
 *
 * 문제 유형 : sort, 문자열
 */
public class DeleteIdxStr {

    // sort 내림차순 - 성능 최적화, 메모리 절약
    private String solution1(String my_string, int[] indices) {
        StringBuilder sb = new StringBuilder(my_string);

        // 인덱스 내림차순 - 뒤에서 부터 삭제 시 앞 인덱스 변하지 않음
        Arrays.sort(indices);
        for (int i = indices.length - 1; i >= 0; i--) {
            sb.deleteCharAt(indices[i]);
        }
        return sb.toString();
    }



    // split(), join()
    // 직관적, 간결함 / 공간복잡도 증가, 성능 저하
    // - split 문자열 길이만큼의 배열 추가 생성되므로 메모리 사용량 증가.
    // - join 내부적으로 StringBuilder 사용하여 문자열 다시 생성 과정 있음
    private String solution2(String my_string, int[] indices) {
        String[] arr = my_string.split("");
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = "";
        }
        return String.join("", arr);
    }



    // StringBuilder
    // 메모리 사용량 절약, 성능 우수 / 불필요한 공백 제거, 인덱스 잦은 변경
    // - replace(" ", "") 불필요한 공백 제거 과정, 새로운 문자열이 만들어져 성능 다소 저하
    // - 여러 번 인덱스를 변경할 경우 문제 발생 가능
    private String solution3(String my_string, int[] indices) {
        StringBuilder sb = new StringBuilder(my_string);
        for (int i = 0; i < indices.length; i++) {
            sb.replace(indices[i], indices[i] + 1, " ");
        }
        return sb.toString().replace(" ", "");
    }
}
