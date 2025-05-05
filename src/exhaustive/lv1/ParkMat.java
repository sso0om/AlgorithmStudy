package exhaustive.lv1;

import java.util.Arrays;

/** 공원 - 프로그래머스
 *
 * 문제 유형 : 완전탐색(브루트 포스), 누적합 공식, Board
 */
public class ParkMat {

    // 1. 완전탐색(브루트 포스)
    // - 모든 가능한 위치(i, j)에서 모든 가능한 돗자리 크기에 대해 직접 놓아보며 조건 검사
    // - 시간 복잡도 : O(size^2), 최악의 경우 : O(k * h * w * size^2)
    private int solution1(int[] mats, String[][] park) {
        // 돗자리 크기순 정렬
        Arrays.sort(mats);

        // 큰 돗자리부터 직접 놓아보며 조건 검사 : O(k)
        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];
            // 돗자리를 깔 수 있을 시 크기 리턴
            if (canPlaceMat(size, park)) {
                return size;
            }
        }
        return -1;
    }

    // 돗자리를 깔 수 있는 지 검사
    private boolean canPlaceMat(int size, String[][] park) {
        int h = park.length - size;
        int w = park[0].length - size;

        // 전체 영역 검사 (돗자리 시작점) : O(hw)
        for (int i = 0; i <= h; i++) {
            for (int j = 0; j <= w; j++) {
                // 돗자리 크기만큼 빈 공간 있을 경우 true 리턴
                if (isAreaEmpty(size, park, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 돗자리 크기의 공간이 비었는지 여부
    private boolean isAreaEmpty(int size, String[][] park, int row, int col) {
        // 돗자리 크기만큼 검사 : O(size^2)
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                // 다른 돗자리가 이미 있는 경우 false 리턴
                if (!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }





    // 2. 누적합(Prefix Sum)
    // - 누적합/2차원 합 배열 + 최적화된 완전탐색 조합 방식
    // - 시간 복잡도 : O(N^2)
    private int solution2(int[] mats, String[][] park) {
        int h = park.length;
        int w = park[0].length;
        int[][] usedMap = new int[h][w];

        // 다른 돗자리가 이미 있는 경우 1, 빈 자리는 0
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                usedMap[i][j] = park[i][j].equals("-1") ? 0 : 1;
            }
        }

        // 누적합 배열 생성 : O(N^2)
        int[][] prefix = new int[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                prefix[i][j] = usedMap[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        // 큰 돗자리부터 직접 놓아보며 조건 검사
        Arrays.sort(mats);
        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];
            // 돗자리를 깔 수 있을 시 크기 리턴
            if (canPlaceMat2(size, prefix, h, w)) {
                return size;
            }
        }
        return -1;
    }

    // 돗자리를 깔 수 있는 지 검사
    private boolean canPlaceMat2(int size, int[][] prefix, int h, int w) {
        // 전체 영역 검사 (돗자리 시작점)
        for (int i = 0; i <= h - size; i++) {
            for (int j = 0; j <= w - size; j++) {
                // 돗자리 크기만큼 빈 공간 있을 경우 true 리턴
                if (getAreaSum(prefix, i, j, i + size - 1, j + size - 1) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // 돗자리 크기의 공간이 비었는지 여부
    // - 사각형 범위 검사(구간 합) : O(1)
    private int getAreaSum(int[][] prefix, int r1, int c1, int r2, int c2) {
        return prefix[r2 + 1][c2 + 1]
                - prefix[r1][c2 + 1]
                - prefix[r2 + 1][c1]
                + prefix[r1][c1];
    }
}
