package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.sort.RandomTree;
import core.sort.Tree;
import core.support.Utils;

import static core.sort.SortUtils.bubble;
import static core.sort.SortUtils.insertion;
import static core.sort.SortUtils.merge;
import static core.sort.SortUtils.selection;

public class Main {

    public static void main(String[] args) {
    }

    public static void testBubbleSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        bubble(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testInsertionSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        insertion(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testSelectionSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        selection(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void testMergeSort() {
        int[] arr = {3, 6, 1, 5, 2, 8};
        System.out.println(Arrays.toString(merge(arr)));
    }

    public static void testBinaryTree() {
        Tree myTree = new Tree(7);      // создать дерево (с ключом)
        myTree.insert(new Tree(5));     // присоединять поддеревья
        myTree.insert(new Tree(9));

        //если хочу статик метод в интерфейсе, то соответственно передавать визитер в метод не нужно
//        myTree.traverse();

        //если хочу дефолтный метод в интерфейсе, то передаю инстанс интерфейса без реализации метода
//        myTree.traverse(new TreeVisitor() {});

        //если хочу нереализованный метод в интерфейсе, то нужно бахнуть лямбду
        //(если будет >1 абстрактных методов в интерфейсе, то он перестанет быть функциональным, и через лямбду уже не получится)
        myTree.traverse(node -> System.out.println(" " + node.getKey()));
    }

    public static void testStreamMethods() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        //без терминального метода в консоль ничего не выведется
        integerList.stream()
            .peek(System.out::println)
            .count();

        //foreach является терминальным, поэтому все ок
        integerList.stream()
            .forEach(System.out::println);
    }

    public static void testPalindrome() {
        int num = 12345321;
        String str = "dad";
        System.out.println(Utils.checkIfPalindrome(num));
        System.out.println(Utils.checkIfPalindrome(str));
    }

    public static void calculateSumOfTreeNodes() {
        RandomTree root = new RandomTree(5);
        root.insert(new RandomTree(3));
        root.insert(new RandomTree(8));
        root.insert(new RandomTree(2));
        root.insert(new RandomTree(4));
        root.insert(new RandomTree(10));

        System.out.println(root.sum());
    }
}