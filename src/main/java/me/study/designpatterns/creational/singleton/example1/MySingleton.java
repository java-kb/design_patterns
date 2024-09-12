package me.study.designpatterns.creational.singleton.example1;

/*
 * This is a simple way to implement the singleton pattern! The instance of the
class is created at the latest possible time, namely when it is needed for the first time.
Therefore, this procedure is called lazy instantiation, i.e. delayed loading.
 */
public class MySingleton {
    private static MySingleton instance;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (instance == null)
            instance = new MySingleton();
        return instance;
    }

    /*
     * The class described above runs beautifully as long as you don’t use
     * concurrency. What can
     * happen? Assume the following case: You have two threads, both accessing the
     * getInstance() method. The first thread gets to line 9 and finds that there is
     * no instance yet.
     * Then the Virtual Machine takes away its compute time and lets Thread 2 access
     * the method.
     * Thread 2 is allowed to work until the end of the method and creates an
     * instance of the
     * class, which is returned. Then Thread 1 gets allocated compute time again.
     * The last thing
     * he remembers is that the reference is zero. It will enter the block and
     * create an instance as
     * well. And now comes exactly what you wanted to avoid – you have two instances
     * of
     * the class.
     * Whenever you want to prevent code from being executed by two threads at the
     * same
     * time, lock the affected code:
     * The method can only be
     * entered by a thread when no other thread is working with it (anymore). This
     * solves the
     * problem. However, synchronization is costly, so this approach is an
     * unnecessary brake.
     * Imagine – every time you want to get the one instance of the class, a lock is
     * set on the
     * method
     */
    public static synchronized MySingleton getInstanceSynchronized() {
        if (instance == null)
            instance = new MySingleton();
        return instance;
    }

    /*
     * Do not lock the entire method, but only the critical part! First query
     * whether an instance
     * has already been created. If the method is called for the second time, the
     * result of the comparison is false. Therefore, a lock is only required on the
     * first call, when the comparison
     * instance == null returns a true. So inside the if statement, you create a
     * block that
     * is synchronized. In this block, you query – this time in a thread-safe manner
     * – whether
     * there is an instance of the class. If not, create one. Finally, return the
     * instance
     */
    public static MySingleton getInstanceDoubleCheckedLocking() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null) {
                    instance = new MySingleton();
                }
            }
        }

        return instance;
    }

    /*
     * To avoid all problems in concurrent systems, you can resort to early loading.
     * As usual, you
     * create a static variable that holds the reference to the single instance. You
     * initialize it as
     * soon as you load it. A static method returns that instance
     * You avoid all problems resulting from concurrency with this approach. But you
     * have to
     * trust that the virtual machine will first create the instance before allowing
     * other threads to
     * access it – and that is exactly what you can rely on according to the
     * specification.
     */
    private static final MySingleton INSTANCE = new MySingleton();

    public static MySingleton getInstanceUsingVariable() {
        return INSTANCE;
    }

    /*
     * Since no second instance of the class is to be created and cannot be created
     * at all, the
     * following solution is also conceivable: Make the
     * instance public, and you then additionally save the getInstance method:
     */
    public static final MySingleton INSTANCE_PUBLIC = new MySingleton();

    public void doSomething() {
        System.out.println("MySingleton doSomething");
    }
}
