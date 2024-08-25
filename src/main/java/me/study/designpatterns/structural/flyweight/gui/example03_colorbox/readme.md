https://sourcemaking.com/design_patterns/flyweight/java/3

# Before
heavyweight ColorBoxes  ==>  ColorBox Flyweights and a Factory 
 (1 thread per ColorBox)         of pooled HandlerThreads

Discussion. Creating a thread for each ColorBox is a much more straight- forward approach, but it doesn't scale when dozens of ColorBoxes are created. Sharing a "pool" of threads across the collection of ColorBoxes requires more thought to set-up, but does not saturate "system resources" like the former approach does.

In the implementation below, each ColorBox "wraps" itself with a Thread object. The Thread object provides all the "threading functionality magic" and simply calls ColorBox's run() method when it is promoted from the "ready" state to the "running" state. When each Thread/ColorBox is swapped into the CPU, it causes the ColorBox part of itself to change its color and then graciously gives up the CPU [by calling sleep()] so that other Threads may run.

In the ThreadPool implementation, after the ColorBoxes are set-up, the ThreadPool creates and starts 8 HandlerThreads. When a HandlerThread is swapped into the CPU, it gets a random ColorBox object from ThreadPool's private Vector, tells the ColorBox to change its color, and graciously returns to the "asleep" state.

"You can typically make your threaded applications run FASTER by inserting calls to sleep() (with reasonably long durations)." This definitely contributes to the perception that Threads are a "black art". Not enough calls: monopolization of the CPU. Not enough duration: time expiration interrupt events interrupt the running thread before it can finish useful work.

class ColorBox extends Canvas implements Runnable {
    private int    pause;
    private Color  curColor = getColor();
    private static Color[]  colors = {Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.red,
            Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow};

    public ColorBox(int p) {
        pause = p;
        new Thread(this).start();
    }

    private static Color getColor() {
        return colors[(int)(Math.random() * 1000) % colors.length];
    }
    public void run() {
        while(true) {
            curColor = getColor();
            repaint();
            try {
                Thread.sleep(pause);
            } catch(InterruptedException ignored) { }
        }
    }

    public void paint(Graphics g) {
        g.setColor(curColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class FlyweightDemo {
    public static void main( String[] args ) {
        int size = 8;
        int pause = 100;
        if (args.length > 0) {
            size  = Integer.parseInt( args[0] );
        }
        if (args.length > 1) {
            pause = Integer.parseInt( args[1] );
        }
        Frame frame = new Frame( "ColorBoxes - 1 thread per ColorBox" );
        frame.setLayout( new GridLayout( size, size ) );
        for (int i=0; i < size*size; i++) {
            frame.add( new ColorBox(pause));
        }
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
Output
D:> java ColorBoxes 18 50
        produces 324 boxes/threads and 50 millisecond sleep()
# After
8 shared HandlerThreads in a ThreadPool
The ColorBox class has now become a Flyweight: the color changing and painting capability remains "intrinsic", and the threaded behavior has been made "extrinsic".

The ThreadPool class plays the role of the Factory. As ColorBox objects are created, they register themselves with the ThreadPool object. The latter launches 8 "handler" threads. When each thread is swapped into the CPU, it selects a random Flyweight from the ThreadPool's cache, and asks the object to changeColor().

class ColorBox extends Canvas {
    private Color  curColor = getColor();
    private static Color[]  colors = { Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.red,
            Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow };

    public ColorBox(ThreadPool tp) {
        tp.register(this);
    }

    private static Color getColor() {
        return colors[(int)(Math.random() * 1000) % colors.length];
    }

    public void changeColor() {
        curColor = getColor();
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(curColor);
        g.fillRect( 0, 0, getWidth(), getHeight() );
    }
}

class ThreadPool {
    private Vector boxes = new Vector();
    private int pause;

    class HandlerThread extends Thread {
        public void run() {
            while(true) {
                ((ColorBox)boxes.elementAt(
                        (int)(Math.random()*1000) % boxes.size() )).changeColor();
                try {
                    Thread.sleep(pause);
                } catch(InterruptedException ignored) {}
            }
        }
    }

    public ThreadPool(int p) {
        pause = p;
    }

    public void register(ColorBox r) {
        boxes.addElement(r);
    }

    public void start() {
        int NUM_THREADS = 8;
        for (int i = 0; i < NUM_THREADS; i++) {
            new HandlerThread().start();
        }
    }
}

public class FlyweightDemo {
    public static void main( String[] args ) {
        int size = 8;
        int pause = 100;
        if (args.length > 0) {
            size  = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            pause = Integer.parseInt(args[1]);
        }
        ThreadPool tp = new ThreadPool(pause);
        Frame frame = new Frame("ColorBoxes - 8 shared HandlerThreads");
        frame.setLayout(new GridLayout(size, size));
        for (int i=0; i < size * size; i++) {
            frame.add(new ColorBox(tp));
        }
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tp.start();
    }
}

Output
D:> java ColorBoxes 18 50
        produces 324 boxes, 8 threads, and 50 millisecond sleep()
            performance is very much improved