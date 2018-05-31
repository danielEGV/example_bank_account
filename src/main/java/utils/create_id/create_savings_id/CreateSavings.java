package utils.create_id.create_savings_id;

import utils.create_id.random_id.IRandomID;
import utils.properties.properties_size_ids.properties_size_savings_id.IPropertiesSizeSavingsID;

public class CreateSavings implements ICreateSavings {

    IPropertiesSizeSavingsID propertiesSizeSavingsID;

    public CreateSavings(IPropertiesSizeSavingsID propertiesSizeSavingsID) {
        this.propertiesSizeSavingsID = propertiesSizeSavingsID;
    }

    @Override
    public int createSafetyDepositBoxID() {
        return IRandomID.randomID.apply(propertiesSizeSavingsID.readSizeSafetyDepositBoxID());
    }

    @Override
    public int createSafetyDepositBoxKey() {
        return IRandomID.randomID.apply(propertiesSizeSavingsID.readSizeSafetyDepositBoxKey());
    }
}
