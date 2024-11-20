import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLine[0];
        int l = firstLine[1];

        int[][] map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = solution(n, l, map);
        System.out.println(answer);

        br.close();
    }

    static int solution(int n, int l, int[][] map) {
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            boolean can = true;
            int lastDescending = -1;    
            for (int j = 0; j < n; j++) {
                if (j + 1 < n
                        && map[i][j + 1] != map[i][j]) {
                    if (map[i][j + 1] == map[i][j] + 1) {
                        for (int k = j; k > j - l; k--) {
                            if (k < 0 || map[i][k] != map[i][j] || lastDescending >= k) {
                                can = false;
                                break;
                            }
                        }
                    } else if (map[i][j + 1] == map[i][j] - 1) {
                        for (int k = j + 1; k <= j + l; k++) {
                            if (k >= n || map[i][k] != map[i][j + 1]) {
                                can = false;
                                break;
                            }
                            lastDescending = k;
                        }
                    } else {
                        can = false;
                    }
                    if (!can) {
                        break;
                    }
                }
            }
            if (can) {
                count++;
            }
        }

        for (int i = 0; i < n; i++) {
            boolean can = true;
            int lastDescending = -1;    
            for (int j = 0; j < n; j++) {
                if (j + 1 < n
                        && map[j + 1][i] != map[j][i]) {
                    if (map[j + 1][i] == map[j][i] + 1) {
                        for (int k = j; k > j - l; k--) {
                            if (k < 0 || map[k][i] != map[j][i] || lastDescending >= k) {
                                can = false;
                                break;
                            }
                        }
                    } else if (map[j + 1][i] == map[j][i] - 1) {
                        for (int k = j + 1; k <= j + l; k++) {
                            if (k >= n || map[k][i] != map[j + 1][i]) {
                                can = false;
                                break;
                            }
                            lastDescending = k;
                        }
                    } else {
                        can = false;
                    }
                    if (!can) {
                        break;
                    }
                }
            }
            if (can) {
                count++;
            }
        }

        return count;
    }
}
