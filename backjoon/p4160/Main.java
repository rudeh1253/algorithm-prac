import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<int[]> testCases = new ArrayList<>();

        String testCaseHead;
        while (!(testCaseHead = br.readLine()).equals("0")) {
            int n = Integer.parseInt(testCaseHead);
            int[] houses = new int[n];
            for (int i = 0; i < n; i++) {
                houses[i] = Integer.parseInt(br.readLine());
            }
            testCases.add(houses);
        }

        br.close();

        int[] answer = solution(testCases);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i : answer) {
            bw.write(String.valueOf(i));
        }

        bw.flush();
        bw.close();
    }

    static int[] solution(List<int[]> testCases) {
        return testCases.stream()
                .mapToInt(Main::solutionPerTestCase)
                .toArray();
    }

    static int solutionPerTestCase(int[] houseValues) {
        int middleIdx = houseValues.length / 2;
        middleIdx = middleIdx >= 5 ? 5 : middleIdx;
        List<Integer> subList1 = new ArrayList<>(middleIdx);
        for (int i = 0; i < middleIdx; i++) {
            subList1.add(houseValues[i]);
        }
        List<Integer> subList2 = new ArrayList<>(middleIdx + houseValues.length % 2);
        for (int i = middleIdx; i < houseValues.length; i++) {
            subList2.add(houseValues[i]);
        }

        Map<Integer, Integer> table = new HashMap<>();
        fillMap(0, 0, 0, subList1, table, 0);

        return getMin(0, 0, 0, subList2, Collections.unmodifiableMap(table), 0);
    }

    static void fillMap(int jack, int jill, int toSell, List<Integer> leftHouses, Map<Integer, Integer> table, int leftHouseIdx) {
        if (leftHouses.size() == leftHouseIdx) {
            int subtraction = jack - jill;
            if (table.containsKey(subtraction)) {
                Integer previous = table.get(subtraction);
                if (previous > toSell) {
                    table.put(subtraction, toSell);
                }
            } else {
                table.put(subtraction, toSell);
            }
            return;
        }

        Integer house = leftHouses.get(leftHouseIdx);

        fillMap(jack + house, jill, toSell, leftHouses, table, leftHouseIdx + 1);
        fillMap(jack, jill + house, toSell, leftHouses, table, leftHouseIdx + 1);
        fillMap(jack, jill, toSell + house, leftHouses, table, leftHouseIdx + 1);
    }

    static int getMin(int jack, int jill, int toSell, List<Integer> leftHouses, Map<Integer, Integer> table, int leftHouseIdx) {
        if (leftHouses.size() == leftHouseIdx) {
            int subtraction = jill - jack;
            if (table.containsKey(subtraction)) {
                return toSell + table.get(subtraction);
            } else {
                return -1;
            }
        }

        Integer house = leftHouses.get(leftHouseIdx);

        int min = Integer.MAX_VALUE;

        int r1 = getMin(jack + house, jill, toSell, leftHouses, table, leftHouseIdx + 1);
        int r2 = getMin(jack, jill + house, toSell, leftHouses, table, leftHouseIdx + 1);
        int r3 = getMin(jack, jill, toSell + house, leftHouses, table, leftHouseIdx + 1);

        if (min > r1 && r1 != -1) {
            min = r1;
        }

        if (min > r2 && r2 != -1) {
            min = r2;
        }

        if (min > r3 && r3 != -1) {
            min = r3;
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}