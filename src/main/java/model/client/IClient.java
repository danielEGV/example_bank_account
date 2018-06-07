package model.client;

import model.account.Account;
import org.davidmoten.rx.jdbc.annotations.Column;

public interface IClient {
    @Column("nameclient")
    public String getName();

    void setName(String name);

    @Column("socialsecurity")
    int getSocialSecurity();

    void setSocialSecurity(int socialSecurity);

    @Column("accountid")
    Account getAccount();

    void setAccount(Account account);

    String toString();

}
