package utils.properties.properties_size_ids.properties_size_savings_id;

import utils.read_file.IReadFileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSizeSavingID implements IPropertiesSizeSavingsID {

    IReadFileInputStream readFileInputStream;
    Properties properties;

    public PropertiesSizeSavingID(IReadFileInputStream readFileInputStream) {
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

    public int readSizeSafetyDepositBoxID() {
        return Integer.parseInt(properties.getProperty("sizeSafetyDepositBoxId"));
    }

    public int readSizeSafetyDepositBoxKey() {
        return Integer.parseInt(properties.getProperty("sizeSafetyDepositBoxKey"));
    }
}
