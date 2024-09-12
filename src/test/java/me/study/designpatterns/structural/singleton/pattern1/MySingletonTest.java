package me.study.designpatterns.structural.singleton.pattern1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import me.study.designpatterns.creational.singleton.example1.MySingleton;

public class MySingletonTest {

    @Test
    public void testGetInstance() {
        MySingleton singleton1 = MySingleton.getInstance();

        MySingleton singleton2 = MySingleton.getInstance();

        assertSame(singleton1, singleton2);
        assertEquals(singleton1, singleton2);
    }

    @Test
    public void testGetInstanceSynchronized() {
        MySingleton singleton1 = MySingleton.getInstanceSynchronized();

        MySingleton singleton2 = MySingleton.getInstanceSynchronized();

        assertSame(singleton1, singleton2);
        assertEquals(singleton1, singleton2);
    }

    @Test
    public void testGetInstanceDoubleCheckedLocking() {
        MySingleton singleton1 = MySingleton.getInstanceDoubleCheckedLocking();

        MySingleton singleton2 = MySingleton.getInstanceDoubleCheckedLocking();

        assertSame(singleton1, singleton2);
        assertEquals(singleton1, singleton2);
    }

    @Test
    public void testGetInstanceUsingVariable() {
        MySingleton singleton1 = MySingleton.getInstanceUsingVariable();

        MySingleton singleton2 = MySingleton.getInstanceUsingVariable();

        assertSame(singleton1, singleton2);
        assertEquals(singleton1, singleton2);
    }

    @Test
    public void testGetInstanceUsingPublicVariable() {
        MySingleton singleton1 = MySingleton.INSTANCE_PUBLIC;

        MySingleton singleton2 = MySingleton.INSTANCE_PUBLIC;

        assertSame(singleton1, singleton2);
        assertEquals(singleton1, singleton2);
    }
}
