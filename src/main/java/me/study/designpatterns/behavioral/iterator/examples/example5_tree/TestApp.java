package me.study.designpatterns.behavioral.iterator.examples.example5_tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>(1);
        Tree<Integer> tree2= new Tree<>(2);
        Tree<Integer> tree3 = new Tree<>(3);
        tree1.left = tree2;
        tree1.right = tree3;
        tree2.left =  new Tree<>(4);
        tree2.right =  new Tree<>(5);
        tree3.left =  new Tree<>(6);
        tree3.right =  new Tree<>(7);
        System.out.println("Tree traversal: Inorder");
        clientCode(tree1.iterator(Tree.IterationType.inOrder));

        System.out.println("\nTree traversal: Preorder");
        clientCode(tree1.iterator(Tree.IterationType.preOrder));

        System.out.println("\nTree traversal: Postorder");
        clientCode(tree1.iterator(Tree.IterationType.postOrder));
    }

    public static <T> void clientCode(Iterator<T> iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class Tree<T> {

    T value;
    Tree<T> left;
    Tree<T> right;

    public Tree(T value) {
        this.value = value;
    }

    interface Block<T> {
        void apply(T t);
    }

    enum IterationType {
        inOrder,
        preOrder,
        postOrder
    }

    public Iterator<T> iterator(IterationType type) {
        List<T> items = new ArrayList<>();
        switch (type) {
            case inOrder:
                inOrder(items::add);
                break;
            case preOrder:
                preOrder(items::add);
                break;
            case postOrder:
                postOrder(items::add);
                break;
        }
        return items.iterator();
    }

    private void inOrder(Block<T> body) {
        if (left != null)
            left.inOrder(body);
        body.apply(value);
        if (right != null)
            right.inOrder(body);
    }

    private void preOrder(Block<T> body) {
        body.apply(value);
        if (left != null)
            left.preOrder(body);
        if (right != null)
            right.preOrder(body);
    }

    private void postOrder(Block<T> body) {
        if (left != null)
            left.postOrder(body);
        if (right != null)
            right.postOrder(body);
        body.apply(value);
    }
}