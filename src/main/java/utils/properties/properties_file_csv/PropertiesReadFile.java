package utils.properties.properties_file_csv;

import utils.read_file.IReadFileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReadFile implements IPropertiesReadFile {
    IReadFileInputStream readFileInputStream;
    Properties properties;

    public PropertiesReadFile(IReadFileInputStream readFileInputStream) {
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

    public String readFileProperties() {
        return properties.getProperty("src");
    }
}
