package core.exercise.tree;

import lombok.Getter;

public class TreeTraverse {

    public static void testBinaryTree() {
        Tree myTree = new Tree(7);
        myTree.insert(new Tree(5));
        myTree.insert(new Tree(9));

        //если хочу статик метод в интерфейсе, то соответственно передавать визитер в метод не нужно
//        myTree.traverse();

        //если хочу дефолтный метод в интерфейсе, то передаю инстанс интерфейса без реализации метода
//        myTree.traverse(new TreeVisitor() {});

        //если хочу метод без реализации в интерфейсе, то нужно бахнуть лямбду
        //(если будет >1 абстрактных методов в интерфейсе, то он перестанет быть функциональным, и через лямбду уже не получится)
        myTree.traverse(node -> System.out.println(" " + node.getKey()));
    }

    public static class Tree {

        private Tree left;
        private Tree right;
        @Getter
        private final int key;

        public Tree(int key) {        // конструктор с инициализацией ключа
            this.key = key;
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

    public interface TreeVisitor {
//    static void visit(Tree node);

//    default void visit(Tree node) {
//        System.out.println(" " + node.getKey());
//    }

        void visit(Tree node);
    }
}

