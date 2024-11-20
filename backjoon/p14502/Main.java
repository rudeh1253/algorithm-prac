import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static BufferedReader br = null;
    static List<int[]> virusPoses = new ArrayList<>();
    static int spaceNum = 0;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int[] vertAndHort = getVertAndHort();
            solve(getInput(vertAndHort[0], vertAndHort[1]), vertAndHort[0], vertAndHort[1]);
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] getVertAndHort() throws IOException {
        String[] verticalAndHorizontal = br.readLine().split(" ");
        int vertical = Integer.parseInt(verticalAndHorizontal[0]);
        int horizontal = Integer.parseInt(verticalAndHorizontal[1]);
        return new int[] { vertical, horizontal };
    }

    static int[][] getInput(int vertical, int horizontal) throws IOException {
        int[][] spaceStates = new int[vertical][horizontal];

        for (int i = 0; i < vertical; i++)  {
            String[] stateOfSingleRow = br.readLine().split(" ");
            for (int j = 0; j < stateOfSingleRow.length; j++) {
                spaceStates[i][j] = Integer.parseInt(stateOfSingleRow[j]);
                if (spaceStates[i][j] == 2) {
                    virusPoses.add(new int[] { i, j });
                }
                if (spaceStates[i][j] == 0) {
                    spaceNum++;
                }
            }
        }
        return spaceStates;
    }

    static void solve(int[][] spaceStates, int vert, int hort) { 
        int total = vert * hort;
        int max = 0;
        for (int i = 0; i < total - 2; i++) {
            if (spaceStates[i / hort][i % hort] != 0) {
                continue;
            }
            spaceStates[i / hort][i % hort] = 1;
            for (int j = i + 1; j < total - 1; j++) {
                if (spaceStates[j / hort][j % hort] != 0) {
                    continue;
                }
                spaceStates[j / hort][j % hort] = 1;
                for (int k = j + 1; k < total; k++) {
                    if (spaceStates[k / hort][k % hort] != 0) {
                        continue;
                    }
                    spaceStates[k / hort][k % hort] = 1;
                    int result = solveSingleUnit(spaceStates, vert, hort);
                    if (result > max) {
                        max = result;
                    }
                    spaceStates[k / hort][k % hort] = 0;
                }
                spaceStates[j / hort][j % hort] = 0;
            }
            spaceStates[i / hort][i % hort] = 0;
        }
        System.out.println(max);
    }

    static int solveSingleUnit(int[][] spaceStates, int vert, int hort) {
        int[][] copy = Arrays.stream(spaceStates).map(int[]::clone).toArray(int[][]::new);
        for (int[] virusPos : virusPoses) {
            dfs(copy, virusPos[0], virusPos[1]);
        }
        return countSpaces(copy);
    }

    static void dfs(int[][] spaceStates, int row, int column) {
        spaceStates[row][column] = 2;
        try {
            if (spaceStates[row][column - 1] == 0) {
                dfs(spaceStates, row, column - 1);
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (spaceStates[row + 1][column] == 0) {
                dfs(spaceStates, row + 1, column);
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (spaceStates[row][column + 1] == 0) {
                dfs(spaceStates, row, column + 1);
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (spaceStates[row - 1][column] == 0) {
                dfs(spaceStates, row - 1, column);
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    static int countSpaces(int[][] spaceState) {
        int total = 0;
        for (int i = 0; i < spaceState.length; i++) {
            for (int j = 0; j < spaceState[i].length; j++) {
                if (spaceState[i][j] == 0) {
                    total++;
                }
            }
        }
        return total;
    }
}