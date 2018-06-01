package utils.read_csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public interface IReadCSV {
    String SEPARATOR = ",";
    Function<String, String[]> splitSeparator = x -> x.split(SEPARATOR);
    List<String[]> makeListClients() throws IOException;
}
