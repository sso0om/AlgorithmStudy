package math.lv0;

/** 치킨 쿠폰 - 프로그래머스 120884
 *
 * 문제 유형 : 시뮬레이션
 */
public class ServiceChicken {

    // 1. 시뮬레이션 - 문제 의도에 부합
    private int solution1(int chicken) {
        int service = 0;
        while (chicken >= 10) {
            // 10마리당 서비스 치킨 1마리
            int extra = chicken / 10;
            service += extra;

            // 서비스 치킨 1 -> 쿠폰 1
            // 현 치킨 = 서비스 쿠폰 + 나머지 쿠폰
            chicken = extra + chicken % 10;
        }
        return service;
    }

    // 2. 공식 이용
    // 서비스 1마리 = 쿠폰 1장 → 9마리마다 1마리 생긴다 생각하면 되는 구조
    // (chicken - 1) : 처음은 10마리 필요하므로 - 1
    private int solution2(int chicken) {
        // 서비스 치킨 수 S = floor((chicken - 1) / 9)
        return chicken < 10 ? 0 : (chicken - 1) / 9;
    }
}
