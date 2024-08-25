# Real Examples
## JDK
* java.util.Timer (all scheduleXXX() methods)
* java.util.concurrent.Executor#execute()
* java.util.concurrent.ExecutorService (invokeXXX() and submit() methods)
* java.util.concurrent.ScheduledExecutorService (all scheduleXXX() methods)
* java.lang.reflect.Method#invoke()
* javax.servlet.Filter#doFilter()
  https://www.baeldung.com/chain-of-responsibility-pattern#cor-real-world
* java.util.logging.Logger#log()
  https://github.com/openjdk/jdk/blob/master/src/java.logging/share/classes/java/util/logging/Logger.java#L964
  https://github.com/openjdk/jdk/blob/master/src/java.logging/share/classes/java/util/logging/Handler.java