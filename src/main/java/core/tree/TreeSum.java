package core.tree;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class TreeSum {

    public static void calculateSumOfTreeNodes() {
        Tree root = new Tree(5);
        root.insert(new Tree(3));
        root.insert(new Tree(8));
        root.insert(new Tree(2));
        root.insert(new Tree(4));
        root.insert(new Tree(10));

        System.out.println(root.sum());
    }

    public static class Tree {
        private List<Tree> children;
        @Getter
        private final int key;

        public Tree(int key) {
            this.key = key;
        }

        public void insert(Tree subTree) {
            if (children != null) {
                List<Tree> proxyChildren = new ArrayList<>(children);

                for (Tree child : proxyChildren) {
                    if ((subTree.getKey() < key && child.getKey() < key) || (subTree.getKey() > key && child.getKey() > key)) {
                        child.insert(subTree);
                    }
                    else if (children.size() < 2) {
                        children.add(subTree);
                    }
                }
            }
            else {
                children = new ArrayList<>();
                children.add(subTree);
            }
        }

        //сумма всех узлов дерева
        public int sum() {
            int total = 0;

            if (children != null) {
                for (Tree child : children) {
                    total += child.sum();
                }
            }

            return key + total;
        }
    }
}
