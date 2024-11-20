import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int answer = solution(h, w, n, m);
        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int h, int w, int n, int m) {
        m++;
        n++;
        int inRow = w / m + (w % m == 0 ? 0 : 1);
        int inColumn = h / n + (h % n == 0 ? 0 : 1);
        return inColumn * inRow;
    }
}