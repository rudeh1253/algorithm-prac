import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLine[0];
        int m = firstLine[1];

        int[] secondLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] knowingTruth = Arrays.copyOfRange(secondLine, 1, secondLine.length);

        int[][] partyPeople = new int[m][];
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            partyPeople[i] = Arrays.copyOfRange(line, 1, line.length);
        }

        int answer = solution(n, knowingTruth, partyPeople);
        System.out.println(answer);

        br.close();
    }

    static int solution(int n, int[] knowingTruth, int[][] partyPeople) {
        Set<Integer> knowingTruthSet = new HashSet<>();
        for (int k : knowingTruth) {
            knowingTruthSet.add(k);
        }

        Map<Integer, Set<Integer>> knows = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            knows.put(i, new HashSet<>());
        }

        for (int[] pp : partyPeople) {
            Set<Integer> s = new HashSet<>();
            for (int p : pp) {
                s.add(p);
            }

            for (int p : pp) {
                knows.get(p).addAll(s);
            }
        }

        for (Map.Entry<Integer, Set<Integer>> entry : knows.entrySet()) {
            entry.getValue().remove(entry.getKey());
        }

        int count = 0;
        for (int[] pp : partyPeople) {
            boolean canLie = true;
            for (int p : pp) {
                canLie = canLie && canLie(p, knows, knowingTruthSet, new HashSet<>());
            }

            if (canLie) {
                count++;
            }
        }
        return count;
    }

    static boolean canLie(Integer node, Map<Integer, Set<Integer>> knows, Set<Integer> knowingTruthSet, Set<Integer> visited) {
        if (visited.contains(node)) {
            return true;
        }

        visited.add(node);

        if (knowingTruthSet.contains(node)) {
            return false;
        }

        boolean canLie = true;
        for (Integer adj : knows.get(node)) {
            canLie = canLie && canLie(adj, knows, knowingTruthSet, visited);
        }
        return canLie;
    }
}