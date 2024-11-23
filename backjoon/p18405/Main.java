import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLine[0];
        int k = firstLine[1];

        int[][] map = new int[n][];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] lastLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int s = lastLine[0];
        int x = lastLine[1];
        int y = lastLine[2];

        int answer = solution(n, k, map, s, x, y);
        System.out.println(answer);

        br.close();
    }

    static int solution(int n, int k, int[][] map, int s, int x, int y) {
        int[][] mapIter = map;
        for (int i = 0; i < s; i++) {
            mapIter = oneSecond(mapIter);
        }
        return mapIter[x - 1][y - 1];
    }

    static int[][] oneSecond(int[][] map) {
        int[][] toArray = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    to(map, toArray, i, j);
                } else {
                    toArray[i][j] = map[i][j];
                }
            }
        }
        return toArray;
    }

    static void to(int[][] map, int[][] toArray, int i, int j) {
        int min = 15000;

        for (int k = 0; k < 4; k++) {
            int vertical = i + ((k - 1) % 2);
            int horizontal = j + ((k - 2) % 2);

            if (vertical >= 0 && vertical < map.length && horizontal >= 0 && horizontal < map[i].length && map[vertical][horizontal] != 0) {
                min = Math.min(map[vertical][horizontal], min);
            }
        }

        if (min != 15000) {
            
            toArray[i][j] = min;
        }
    }
}