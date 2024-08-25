package me.study.designpatterns.behavioral.command.real_world.example1_jdk_threads_runnable;

import me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.delegate.GUI;

public class TestApp {
    public static void main(String[] args) {
        var run1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("run1");
            }
        };
        Thread thread1 = new Thread(run1);
        thread1.run();

        var run2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("run2");
            }
        };
        Thread thread2 = new Thread(run2);
        thread2.run();
    }
}
