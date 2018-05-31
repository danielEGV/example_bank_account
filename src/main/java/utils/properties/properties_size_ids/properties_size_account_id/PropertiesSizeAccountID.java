package utils.properties.properties_size_ids.properties_size_account_id;

import utils.read_file.IReadFileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSizeAccountID implements IPropetiesSizeAccountID {
    IReadFileInputStream readFileInputStream;
    Properties properties;

    public PropertiesSizeAccountID(IReadFileInputStream readFileInputStream) {
        this.readFileInputStream = readFileInputStream;
        properties = new Properties();
        loadProperties();
    }

    public void loadProperties() {
        try {
            properties.load(readFileInputStream.readFile());
        } catch (IOException e) {
            System.out.println("File error.");
        }
    }

    public int readSizeAccountID() {
        return Integer.parseInt(properties.getProperty("sizeAccountId"));
    }
}
