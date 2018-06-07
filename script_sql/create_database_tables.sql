DROP DATABASE IF EXISTS bankdb;

CREATE DATABASE bankdb;

USE bankdb;

DROP TABLE IF EXISTS checkingaccountbank;
CREATE TABLE IF NOT EXISTS checkingaccountbank(
    debitcardnumber INT(20) PRIMARY KEY,
    debitcardpin INT(20) NOT NULL
);

DROP TABLE IF EXISTS savingsaccountbank;
CREATE TABLE IF NOT EXISTS savingsaccountbank(
    safetydepositboxid INT(20) PRIMARY KEY,
    safetydepositboxkey INT(20) NOT NULL
);

DROP TABLE IF EXISTS accountbank;
CREATE TABLE IF NOT EXISTS accountbank(
	accountid INT(20) PRIMARY KEY,
    balance DOUBLE(30, 2) NOT NULL,
    rate DOUBLE(5, 2) NOT NULL,
    creation DATE NOT NULL,
    modification DATE NULL,
    idsavingsaccount INT(20) NULL,
    idcheckingaccount INT(20) NULL,
    FOREIGN KEY(idsavingsaccount) REFERENCES savingsaccountbank(safetydepositboxid),
    FOREIGN KEY(idcheckingaccount) REFERENCES checkingaccountbank(debitcardnumber)
);

DROP TABLE IF EXISTS clientbank;
CREATE TABLE IF NOT EXISTS clientbank (
	socialsecurity INT(20) PRIMARY KEY,
	nameclient VARCHAR(100) NOT NULL,
    accountid INT(20) NULL,
    FOREIGN KEY(accountid) REFERENCES accountbank(accountid)
);

