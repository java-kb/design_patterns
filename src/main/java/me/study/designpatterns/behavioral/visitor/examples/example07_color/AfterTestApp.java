package me.study.designpatterns.behavioral.visitor.examples.example07_color;

public class AfterTestApp {
    public static void main(String[] args) {
        Color[] set = { new Red(), new Blu(), new Blu(), new Red(), new Red() };
        CountVisitor countOperation = new CountVisitor();
        CallVisitor callOperation = new CallVisitor();

        for (Color color : set) {
            color.accept(countOperation);
            color.accept(callOperation);
        }

        countOperation.reportNum();
    }

    static interface Visitor {
        void visit(Red red);

        void visit(Blu blu);
    }

    static class Color {
        public void accept(Visitor visitor) {
        }
    }

    static class Red extends Color {
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public void eye() {
            System.out.println("Red::eye");
        }
    }

    static class Blu extends Color {
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public void sky() {
            System.out.println("Blu::sky");
        }
    }

    static class CountVisitor implements Visitor {
        private int num_red;
        private int num_blu;

        public CountVisitor() {
            num_red = 0;
            num_blu = 0;
        }

        public void visit(Red red) {
            num_red++;
        }

        public void visit(Blu blu) {
            num_blu++;
        }

        public void reportNum() {
            System.out.println("Reds " + num_red + ", Blus " + num_blu);
        }
    }

    static class CallVisitor implements Visitor {
        public void visit(Red r) {
            r.eye();
        }

        public void visit(Blu b) {
            b.sky();
        }
    }

}
