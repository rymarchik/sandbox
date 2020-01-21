package sort;

public class Tree {

    private Tree left;            // левое и правое поддеревья и ключ
    private Tree right;
    private int key;

    public Tree(int key) {        // конструктор с инициализацией ключа
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    /*  insert (добавление нового поддерева (ключа))
        сравнить ключ добавляемого поддерева (К) с ключом корневого узла (X).
        Если K>=X, рекурсивно добавить новое дерево в правое поддерево.
        Если K<X, рекурсивно добавить новое дерево в левое поддерево.
        Если поддерева нет, то вставить на это место новое дерево
    */
    public void insert(Tree subTree) {
        if (subTree.getKey() < key) {
            if (left != null) {
                left.insert(subTree);
            }
            else {
                left = subTree;
            }
        }
        else if (right != null) {
            right.insert(subTree);
        }
        else {
            right = subTree;
        }
    }

    /*  traverse (обход)
        Рекурсивно обойти левое поддерево.
        Применить функцию f (печать) к корневому узлу.
        Рекурсивно обойти правое поддерево.
    */
    public void traverse(TreeVisitor visitor) {
        if (left != null) {
            left.traverse(visitor);
        }

        visitor.visit(this);

        if (right != null) {
            right.traverse(visitor);
        }
    }
}
