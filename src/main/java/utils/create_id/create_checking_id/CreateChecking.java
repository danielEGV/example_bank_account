package utils.create_id.create_checking_id;

import utils.create_id.random_id.IRandomID;
import utils.properties.properties_size_ids.properties_size_checking_id.IPropertiesSizeCheckingID;

public class CreateChecking implements ICreateChecking {

    IPropertiesSizeCheckingID propertiesSizeCheckingID;

    public CreateChecking(IPropertiesSizeCheckingID propertiesSizeCheckingID) {
        this.propertiesSizeCheckingID = propertiesSizeCheckingID;
    }

    @Override
    public int createCreditCardNumber() {
        return IRandomID.randomID.apply(propertiesSizeCheckingID.readSizeDebitCardNumber());
    }

    @Override
    public int createCreditCardPin() {
        return IRandomID.randomID.apply(propertiesSizeCheckingID.readSizeDebitCardPin());
    }
}
