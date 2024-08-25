package me.study.designpatterns.structural.flyweight.examples.example05_gazillion;

public class TestApp {
    public static void main(String[] args) {
        Before.run();
        After.run();
    }
}

class Before {
    public static final int ROWS = 6, COLS = 10;

    static class Gazillion {
        private static int num = 0;
        private int row, col;

        public Gazillion(int maxPerRow) {
            row = num / maxPerRow;
            col = num % maxPerRow;
            num++;
        }

        void report() {
            System.out.print(" " + row + col);
        }
    }

    public static void run() {
        Gazillion[][] matrix = new Gazillion[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = new Gazillion(COLS);
            }
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j].report();
            }
            System.out.println();
        }
    }
}

class After {
    public static final int ROWS = 6, COLS = 10;
    static class Gazillion {
        private int row;
    
        public Gazillion(int row) {
            this.row = row;
            System.out.println("ctor: " + this.row);
        }
    
        void report(int col) {
            System.out.print(" " + row + col);
        }
    }
    
    static class Factory {
        private Gazillion[] pool;
    
        public Factory(int maxRows) {
            pool = new Gazillion[maxRows];
        }
    
        public Gazillion getFlyweight(int row) {
            if (pool[row] == null) {
                pool[row] = new Gazillion(row);
            }
            return pool[row];
        }
    }
    
    
    public static void run() {
        Factory theFactory = new Factory(ROWS);
        for (int i=0; i < ROWS; i++) {
            for (int j=0; j < COLS; j++)
                theFactory.getFlyweight(i).report(j);
            System.out.println();
        }
    }
    
}