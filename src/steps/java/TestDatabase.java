package steps.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDatabase {
    public static final TestDatabase IN = new TestDatabase();
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account.Builder builder) {
        accounts.add(builder.build());
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void clear() { accounts.clear(); }
}
