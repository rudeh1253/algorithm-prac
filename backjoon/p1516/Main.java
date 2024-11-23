import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        Map<Integer, Integer> costPerBuilding = new HashMap<>();
        Map<Integer, List<Integer>> prerequisites = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = i + 1;
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            costPerBuilding.put(num, line[0]);
            int idx = 1;

            List<Integer> pr = new ArrayList<>(line.length - 2);
            while (line[idx] != -1) {
                pr.add(line[idx]);
                idx++;
            }
            prerequisites.put(num, pr);
        }

        int[] answer = solution(n, costPerBuilding, prerequisites);
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int a : answer) {
            bw.write(String.valueOf(a));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] solution(int n, Map<Integer, Integer> costPerBuilding, Map<Integer, List<Integer>> prerequisites) {
        Map<Integer, Integer> realCostPerBuilding = new HashMap<>();

        for (Integer key : costPerBuilding.keySet()) {
            realCostPerBuilding.put(key, prerequisites.get(key).isEmpty() ? costPerBuilding.get(key) : -1);
        }

        // return new int[] { realCostPerBuilding.get(1), realCostPerBuilding.get(2), realCostPerBuilding.get(3), realCostPerBuilding.get(4), realCostPerBuilding.get(5),  };

        // return Arrays.stream(new int[] { realCostPerBuilding.get(1), realCostPerBuilding.get(2), realCostPerBuilding.get(3), realCostPerBuilding.get(4), realCostPerBuilding.get(5),  })
        // .max();

        // return new int[] { prerequisites.get(4).get(1) } ;

        fillMap(realCostPerBuilding, costPerBuilding, prerequisites);

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = realCostPerBuilding.get(i + 1);
        }
        return result;
    }

    static void fillMap(Map<Integer, Integer> realCostPerBuilding, Map<Integer, Integer> costPerBuilding, Map<Integer, List<Integer>> prerequisites) {
        for (Integer building : costPerBuilding.keySet()) {
            recur(building, realCostPerBuilding, costPerBuilding, prerequisites);
        }
    }

    static Integer recur(Integer building, Map<Integer, Integer> realCostPerBuilding, Map<Integer, Integer> costPerBuilding, Map<Integer, List<Integer>> prerequisites) {
        Integer fromDpTable = realCostPerBuilding.get(building);
        if (!fromDpTable.equals(-1)) {
            return fromDpTable;
        }

        List<Integer> pr = prerequisites.get(building);
        List<Integer> returns = new ArrayList<>(pr.size());
        for (Integer requiredBuilding : pr) {
            returns.add(recur(requiredBuilding, realCostPerBuilding, costPerBuilding, prerequisites));
        }

        Integer max = returns.get(0);

        for (Integer r : returns) {
            max = Math.max(max, r);
        }

        Integer buildingTime = max + costPerBuilding.get(building);
        
        realCostPerBuilding.put(building, buildingTime);
        return buildingTime;
    }
}