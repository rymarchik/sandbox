//package core;
//
//import core.sort.Tree;
//
//public class Main {
//
//    public static void main(String[] args) {
//        int[] arr = {3, 6, 1, 5, 2, 8};
//
////        bubble(arr);
////        insertion(arr);
////        selection(arr);
//
////        System.out.println(Arrays.toString(arr));
////        System.out.println(Arrays.toString(merge(arr)));
//
//        Tree myTree = new Tree(7);      // создать дерево (с ключом)
//        myTree.insert(new Tree(5));     // присоединять поддеревья
//        myTree.insert(new Tree(9));
//
//        //если хочу статик метод в интерфейсе, то соответственно передавать визитер в метод не нужно
////        myTree.traverse();
//
//        //если хочу дефолтный метод в интерфейсе, то передаю инстанс интерфейса без реализации метода
////        myTree.traverse(new TreeVisitor() {});
//
//        //если хочу нереализованный метод в интерфейсе, то нужно бахнуть лямбду
//        //(если будет >1 абстрактных методов в интерфейсе, то он перестанет быть функциональным, и через лямбду уже не получится)
//        myTree.traverse(node -> System.out.println(" " + node.getKey()));
//    }
//}