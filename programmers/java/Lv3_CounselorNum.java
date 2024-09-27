import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/214288
 */
class Lv3_CounselorNum {
    
    public int solution(int k, int n, int[][] reqs) {
        int[] categories = new int[k];
        Arrays.fill(categories, 1);
        
        for (int i = k + 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int j = 0; j < k; j++) {
                int[] copied = Arrays.copyOf(categories, categories.length);
                copied[j]++;
                int result = getWaitTime(copied, reqs);
                if (result < min) {
                    min = result;
                    minIdx = j;
                }
            }
            categories[minIdx]++;
        }
        
        return getWaitTime(categories, reqs);
    }
    
    static int getWaitTime(int[] categories, int[][] reqs) {
        int[] copy = new int[categories.length];
        for (int i = 0; i < categories.length; i++) {
            copy[i] = categories[i];
        }
        
        PriorityQueue<Integer>[] pqs = new PriorityQueue[categories.length];
        for (int i = 0; i < pqs.length; i++) {
            pqs[i] = new PriorityQueue<>();
        }
        
        int waitTimeSum = 0;
        
        for (int[] req : reqs) {
            int time = req[0];
            int ellapseTime = req[1];
            int category = req[2] - 1;
            
            while (!pqs[category].isEmpty()) {
                if (time >= pqs[category].peek()) {
                    pqs[category].poll();
                } else {
                    break;
                }
            }
            
            if (pqs[category].size() < categories[category]) {
                pqs[category].add(time + ellapseTime);
            } else {
                List<Integer> cache = new ArrayList<>();
                
                while (pqs[category].size() > categories[category]) {
                    cache.add(pqs[category].poll());
                }
                
                waitTimeSum += pqs[category].peek() - time;
                pqs[category].add(pqs[category].peek() + ellapseTime);
                
                for (Integer c : cache) {
                    pqs[category].add(c);
                }
            }
        }
        return waitTimeSum;
    }
}