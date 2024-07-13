import java.util.HashMap;
import java.util.Map;

public class RunningRace {
    
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> nameIndexed = new HashMap<>();
        Map<Integer, String> rankIndexed = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            nameIndexed.put(players[i], i);
            rankIndexed.put(i, players[i]);
        }

        for (String call : callings) {
            int rank = nameIndexed.get(call);
            String next = rankIndexed.get(rank - 1);
            nameIndexed.put(next, rank);
            nameIndexed.put(call, rank - 1);
            rankIndexed.put(rank, next);
            rankIndexed.put(rank - 1, call);
        }

        String[] answer = new String[nameIndexed.size()];
        nameIndexed.forEach((k, v) -> {
            answer[v] = k;
        });
        return answer;
    }
}
