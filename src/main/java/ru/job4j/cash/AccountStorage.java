package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (getById(account.id()).isEmpty()) {
            accounts.put(account.id(), account);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean update(Account account) {
        if (getById(account.id()).isPresent()) {
            accounts.put(account.id(), account);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (getById(fromId).isPresent() && getById(toId).isPresent()) {
            var fromAcc = new Account(fromId, accounts.get(fromId).amount() - amount);
            var toAcc = new Account(toId, accounts.get(toId).amount() + amount);
            update(fromAcc);
            update(toAcc);
            return true;
        } else {
            return false;
        }
    }
}