package simulation.lv2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 캐시 - 프로그래머스 17680
 *
 * 문제 유형 : Simulation, 구현, ArrayList, LinkedHashMap
 */
public class CacheSimulation {

    // 1. ArrayList - 추천
    // 코딩테스트에선 List 풀이가 가장 흔함
    public int solution1(int cacheSize, String[] cities) {
        // 캐시 사이즈가 0일 경우 항상 miss -> 도시 개수 * 5
        if(cacheSize == 0) return cities.length * 5;

        List<String> cache= new ArrayList<>();
        int time = 0;

        for(String city : cities) {
            city = city.toLowerCase();

            if(cache.remove(city)) { // 도시가 기존 캐시에 있으면 hit (제거 및 + 1)
                time += 1;
            } else { // 도시가 기존 캐시에 없으면 miss (+5)
                if(cache.size() >= cacheSize) { // 캐시 사이즈 이상일 경우 오래된 원소 제거
                    cache.remove(0);
                }
                time += 5;
            }
            // 도시 캐시에 추가
            cache.add(city);
        }

        return time;
    }



    // 2. LinkedHashMap
    // 코테에서는 지금 문제 크기에 비해 약간 과함
    public int solution2(int cacheSize, String[] cities) {
        // 캐시 크기가 0이면 무조건 miss
        if (cacheSize == 0) return cities.length * 5;

        // LinkedHashMap 사용 (순서 유지)
        // key: 도시명, value: 의미 없는 값 (더미)
        Map<String, Integer> cache = new LinkedHashMap<>();

        int time = 0;

        for (String city : cities) {
            city = city.toLowerCase();

            // cache hit: 이미 존재하는 경우 (제거 및 삽입 + 1)
            if (cache.containsKey(city)) {
                cache.remove(city);
                cache.put(city, 1);
                time += 1;

            } else {
                // 캐시가 꽉 찼으면 가장 오래된 것 제거
                if (cache.size() >= cacheSize) {
                    String oldest = cache.keySet().iterator().next();
                    cache.remove(oldest);
                }

                // cache miss : 새 데이터 추가 + 5
                cache.put(city, 1);
                time += 5;
            }
        }

        return time;
    }
}
