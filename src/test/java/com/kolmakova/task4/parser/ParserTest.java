package com.kolmakova.task4.parser;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.exception.TextException;
import com.kolmakova.task4.util.TextReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParserTest {

    private static final String FILE_PATH = "src/test/resources/data.txt";

    private TextReader textReader;
    private TextParser textParser;

    @BeforeMethod
    public void setUp() {
        textReader = new TextReader();
        textParser = new DefaultTextParser();
    }

    @Test
    public void testParse() throws TextException {
        String text = textReader.readDataFromFile(FILE_PATH);
        TextComponent component = textParser.parse(text);
        String expected = "It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n" +
                "It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a moreor-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n" +
                "It is a established fact that a reader will be of a page when looking at its layout... \n";

        String actual = component.toString();
        Assert.assertEquals(actual, expected);
    }

}
