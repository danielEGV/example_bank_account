package utils.read_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFileBufferedReader implements IReadFileBufferedReader {
    BufferedReader bufferedReader;

    public ReadFileBufferedReader(String src) {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(src));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public BufferedReader readFile() {
        return bufferedReader;
    }
}
