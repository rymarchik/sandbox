package core.sort;

import java.util.ArrayList;
import java.util.List;

public class RandomTree {

    private List<RandomTree> children;
    private int key;

    public RandomTree(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void insert(RandomTree subTree) {
        if (children != null) {
            List<RandomTree> proxyChildren = new ArrayList<>(children);

            for (RandomTree child : proxyChildren) {
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
            for (RandomTree child : children) {
                total += child.sum();
            }
        }

        return key + total;
    }
}
