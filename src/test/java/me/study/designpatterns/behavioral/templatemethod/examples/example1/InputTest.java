package me.study.designpatterns.behavioral.templatemethod.examples.example1;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.assertEquals;

public class InputTest {
    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testConvertUpper() {
        provideInput("ConvertUpper");
        Input input = new UppercaseConverter();
        var converted = input.run();
        assertEquals("CONVERTUPPER", converted);
    }

    @Test
    public void testConvertLower() {
        provideInput("ConvertLower");
        Input input = new LowercaseConverter();
        var converted = input.run();
        assertEquals("convertlower", converted);
    }
}
