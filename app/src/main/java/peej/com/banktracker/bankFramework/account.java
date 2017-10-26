package peej.com.banktracker.bankFramework;

import peej.com.banktracker.currencies.currencyTypes;

/**
 * Created by johnsopa on 26-10-17.
 */

public class account {
    private float balance;
    private String bankName;
    private String accountName;
    private currencyTypes currencyType;
    private accountTypes accountType;

    public account(int startBalance, String bankName, String accountName, currencyTypes c, accountTypes a){
        balance = startBalance;
        this.bankName = bankName;
        this.accountName = accountName;
        currencyType = c;
        accountType = a;
    }

    public float getBalance(){
        return balance;
    }

    public String getBankName(){
        return bankName;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public currencyTypes getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(currencyTypes currencyType) {
        this.currencyType = currencyType;
    }

    public accountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(accountTypes accountType) {
        this.accountType = accountType;
    }


}
