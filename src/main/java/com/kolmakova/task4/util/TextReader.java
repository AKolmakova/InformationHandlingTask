package com.kolmakova.task4.util;

import com.kolmakova.task4.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {

    private static final Logger LOGGER = LogManager.getLogger();

    public String readDataFromFile(String pathToFile) throws TextException {
        Path path = Paths.get(pathToFile);
        if (!Files.exists(path)) {
            throw new TextException(String.format("Resource '%s' is not found.", pathToFile));
        }
        try {
            return Files.readString(path);
        } catch (IOException e) {
            LOGGER.error(String.format("Error occurred during read file %s", path), e);
        }

        return null;
    }

}
