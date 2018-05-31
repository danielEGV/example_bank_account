package model.rate;

import utils.properties.properties_rate.PropertiesRate;
import utils.read_file.ReadFileInputStream;

public class BaseRate implements IBaseRate {

    @Override
    public double baseRate() {
        return new PropertiesRate(
                new ReadFileInputStream("properties_bank/base_rate.properties")
        ).readBaseRate();
    }
}
