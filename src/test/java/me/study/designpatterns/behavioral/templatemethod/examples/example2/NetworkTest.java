package me.study.designpatterns.behavioral.templatemethod.examples.example2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NetworkTest {
    @Test
    public void testFacebookSuccess() {
        String userName = "test";
        String password = "test";
        Network network = new Facebook(userName, password);
        String message = "Hello Facebook";
        Boolean sent = network.post(message);
        assertEquals(true, sent);
    }

    @Test
    public void testTwitterSuccess() {
        String userName = "test";
        String password = "test";
        Network network = new Twitter(userName, password);
        String message = "Hello Facebook";
        Boolean sent = network.post(message);
        assertEquals(true, sent);
    }
}
