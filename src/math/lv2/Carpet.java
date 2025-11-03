package math.lv2;

/** 카펫 - 프로그래머스 42842
 *
 * 문제 유형 : 수식, 약수 탐색
 */
public class Carpet {

    // 방법 1. yellow의 약수 공식 - 추천
    public int[] solution1(int brown, int yellow) {
        int total = brown + yellow;

        for(int h = 1; h * h <= yellow; h++) { // 항상 세로가 가로보다 같거나 작음
            if(yellow % h != 0) continue;

            int w = yellow / h;
            int tH = h + 2;
            int tW = w + 2;
            if(tH * tW == total) {
                return new int[]{tW, tH};
            }
        }
        return new int[0];
    }

    // 방법 2. total의 약수 공식
    public int[] solution2(int brown, int yellow) {
        int total = brown + yellow;

        // 노랑을 감싸기위해 최소 3부터 시작, 세로는 가로 보다 같거나 작음
        for(int h = 3; h <= total / h; h++) {
            if(total % h != 0) continue;

            int w = total / h;
            // 테두리 2를 제외한 내부 크기
            int inner = (w - 2) * (h - 2);

            if(inner == yellow) {
                return new int[]{w, h};
            }
        }
        return new int[]{0, 0};
    }

    // 방법 3. 4를 제외한 너비 공식
    // -4는 모서리 중복 제거
    // w * h = brown + yellow
    // brown = (w + h) * 2 - 4
    public int[] solution3(int brown, int yellow) {
        int total = brown + yellow;

        for (int height = 3; height <= total / height; height++) {
            if (total % height != 0) continue;

            int width = total / height;

            // 갈색 격자 수 = (가로 + 세로) * 2 - 4
            int border = (width + height) * 2 - 4;

            if (border == brown) {
                return new int[]{width, height};
            }
        }

        return new int[]{0, 0}; // 이론상 도달하지 않음
    }
}
