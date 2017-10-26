package peej.com.banktracker.db;

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "peej.com.banktracker.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "banks";

        public static final String COL_BANK_NAME_TITLE = "bank_name";
        public static final String COL_ACCOUNT_NAME_TITLE = "account_name";
        public static final String COL_INITIAL_BALANCE_TITLE = "balance";
        public static final String COL_ACCOUNT_TYPE_TITLE = "account_type";
        public static final String COL_CURRENCY = "currency_type";
    }
}
