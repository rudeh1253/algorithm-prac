import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        LinkedListStack stack = new LinkedListStack();

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int cmd = sc.nextInt();
            switch (cmd) {
                case 1:
                    int integer = sc.nextInt();
                    stack.push(integer);
                    break;
                case 2:
                    System.out.println(stack.pop());
                    break;
                case 3:
                    System.out.println(stack.size());
                    break;
                case 4:
                    System.out.println(stack.isEmpty());
                    break;
                case 5:
                    System.out.println(stack.top());
                    break;
            }
        }

        sc.close();
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
