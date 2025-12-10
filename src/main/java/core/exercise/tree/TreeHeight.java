package core.exercise.tree;

public class TreeHeight {

    public static void case1() {
        Node leaf1 = new Node(null, null);
        Node leaf2 = new Node(null, null);
        Node node = new Node(leaf1, null);
        Node root = new Node(node, leaf2);
        System.out.println(root.height());
    }

    public static class Node {
        private final Node leftChild;
        private final Node rightChild;

        public Node(Node leftChild, Node rightChild) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int height() {
            // Если узел пустой (нет ни левого, ни правого ребенка), его высота — 0
            if (this.leftChild == null && this.rightChild == null) {
                return 0;
            }

            // Иначе считаем высоту рекурсивно
            return 1 + Math.max(
                leftChild != null ? leftChild.height() : 0,
                rightChild != null ? rightChild.height() : 0
            );
        }
    }
}
