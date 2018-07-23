package ru.job4j.bank;

import ru.job4j.bank.models.Accounts;
import ru.job4j.bank.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    /**
     * База пользователей и их счетов.
     */
    private Map<User, List<Accounts>> db = new HashMap<>();

    /**
     * Добавление пользователей в базу.
     * @param user пользователь.
     */
    public void addUser(User user) {
        this.db.putIfAbsent(user, new ArrayList<Accounts>());
    }

    /**
     * Удаление пользователя.
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        this.db.remove(user);

    }

    /**
     * Добавление счета для пользователя.
     * @param passport паспортные данные пользователя.
     * @param account счет.
     */
    public void addAccountToUser(String passport, Accounts account) {
        List<Accounts> userAcc = this.db.get(findUserByPassport(passport));
        if (userAcc != null) {
            userAcc.add(account);
        }
    }

    /**
     * Удаление счета пользователя.
     * @param passport паспортные данные пользователя.
     * @param account счет пользователя.
     */
    public void deleteAccountFromUser(String passport, Accounts account) {
        List<Accounts> userAcc = this.db.get(findUserByPassport(passport));
        if (userAcc != null) {
            userAcc.remove(account);
        }
    }

    /**
     * Получение списка счетов пользователя.
     * @param passport паспортные данные пользователя.
     * @return список счетов.
     */
    public List<Accounts> getUserAccounts(String passport) {
        return this.db.get(findUserByPassport(passport));
    }

    /**
     * Перевод средств.
     * @param srcPassport паспортные данные отправителя.
     * @param srcRequisite реквизиты счета отправителя.
     * @param destPassport паспортные данные получателя.
     * @param destRequisite реквизиты счета получателя.
     * @param amount количество средств.
     * @return true, если перевод прошел успешно. Иначе false.
     */
    public boolean transferMoney(String srcPassport,
                                 String srcRequisite,
                                 String destPassport,
                                 String destRequisite,
                                 double amount) {
        boolean result = false;
        Accounts senderAcc = getAccByRequisite(srcPassport, srcRequisite);
        Accounts recipientAcc = getAccByRequisite(destPassport, destRequisite);
        if (senderAcc != null
                && recipientAcc != null
                && senderAcc.getValue() >= amount) {
            senderAcc.setValue(senderAcc.getValue() - amount);
            recipientAcc.setValue(recipientAcc.getValue() + amount);
            result = true;
        }

        return result;
    }

    public Map<User, List<Accounts>> getDb() {
        return db;
    }

    /**
     * Поиск пользователоя по паспортным данным.
     * @param passport паспортные данные.
     * @return пользователь.
     */
    private User findUserByPassport(String passport) {
        User user = null;
        for (Map.Entry<User, List<Accounts>> userListEntry : this.db.entrySet()) {
            if (userListEntry.getKey().getPassport().equals(passport)) {
                user = userListEntry.getKey();
                break;
            }
        }
        return user;
    }

    /**
     * Получение счета пользователя по паспортным данным и реквизитам.
     * @param passport паспортные данные.
     * @param requisite реквизиты счета.
     * @return счет пользователя.
     */
    private Accounts getAccByRequisite(String passport, String requisite) {
        List<Accounts> userAcc = this.db.get(findUserByPassport(passport));
        Accounts currentAcc = null;
        if (userAcc != null) {
            for (Accounts accounts1 : userAcc) {
                if (accounts1.getRequisites().equals(requisite)) {
                    currentAcc = accounts1;
                }
            }
        }
        return currentAcc;
    }
}
