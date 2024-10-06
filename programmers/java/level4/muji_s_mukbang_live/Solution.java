import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] foodTimes, long k) {
        PriorityQueue<Pair> pairs = new PriorityQueue<>((p1, p2) -> p1.foodTime - p2.foodTime);
        
        Pair max = new Pair(0, foodTimes[0]);
        for (int i = 0; i < foodTimes.length; i++) {
            Pair pair = new Pair(i, foodTimes[i]);
            pairs.add(pair);
            if (max.foodTime < pair.foodTime) {
                max = pair;
            }
        }
        
        boolean[] aliveFoods = new boolean[foodTimes.length];
        
        Arrays.fill(aliveFoods, true);
        
        return recur(pairs, k, aliveFoods, 0, max);
    }
    
    int recur(PriorityQueue<Pair> pairs, long k, boolean[] aliveFoods, int prevHeadFoodTime, Pair max) {
        if (pairs.isEmpty()) {
            return -1;
        }
        int size = pairs.size();
        Pair head = pairs.peek();
        
        if (size * (head.foodTime - prevHeadFoodTime) < k) {
            while (!pairs.isEmpty() && pairs.peek().foodTime == head.foodTime) {
                Pair top = pairs.poll();
                aliveFoods[top.idx] = false;
            }
            return recur(pairs, k - size * (head.foodTime - prevHeadFoodTime), aliveFoods, head.foodTime, max);
        } else if (size * (head.foodTime - prevHeadFoodTime) == k) {
            return head.foodTime == max.foodTime ? -1 : 1;
        }
        
        int nextFoodOrder = (int)(k % size);
        
        int count = 0;
        for (int i = 0; i < aliveFoods.length; i++) {
            if (aliveFoods[i]) {
                if (count == nextFoodOrder) {
                    return i + 1;
                }
                count++;
            }
        }
        return -1;
    }
    
    static class Pair {
        int idx;
        int foodTime;
        
        Pair(int idx, int foodTime) {
            this.idx = idx;
            this.foodTime = foodTime;
        }
    }
}
