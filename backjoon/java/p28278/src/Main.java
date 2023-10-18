import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedListStack stack = new LinkedListStack();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().split(" ")[0]);
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int cmd = Integer.parseInt(input[0]);
            switch (cmd) {
                case 1:
                    int integer = Integer.parseInt(input[1]);
                    stack.push(integer);
                    break;
                case 2:
                    sb.append(stack.pop() + "\n");
                    break;
                case 3:
                    sb.append(stack.size() + "\n");
                    break;
                case 4:
                    sb.append(stack.isEmpty() + "\n");
                    break;
                case 5:
                    sb.append(stack.top() + "\n");
                    break;
            }
        }

        System.out.print(sb.toString());

        br.close();
    }

    static class LList {
        static class Node {
            private int data;
            private Node next;
            private Node prev;
        }

        private Node head;
        private Node tail;
        private int size;

        public LList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            head.prev = null;
            tail.next = null;
            tail.prev = head;
            size = 0;
        }

        public void add(int data) {
            Node newNode = new Node();
            newNode.data = data;
            newNode.next = tail;
            newNode.prev = tail.prev;
            tail.prev = newNode;
            this.size++;
        }

        public int removeLast() {
            if (this.size == 0) {
                return -1;
            }
            int dat = this.tail.prev.data;
            Node toRemove = this.tail.prev;
            this.tail.prev = toRemove.prev;
            toRemove.prev.next = this.tail;
            this.size--;
            return dat;
        }

        public int size() {
            return this.size;
        }

        public int getLast() {
            return this.isEmpty() == 1 ? -1 : this.tail.prev.data;
        }

        public int isEmpty() {
            return this.size == 0 ? 1 : 0;
        }
    }

    static class LinkedListStack {
        LList list;

        public LinkedListStack() {
            this.list = new LList();
        }

        public void push(int data) {
            this.list.add(data);
        }

        public int pop() {
            return this.list.removeLast();
        }

        public int top() {
            return this.list.getLast();
        }

        public int size() {
            return this.list.size();
        }

        public int isEmpty() {
            return this.list.isEmpty();
        }
    }
}
