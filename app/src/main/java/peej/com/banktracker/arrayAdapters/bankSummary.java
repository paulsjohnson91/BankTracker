package peej.com.banktracker.arrayAdapters;

/**
 * Created by johnsopa on 27-10-17.
 */

public class bankSummary {
    public String accountname;
    public String bankname;
    public String balance;
    public String record_id;

    public bankSummary(String accountname, String bankname, String balance, String record_id) {
        this.accountname = accountname;
        this.balance = balance;
        this.bankname = bankname;
        this.record_id = record_id;
    }
}