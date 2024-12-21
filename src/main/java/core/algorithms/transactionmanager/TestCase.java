package core.algorithms.transactionmanager;

import core.algorithms.transactionmanager.treemapimpl.DataCommand;

public class TestCase {

    public static void case1() {
        DataCommand command = new DataCommand();
        command.set("a", 10);
        System.out.println(command.get("a")); // 10
        command.delete("a");
        System.out.println(command.get("a")); // NULL
    }

    public static void case2() {
        DataCommand command = new DataCommand();
        command.set("a", 10);
        command.set("b", 10);
        command.count(10); // 2

        command.count(20); // 0

        command.delete("a");
        command.count(10); // 1

        command.set("b", 30);
        command.count(10); // 0
    }

    public static void case3() {
        DataCommand command = new DataCommand();
        command.begin();
        command.set("a", 10);
        System.out.println(command.get("a")); // 10

        command.begin();
        command.set("a", 20);
        System.out.println(command.get("a")); // 20

        command.rollback();
        System.out.println(command.get("a")); // 10

        command.rollback();
        System.out.println(command.get("a")); // NULL
    }

    public static void case4() {
        DataCommand command = new DataCommand();
        command.begin();
        command.set("a", 30);
        command.begin();
        command.set("a", 40);
        command.commit();
        System.out.println(command.get("a")); // 40
        command.rollback(); // NO TRANSACTION
    }

    public static void case5() {
        DataCommand command = new DataCommand();
        command.set("a", 50);
        command.begin();
        System.out.println(command.get("a")); // 50

        command.set("a", 60);
        command.begin();
        command.delete("a");
        System.out.println(command.get("a")); // NULL

        command.rollback();
        System.out.println(command.get("a")); // 60

        command.commit();
        System.out.println(command.get("a")); // 60
    }

    public static void case6() {
        DataCommand command = new DataCommand();
        command.set("a", 10);
        command.begin();
        command.count(10); // 1

        command.begin();
        command.delete("a");
        command.count(10); // 0

        command.rollback();
        command.count(10); // 1
    }
}
