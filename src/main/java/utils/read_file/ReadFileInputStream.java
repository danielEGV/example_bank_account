package utils.read_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReadFileInputStream implements IReadFileInputStream {
    InputStream inputStream;

    public ReadFileInputStream(String src) {
        try {
            inputStream = new FileInputStream(src);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public InputStream readFile() {
        return inputStream;
    }
}
