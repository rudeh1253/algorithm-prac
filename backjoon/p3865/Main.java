import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        List<List<String>> list = new ArrayList<>();
        while (!(line = br.readLine().trim()).equals("0")) {
            int testcaseCount = Integer.parseInt(line);
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < testcaseCount; i++) {
                lines.add(br.readLine());
            }
            list.add(lines);
        }

        String[] answer = solution(list);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (String a : answer) {
            bw.write(a);
            bw.newLine();
        }
        bw.flush();

        br.close();
        bw.close();
    }

    static String[] solution(List<List<String>> input) {
        return input.stream()
                .map(Main::singleTest)
                .map(String::valueOf)
                .toArray(String[]::new);
    }

    static int singleTest(List<String> input) {
        String target = input.get(0).split(":")[0];

        Map<String, String[]> membersByConference = new HashMap<>();

        for (String i : input) {
            String[] firstSplit = i.split(":");
            String[] secondSplit = firstSplit[1].substring(0, firstSplit[1].length() - 1).split(",");
            membersByConference.put(firstSplit[0], secondSplit);
        }

        return dfs(target, membersByConference, new HashSet<>());
    }

    static int dfs(String node, Map<String, String[]> membersByConference, Set<String> visited) {
        if (visited.contains(node)) {
            return 0;
        }
        visited.add(node);

        if (!membersByConference.containsKey(node)) {
            return 1;
        }

        return Arrays.stream(membersByConference.get(node))
                .map((e) -> dfs(e, membersByConference, visited))
                .reduce(0, Integer::sum, Integer::sum);
    }
}