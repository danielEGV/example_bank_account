package utils.read_csv;

import utils.read_file.IReadFileBufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV implements IReadCSV {
    IReadFileBufferedReader readFileBufferedReader;

    public ReadCSV(IReadFileBufferedReader readFileBufferedReader) {
        this.readFileBufferedReader = readFileBufferedReader;
    }


    @Override
    public List<String[]> makeListClients() throws IOException {
        String line;
        List<String[]> arrayList = new ArrayList<>();
        BufferedReader bufferedReader = readFileBufferedReader.readFile();
        while ((line = bufferedReader.readLine()) != null) {
            arrayList.add(splitSeparator.apply(line));
        }
        return arrayList;
    }
}
