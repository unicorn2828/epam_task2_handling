package by.kononov.handling.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.handling.exception.CustomException;
import by.kononov.handling.validator.FileValidator;

public class DataReader{
	static final Logger logger = LogManager.getLogger();

	public String readFile(String filePath) throws CustomException {
		FileValidator validator = new FileValidator();
		if (!validator.isFile(filePath)) {
			throw new CustomException("bad or doesn't exist: " + filePath);
		}
		try {
			return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(filePath).toURI())));
		} catch (IOException | URISyntaxException e) {
			logger.error("can't be read data: " + filePath, e);
			throw new CustomException("can't be read data: " + filePath, e);
		}
	}
}