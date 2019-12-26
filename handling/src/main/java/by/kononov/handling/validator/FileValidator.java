package by.kononov.handling.validator;

import java.io.File;
import java.net.URL;

public class FileValidator{

	public boolean isFile(String filePath) {
		URL resource = getClass().getClassLoader().getResource(filePath);
		File file = null;
		if (resource != null) {
			file = new File(resource.getFile());
		}
		return file != null && !file.isDirectory() && file.exists() && file.length() != 0;
	}
}
