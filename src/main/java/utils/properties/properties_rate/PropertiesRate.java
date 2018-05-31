package utils.properties.properties_rate;

import utils.read_file.IReadFileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertiesRate implements IPropertiesRate {
    IReadFileInputStream readFile;
    Properties properties;

    public PropertiesRate(IReadFileInputStream readFile) {
        this.readFile = readFile;
        properties = new Properties();
        loadProperties();
    }

    public void loadProperties() {
        try {
            properties.load(readFile.readFile());
        } catch (IOException e) {
            System.out.println("File error.");
        }
    }

    public double readBaseRate() {
        return Double.parseDouble(properties.getProperty("baseRate"));
    }
}
