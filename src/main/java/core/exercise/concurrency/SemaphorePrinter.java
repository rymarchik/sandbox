package core.exercise.concurrency;

import java.util.concurrent.Semaphore;

public final class SemaphorePrinter {

    private final Semaphore semA = new Semaphore(1);
    private final Semaphore semB = new Semaphore(0);

    public void printA() {
        for (int i = 1; i <= 5; i+=2) {
            semA.acquireUninterruptibly();
            System.out.println("A: " + i);
            semB.release();
        }
    }

    public void printB() {
        for (int i = 2; i <= 5; i+=2) {
            semB.acquireUninterruptibly();
            System.out.println("B: " + i);
            semA.release();
        }
    }

    public static void test() throws InterruptedException {
        var p = new SemaphorePrinter();
        Thread t1 = Thread.ofVirtual().start(p::printA);
        Thread t2 = Thread.ofVirtual().start(p::printB);
        t1.join();
        t2.join();
    }
}
