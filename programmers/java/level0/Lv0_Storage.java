package level0;
import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/250126
public class Lv0_Storage {

    public String solution(String[] storage, int[] num) {
        List<String> cleanItems = new ArrayList<>();
        List<Integer> numOfItems = new ArrayList<>();

        String currentItem = null;
        int accumulator = 0;
        for (int i = 0; i < storage.length; i++) {
            String item = storage[i];
            int itemNum = num[i];
            if (item.equals(currentItem)) {
                accumulator += itemNum;
            } else {
                if (currentItem != null) {
                    cleanItems.add(currentItem);
                    numOfItems.add(accumulator);
                }
                currentItem = item;
                accumulator = itemNum;
            }
        }

        int maxIdx = -1;
        int max = 0;
        for (int i = 0; i < numOfItems.size(); i++) {
            if (numOfItems.get(i) > max) {
                max = numOfItems.get(i);
                maxIdx = i;
            }
        }
        return cleanItems.get(maxIdx);
    }
}