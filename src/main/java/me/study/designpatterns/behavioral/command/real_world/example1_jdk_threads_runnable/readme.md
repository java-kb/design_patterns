If you want to implement concurrency in a program, create an object of type Runnable. 
The Runnable interface requires you to override the run() method. This method will 
contain the code that you want to execute concurrently. You can implement the execution 
of the command in two ways: Either the Runnable object executes the code itself, or it 
delegates the execution to another object. In our example, the travel agency object has 
delegated the command to execute a trip to the tour operator. However, it is not mandatory 
to have the recipient execute the command. It would also be conceivable to have the entire 
business logic, or at least a large part of it, executed by the command.

This would look like this, for example:

 Runnable runnableDemo = new Runnable() {
 @Override
 public void run() {
 // ... concurrent code
 }
 };

You then create an instance of the Thread class and pass an instance of that class to the 
constructor:
 Thread threadDemo = new Thread(runnableDemo);
And fnally, you call the start() method on the Thread instance, which results in the 
code of a Runnable type class being executed. As an Invoker, the Thread class is as loosely 
coupled to the Receiver as travel providers are to tour operators.

 threadDemo.start();
