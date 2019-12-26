package test.kononov.handling.reader;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.kononov.handling.exception.CustomException;
import by.kononov.handling.reader.DataReader;

public class ReaderTest{
	private static final String FILE_NAME = "data/mainText.txt";
	private DataReader dataReader;

	@BeforeClass
	public void setUp() {
		dataReader = new DataReader();
	}

	@AfterClass
	public void tierDown() {
		dataReader = null;
	}

	@Test(description = "check if the file can be read")
	public void readFileTestPositive() throws CustomException {
		String actual = dataReader.readFile(FILE_NAME);
		assertNotNull(actual);
	}

	@Test(timeOut = 500, description = "check for exception if the file can't be read longer then specified time")
	public void readFileTestTime() throws CustomException {
		dataReader.readFile(FILE_NAME);
	}
}