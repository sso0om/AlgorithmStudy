package array.lv2;

import java.util.Arrays;

/** H-Index - 프로그래머스 42747
 *
 * 문제 유형 : 배열 정렬
 */
public class HIndex {

    // 방법 1. 오름차순
    public int solution1(int[] citations) {
        int n = citations.length;
        int h = 0;
        Arrays.sort(citations);

        for(int i = 0; i < n; i++) {
            int cnt = n - i; // citations[i] 이상 인용된 논문 수
            if(cnt <= citations[i]) {
                h = cnt;
                break;
            }
        }
        return h;
    }

    // 방법 2. 내림차순
    public int solution2(int[] citations) {
        Arrays.sort(citations);

        int h = 0;
        for (int i = citations.length - 1, cnt = 1; i >= 0; i--, cnt++) {
            if (citations[i] >= cnt) {
                h = cnt;
            } else {
                break;
            }
        }
        return h;
    }
}
