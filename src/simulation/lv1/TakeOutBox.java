package simulation.lv1;

/** 택배 상자 옮기기
 *
 * 문제 유형 : 시뮬레이션
 */
public class TakeOutBox {

    // 1. 시뮬레이션 - 상자 쌓기 직접 구현
    private int solution1(int n, int w, int num) {
        int[][] boxes = new int[w][(n + w - 1) / w];
        boolean leftToRight = true;
        int idx = 0, h = 0;
        int numIdx = 0, numH = 0;

        // 상자 쌓기
        for (int i = 1; i <= n; i++) {
            boxes[idx][h] = i;
            if (i == num) { // 대상 상자
                numIdx = idx;
                numH = h;
            }

            // 왼-오 : idx++, 오-왼 : idx--;
            idx = leftToRight ? idx + 1 : idx - 1;
            if (i % w == 0) {
                // 회점 일 경우 idx 초기화
                // 왼-오 -> 오-왼 : 길이 - 1, 오-왼 -> 왼-오 : 0
                idx = leftToRight ? w - 1 : 0;
                h++; // 층 증가
                leftToRight = !leftToRight;
            }
        }

        // 상자 개수 : 제일 위 상자 높이 - 현재 높이
        int lastH = boxes[numIdx].length - 1;
        return boxes[numIdx][lastH] == 0 ? lastH - numH : lastH - numH + 1;
    }

    
    
    // 2. 수학적 접근
    private int solution2(int n, int w, int num) {
        int h = (n + w - 1) / w; // 최대 높이

        int numRow = (num - 1) / w;      // 대상 상자의 높이
        int numIdxInRow = (num - 1) % w; // 한 뱡향일 때의 idx
        boolean isLeftToRight = numRow % 2 == 0;

        // 왼-오 : 기존 idx, 오-왼 : 길이 - 1 - 기존 idx
        int col = isLeftToRight ? numIdxInRow : (w - 1 - numIdxInRow);

        int cnt = 0;
        for (int r = h - 1; r >= numRow; r--) {
            boolean left = r % 2 == 0;

            // 왼-오 : 
            int boxIdxInRow = left ? col : (w - 1 - col);
            int boxNum = r * w + boxIdxInRow + 1;
            if (boxNum > n) continue;

            cnt++;
        }
        return cnt;
    }
}
