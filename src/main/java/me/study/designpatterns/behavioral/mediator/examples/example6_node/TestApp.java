package me.study.designpatterns.behavioral.mediator.examples.example6_node;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        ConsoleUtil.Title("Before");
        new Before().run();
        ConsoleUtil.Title("After");
        new After().run();
    }
}

class Before {
    public void run() {
        Node lst = new Node(11);
        Node two = new Node(22);
        Node thr = new Node(33);
        Node fou = new Node(44);

        lst.addNode(two);
        lst.addNode(thr);
        lst.addNode(fou);

        lst.traverse();
        lst.removeNode(44);
        lst.traverse();
        lst.removeNode(22);
        lst.traverse();
        lst.removeNode(11);
        lst.traverse();
    }

    class Node {
        private int val;
        private Node next;

        public Node(int v) {
            this.val = v;
            this.next = null;
        }

        public void addNode(Node n) {
            if (next != null) {
                next.addNode(n);
            } else {
                next = n;
            }
        }

        public void traverse() {
            System.out.print(val + "  ");
            if (next != null) {
                next.traverse();
            } else {
                System.out.println();
            }
        }

        public void removeNode(int v) {
            if (next != null) {
                if (next.val == v) {
                    next = next.next;
                } else {
                    next.removeNode(v);
                }
            }
        }
    }
}

class After {

    public void run() {
        List lst = new List();
        Node one = new Node(11);
        Node two = new Node(22);
        Node thr = new Node(33);
        Node fou = new Node(44);
        lst.addNode(one);
        lst.addNode(two);
        lst.addNode(thr);
        lst.addNode(fou);
        lst.traverse();
        lst.removeNode(44);
        lst.traverse();
        lst.removeNode(22);
        lst.traverse();
        lst.removeNode(11);
        lst.traverse();
    }

    class Node {
        private int m_val;

        public Node(int v) {
            m_val = v;
        }

        public int getVal() {
            return m_val;
        }
    }

    class List {
        private ArrayList<Node> m_arr = new ArrayList<>();

        public void addNode(Node n) {
            m_arr.add(n);
        }

        public void traverse() {
            for (Node node : m_arr) {
                System.out.print(node.getVal() + "  ");
            }
            System.out.println();
        }

        public void removeNode(int v) {
            Iterator<Node> it = m_arr.iterator();
            while (it.hasNext()) {
                if (it.next().getVal() == v) {
                    it.remove();
                    break;
                }
            }
        }
    }

}