package me.study.designpatterns.behavioral.visitor.examples.example07_color;

public class BeforeTestApp {
    public static void main(String[] args) {
        Color[] set = { new Red(), new Blu(), new Blu(), new Red(), new Red() };

        for (Color color : set) {
            color.count();
            color.call();
        }

        Color.reportNum();
    }
    static abstract class Color {
        public abstract void count();
    
        public abstract void call();
    
        static void reportNum() {
            System.out.println("Reds " + sNumRed + ", Blus " + sNumBlu);
        }
    
        protected static int sNumRed = 0;
        protected static int sNumBlu = 0;
    }
    
    static class Red extends Color {
        public void count() {
            ++sNumRed;
        }
    
        public void call() {
            eye();
        }
    
        private void eye() {
            System.out.println("Red::eye");
        }
    }
    
    static class Blu extends Color {
        public void count() {
            ++sNumBlu;
        }
    
        public void call() {
            sky();
        }
    
        private void sky() {
            System.out.println("Blu::sky");
        }
    }
    
}

