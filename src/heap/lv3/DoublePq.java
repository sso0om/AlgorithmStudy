package heap.lv3;

import java.util.*;

/** 이중우선순위큐 - 프로그래머스 42628
 *
 * 문제 유형 : Heap, PriorityQueue, HashMap, Lazy Deletion, TreeMap
 */
public class DoublePq {

    // 1. PriorityQueue 2 + HashMap
    // - Lazy Deletion 패턴
    public int[] solution1(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> countMap = new HashMap<>(); // 실제 살아있는 값 개수 관리
        int size = 0; // 현재 유효 데이터 개수

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);

            // 삽입
            if (command.equals("I")) {
                minHeap.offer(num);
                maxHeap.offer(num);

                // 개수 증가
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                size++;
                continue;
            }

            // // 비어있으면 삭제 무시
            if (size == 0) continue;

            // 1 : 최대값 삭제, -1 : 최솟값 삭제
            if (num == 1) {
                deleteValidValue(maxHeap, countMap);
            } else {
                deleteValidValue(minHeap, countMap);
            }

            size--;
        }

        // 큐가 비어있으면 [0, 0] 반환
        if (size == 0) return new int[]{0, 0};

        // 큐가 비어있지 않으면 [최댓값, 최솟값] 반환
        int max = peekValidValue(maxHeap, countMap);
        int min = peekValidValue(minHeap, countMap);

        return new int[]{max, min};
    }

    // 최대값/최솟값 삭제
    private void deleteValidValue(
        PriorityQueue<Integer> heap,
        Map<Integer, Integer> countMap
    ) {
        while (!heap.isEmpty()) {
            int value = heap.poll();

            // 유효성 체크 후 삭제
            if (countMap.getOrDefault(value, 0) > 0) {
                countMap.put(value, countMap.get(value) - 1);
                return;
            }
            // 이미 삭제된 값이면 그냥 버림 (skip)
        }
    }

    // 최대값/최솟값 찾기
    // 힙에 쓰레기 값 남아있을 수 있어서 정리 필요
    private int peekValidValue(
        PriorityQueue<Integer> heap,
        Map<Integer, Integer> countMap
    ) {
        while (!heap.isEmpty()) {
            int value = heap.peek();

            if (countMap.getOrDefault(value, 0) > 0) {
                return value;
            }

            // 이미 삭제된 값 → 제거
            heap.poll();
        }

        return 0;
    }



    // 2. TreeMap
    // - Java에서 TreeMap이 가장 깔끔한 구현
    // - 실수 적고, 코드 짧고, 설명 쉬움
    public int[] solution2(String[] operations) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(String operation : operations) {
            int num = Integer.parseInt(operation.substring(2));

            // 삽입 명령
            if(operation.charAt(0) == 'I') {
                treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                continue;
            }

            // 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시
            if(treeMap.isEmpty()) continue;

            // 1 : 최대값, -1 : 최솟값
            int key = num == 1 ? treeMap.lastKey() : treeMap.firstKey();
            int val = treeMap.get(key);

            // 1개면 map에서 제거, 2개 이상이면 개수 차감
            if(val == 1) {
                treeMap.remove(key);
            } else {
                treeMap.put(key, val - 1);
            }
        }

        // 큐가 비어있으면 [0, 0] 반환, 비어있지 않으면 [최댓값, 최솟값] 반환
        return treeMap.isEmpty()
            ? new int[] {0, 0}
            : new int[] {treeMap.lastKey(), treeMap.firstKey()};
    }
}
