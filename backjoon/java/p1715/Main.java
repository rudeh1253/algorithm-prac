import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine().trim());
            pq.add(input);
        }

        int answer = 0;
        while (pq.size() != 1) {
            int num = pq.poll() + pq.poll();
            pq.add(num);
            answer += num;
        }

        System.out.println(answer);

        br.close();
    }
}
