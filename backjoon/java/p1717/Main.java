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
        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLine[0];
        int m = firstLine[1];
        int[][] ops = new int[m][];
        for (int i = 0; i < m; i++) {
            ops[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<String> answer = solution(n, ops);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String a : answer) {
            bw.write(a);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static List<String> solution(int n, int[][] ops) {
        List<String> answer = new ArrayList<>(ops.length);
        Map<Integer, SetNode> sets = new HashMap<>();
        
        for (int i = 0; i <= n; i++) {
            sets.put(i, new SetNode(i));
        }

        for (int[] op : ops) {
            if (op[0] == 0) {
                SetNode pointer1 = sets.get(op[1]);
                SetNode pointer2 = sets.get(op[2]);

                if (pointer1.equals(pointer2)) {
                    continue;
                }

                SetNode ans1 = pointer1.getAncestor();
                SetNode ans2 = pointer2.getAncestor();

                if (ans1.height > ans2.height) {
                    ans1.addNode(ans2);
                } else {
                    ans2.addNode(ans1);
                }
            } else {
                answer.add(
                    sets.get(op[1]).equals(sets.get(op[2])) ? "YES" : "NO"
                );
            }
        }
        return answer;
    }

    static class SetNode {
        Integer setNumber;
        SetNode parent;
        SetNode leftChild;
        SetNode rightChild;
        int height;

        SetNode(Integer setNumber) {
            this.setNumber = setNumber;
            this.height = 1;
        }

        public void addNode(SetNode node) {
            int leftHeight = this.leftChild == null ? 0 : this.leftChild.height;
            int rightHeight = this.rightChild == null ? 0 : this.rightChild.height;

            node.parent = this;
            if (leftHeight < rightHeight) {
                if (this.leftChild == null) {
                    this.leftChild = node;
                } else {
                    this.leftChild.addNode(node);
                }
            } else {
                if (this.rightChild == null) {
                    this.rightChild = node;
                } else {
                    this.rightChild.addNode(node);
                }
            }
            leftHeight = this.leftChild == null ? 0 : this.leftChild.height;
            rightHeight = this.rightChild == null ? 0 : this.rightChild.height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        @Override
        public boolean equals(Object obj) {
            SetNode target = (SetNode)obj;

            SetNode ans1 = getAncestor();
            SetNode ans2 = target.getAncestor();
            return ans1.setNumber.equals(ans2.setNumber);
        }

        public SetNode getAncestor() {
            if (this.parent != null) {
                return this.parent.getAncestor();
            }
            return this;
        }
    }
}
