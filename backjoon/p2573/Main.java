import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = firstLine[0];
        int m = firstLine[1];

        int[][] icebergs = new int[n][];
        for (int i = 0; i < n; i++) {
            icebergs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = new Main().solution(icebergs);
        System.out.println(answer);

        br.close();
    }

    int solution(int[][] icebergs) {
        Map<Coord, Integer> icebergsMap = new HashMap<>();
        for (int i = 0; i < icebergs.length; i++) {
            for (int j = 0; j < icebergs[i].length; j++) {
                if (icebergs[i][j] != 0) {
                    icebergsMap.put(new Coord(i, j), icebergs[i][j]);
                }
            }
        }

        int count = 0;
        while (true) {
            int chunkCount = getChunks(icebergsMap);
            if (chunkCount >= 2) {
                return count;
            }
            if (chunkCount == 0) {
                return 0;
            }

            decr(icebergsMap);
            count++;
        }
    }

    int getChunks(Map<Coord, Integer> icebergs) {
        Set<Coord> visited = new HashSet<>();

        int count = 0;
        for (Map.Entry<Coord, Integer> entry : icebergs.entrySet()) {
            Coord coord = entry.getKey();
            if (visited.contains(coord)) {
                continue;
            }
            count++;
            dfs(coord, icebergs, visited);
        }

        return count;
    }

    void dfs(Coord coord, Map<Coord, Integer> icebergs, Set<Coord> visited) {
        if (visited.contains(coord)) {
            return;
        }

        visited.add(coord);

        for (int i = 0; i < 4; i++) {
            int nextRow = coord.row + (i - 1) % 2;
            int nextCol = coord.col + (i - 2) % 2;

            Coord nextCoord = new Coord(nextRow, nextCol);
            if (icebergs.containsKey(nextCoord)) {
                dfs(nextCoord, icebergs, visited);
            }
        }
    }

    static class Coord {
        final int row;
        final int col;

        Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof Coord) {
                Coord target = (Coord) obj;
                return this.row == target.row
                        && this.col == target.col;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }

    void decr(Map<Coord, Integer> icebergs) {
        Map<Coord, Integer> temp = new HashMap<>();
        for (Map.Entry<Coord, Integer> entry : icebergs.entrySet()) {
            Coord coord = entry.getKey();
            int decrementCount = 0;
            for (int i = 0; i < 4; i++) {
                int adjacentRow = coord.row + (i - 2) % 2;
                int adjacentCol = coord.col + (i - 1) % 2;

                Coord adjacency = new Coord(adjacentRow, adjacentCol);
                if (!icebergs.containsKey(adjacency)) {
                    decrementCount++;
                }
            }
            temp.put(coord, decrementCount);
        }

        for (Map.Entry<Coord, Integer> entry : temp.entrySet()) {
            Integer previousValue = icebergs.get(entry.getKey());
            int to = previousValue - entry.getValue();

            if (to <= 0) {
                icebergs.remove(entry.getKey());
            } else {
                icebergs.put(entry.getKey(), to);
            }
        }
    }
}