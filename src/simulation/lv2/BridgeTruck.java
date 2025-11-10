package simulation.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/** 다리를 지나는 트럭 - 프로그래머스 42583
 *
 * 문제 유형 : Queue, Deque, 공간 시뮬레이션
 */
public class BridgeTruck {

    // 방법1. 고정 길이 Queue + 0 패딩
    // Deque(FIFO) 를 다리처럼 사용
    public int solution1(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> trucks = new ArrayDeque<>();
        Deque<Integer> bridge = new ArrayDeque<>();

        for(int w : truck_weights) {
            trucks.offer(w);
        }

        for(int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int sec = 0;
        int totalWeight = 0;

        while(!trucks.isEmpty()) {
            sec++;
            totalWeight -= bridge.poll(); // 트럭 1개 또는 0(빈값) 다리 건넘

            // 제한 무게 이내일 경우 트럭
            if(totalWeight + trucks.peek() <= weight) {
                int truck = trucks.poll();
                bridge.offer(truck);
                totalWeight += truck;
            } else { // 제한 무게 넘었을 경우 0(빈 값)
                bridge.offer(0);
            }
        }

        // 총 시간 + 다리위에 있는 트럭 이동 시간(다리 길이)
        return sec + bridge_length;
    }


    // 방법2. Truck 객체 - 추천
    static class Truck {
        int weight;
        int exitTime;

        Truck(int weight, int exitTime) {
            this.weight = weight;
            this.exitTime = exitTime;
        }
    }

    public int solution2(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> bridge = new ArrayDeque<>();
        int sec = 0;
        int idx = 0;
        int totalW = 0;

        // 대기중이거나 다리 위에 있는 트럭 이동
        while(idx < truck_weights.length || !bridge.isEmpty()) {
            sec++;

            // 다리 지난 트럭 제거
            if(!bridge.isEmpty() && bridge.peek().exitTime == sec) {
                totalW -= bridge.poll().weight;
            }

            // 제한 무게 이내인 경우 새 트럭 투입
            if(idx < truck_weights.length &&
                totalW + truck_weights[idx] <= weight) {

                int truckW = truck_weights[idx++];
                totalW += truckW;
                bridge.offer(new Truck(truckW, sec + bridge_length));
            }
        }

        return sec;
    }
}
