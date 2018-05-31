package utils.properties.properties_size_ids.properties_size_checking_id;

import utils.read_file.IReadFileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSizeCheckingID implements IPropertiesSizeCheckingID {

    IReadFileInputStream readFileInputStream;
    Properties properties;

    public PropertiesSizeCheckingID(IReadFileInputStream readFileInputStream) {
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

    public int readSizeDebitCardNumber() {
        return Integer.parseInt(properties.getProperty("sizeDebitCardNumber"));
    }

    public int readSizeDebitCardPin() {
        return Integer.parseInt(properties.getProperty("sizeDebitCardPin"));
    }


}
