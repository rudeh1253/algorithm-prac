import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        solve1();
    }

    private static void output(String line) {
        System.out.print(line);
    }

    public static void solve1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long x = sc.nextLong();
        long y = sc.nextLong();

        BinaryTree tree = new BinaryTree();
        long wellY = y;
        tree.add(wellY);
        long sumX = Math.abs(x);
        long sumY = 0;

        int upper = 0;
        int lower = 0;
        int equal = 1;

        output(wellY + " " + (sumX + sumY) + "\n");

        for (int i = 1; i < N; i++) {
            x = sc.nextLong();
            y = sc.nextLong();
            sumX += Math.abs(x);
            sumY += Math.abs(y - wellY);
            tree.add(y);

            if (wellY > y) {
                lower++;
            } else if (wellY < y) {
                upper++;
            } else {
                equal++;
            }

            while (lower >= equal + upper) {
                BinaryTreeNode predecessor = tree.getPredecessor(wellY);
                if (predecessor != null) {
                    long displacement = Math.abs(wellY - predecessor.item);
                    sumY = sumY - (displacement * lower) + (displacement * (upper + equal));
                    wellY = predecessor.item;
                    upper = upper + equal;
                    lower = lower - predecessor.numSame;
                    equal = predecessor.numSame;
                }
            }
            while (upper > equal + lower) {
                BinaryTreeNode successor = tree.getSuccessor(wellY);
                if (successor != null) {
                    long displacement = Math.abs(wellY - successor.item);
                    sumY = sumY - (displacement * upper) + (displacement * (lower + equal));
                    wellY = successor.item;
                    upper = upper - successor.numSame;
                    lower = lower + equal;
                    equal = successor.numSame;
                }
            }

            if (i != N - 1) {
                output(wellY + " " + (sumX + sumY) + "\n");
            } else {
                output(wellY + " " + (sumX + sumY));
            }
        }

        sc.close();
    }

    static class BinaryTree {
        BinaryTreeNode root;

        public BinaryTree() {
            this.root = null;
        }

        public void add(long item) {
            BinaryTreeNode newNode = new BinaryTreeNode(item);
            if (this.root == null) {
                this.root = newNode;
            } else {
                addHelper(this.root, newNode);
            }
        }

        private void addHelper(BinaryTreeNode superNode, BinaryTreeNode toAdd) {
            superNode.numOfItems++;
            if (superNode.item < toAdd.item) {
                if (superNode.right == null) {
                    superNode.right = toAdd;
                    toAdd.parent = superNode;
                } else {
                    addHelper(superNode.right, toAdd);
                }
            } else if (superNode.item == toAdd.item) {
                superNode.numSame++;
            } else {
                if (superNode.left == null) {
                    superNode.left = toAdd;
                    toAdd.parent = superNode;
                } else {
                    addHelper(superNode.left, toAdd);
                }
            }
            superNode.height = Math.max(superNode.left == null ? 0 : superNode.left.height,
                    superNode.right == null ? 0 : superNode.right.height) + 1;
        }

        public BinaryTreeNode getPredecessor(long item) {
            BinaryTreeNode node = this.get(item);
            return node.left != null ? getRightmost(node.left) : getPredecessorHelper(node);
        }

        public BinaryTreeNode getPredecessor(BinaryTreeNode node) {
            return node.left != null ? getRightmost(node.left) : getPredecessorHelper(node);
        }

        private BinaryTreeNode getRightmost(BinaryTreeNode node) {
            if (node.right == null) {
                return node;
            } else {
                return getRightmost(node.right);
            }
        }

        private BinaryTreeNode getPredecessorHelper(BinaryTreeNode node) {
            if (node.parent == null) {
                return null;
            } else if (node.parent.right == node) {
                return node.parent;
            } else {
                return getPredecessorHelper(node.parent);
            }
        }

        public BinaryTreeNode getSuccessor(long item) {
            BinaryTreeNode node = this.get(item);
            if (node.right != null) {
                return getLeftmost(node.right);
            } else {
                return getSuccessorHelper(node);
            }
        }

        public BinaryTreeNode getSuccessor(BinaryTreeNode node) {
            return node.right != null ? getLeftmost(node.right) : getSuccessorHelper(node);
        }

        private BinaryTreeNode getLeftmost(BinaryTreeNode node) {
            if (node.left == null) {
                return node;
            } else {
                return getLeftmost(node.left);
            }
        }

        private BinaryTreeNode getSuccessorHelper(BinaryTreeNode node) {
            if (node.parent == null) {
                return null;
            } else if (node.parent.left == node) {
                return node.parent;
            } else {
                return getSuccessorHelper(node.parent);
            }
        }

        public BinaryTreeNode get(long item) {
            return getHelper(this.root, item);
        }

        private BinaryTreeNode getHelper(BinaryTreeNode superNode, long item) {
            if (superNode.item < item) {
                if (superNode.right == null) {
                    return null;
                } else {
                    return getHelper(superNode.right, item);
                }
            } else if (superNode.item > item) {
                if (superNode.left == null) {
                    return null;
                } else {
                    return getHelper(superNode.left, item);
                }
            } else {
                return superNode;
            }
        }
    }

    static class BinaryTreeNode {
        long item;
        int height;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;
        int numOfItems;
        int numSame;

        public BinaryTreeNode(long item) {
            this.item = item;
            this.height = 1;
            this.numOfItems = 1;
            this.numSame = 1;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public String toString() {
            return "[ item=" + this.item + ", height=" + this.height + ", numSame=" + this.numSame + ", left="
                    + (this.left == null ? null : this.left.item)
                    + ", right=" + (this.right == null ? null : this.right.item) + ", parent="
                    + (this.parent == null ? null : this.parent.item) + " ]";
        }
    }
}
