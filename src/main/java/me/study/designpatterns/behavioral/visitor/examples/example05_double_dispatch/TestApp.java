package me.study.designpatterns.behavioral.visitor.examples.example05_double_dispatch;

import java.lang.reflect.Method;
/*
 * Visitor in Java: Double dispatch (within a single hierarchy)
Problem."If you want to add a new Visitable object, you have to change the Visitor interface, and then implement that method in each of your Visitors."

Solution. With the ReflectiveVisitor, you only need one method in the Visitor interface - visit(Object). All other visit() methods can be added later as point-to-point coupling is required
 */
public class TestApp {
    public static void main( String[] args ) {
        Element[] list = {new Element1(), new Element2(), new Element3()};
        UpVisitor up = new UpVisitor();
        DownVisitor down = new DownVisitor();
        for (Element element : list) {
            element.accept(up);
        }
        for (Element element : list) {
            element.accept(down);
        }
    }
}

// The "element" hierarchy
interface Element {
    void accept(ReflectiveVisitor v);
}

class Element1 implements Element {
    public void accept(ReflectiveVisitor v) {
        v.visit(this);
    }

    public String callElement1() {
        return "callElement1";
    }
}

class Element2 implements Element {
    public void accept(ReflectiveVisitor v) {
        v.visit(this);
    }

    public String callElement2() {
        return "callElement2";
    }
}

class Element3 implements Element {
    public void accept(ReflectiveVisitor v) {
        v.visit(this);
    }

    public String callElement3() {
        return "callElement3";
    }
}

// The "operation" hierarchy
abstract class ReflectiveVisitor {
    abstract public void visit(Object o);

    public void visitElement3(Element3 e) {
        System.out.println("ReflectiveVisitor: do Base on " + e.callElement3());
    }

    // 1. Look for visitElementClassName() in the current class
    // 2. Look for visitElementClassName() in superclasses
    // 3. Look for visitElementClassName() in interfaces
    // 4. Look for visitObject() in current class
    protected Method getMethod(Class source) {
        Class  clazz = source;
        Method methodName   = null;
        while (methodName == null  &&  clazz != Object.class) {
            String clazzName = clazz.getName();
            clazzName = "visit" + clazzName.substring(clazzName.lastIndexOf('.') + 1);
            try {
                methodName = getClass().getMethod(clazzName, clazz);
            } catch (NoSuchMethodException ex) {
                clazz = clazz.getSuperclass();
            }
        }
        if (clazz == Object.class) {
            // System.out.println( "Searching for interfaces" );
            Class[] interfaces = source.getInterfaces();
            for (Class intface : interfaces) {
                String interfaceName = intface.getName();
                interfaceName = "visit" + interfaceName.substring(interfaceName.lastIndexOf('.') + 1);
                try {
                    methodName = getClass().getMethod(interfaceName, intface);
                } catch (NoSuchMethodException ex) {
                    //ex.printStackTrace();
                }
            }
        }
        if (methodName == null)
            try {
                methodName = getClass().getMethod("visitObject", Object.class);
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        return methodName;
    }
}

class UpVisitor extends ReflectiveVisitor {
    public void visit(Object o) {
        try {
            getMethod(o.getClass()).invoke(this, o);
        } catch (Exception ex) {
            System.out.println( "UpVisitor - no appropriate visit() method" );
        }
    }

    public void visitElement1(Element1 element) {
        System.out.println("UpVisitor: do Up on " + element.callElement1());
    }

    public void visitObject(Object o) {
        System.out.println("UpVisitor: generic visitObject() method");
    }
}

class DownVisitor extends ReflectiveVisitor {
    public void visit(Object o) {
        try {
            getMethod(o.getClass()).invoke(this, o);
        } catch (Exception ex) {
            System.out.println( "DownVisitor - no appropriate visit() method" );
        }
    }

    public void visitElement2(Element2 element) {
        System.out.println("DownVisitor: do Down on " + element.callElement2());
    }
}


