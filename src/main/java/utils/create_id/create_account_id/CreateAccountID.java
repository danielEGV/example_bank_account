package utils.create_id.create_account_id;

import utils.create_id.random_id.IRandomID;
import utils.properties.properties_size_ids.properties_size_account_id.IPropetiesSizeAccountID;

public class CreateAccountID implements ICreateAccountID {

    IPropetiesSizeAccountID propetiesSizeAccountID;

    public CreateAccountID(IPropetiesSizeAccountID propetiesSizeAccountID) {
        this.propetiesSizeAccountID = propetiesSizeAccountID;
    }

    @Override
    public int getAccountID(int type, int socialSecurity, int index) {
        String typeStr = getString.apply(type);
        String ssStr = lastTwoDigit.apply(getString.apply(socialSecurity));
        String indexStr = getString.apply(index);
        String randomID = randomID();
        return getInteger.apply(typeStr + ssStr + indexStr + randomID);
    }

    @Override
    public String randomID() {
        return getString.apply(IRandomID.randomID.apply(propetiesSizeAccountID.readSizeAccountID()));
    }
}
