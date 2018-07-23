package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.bank.models.Accounts;
import ru.job4j.bank.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {
    private Bank bank;
    private User pasha;
    private User vika;
    private User dima;
    private Accounts pashaAcc;
    private Accounts vikaAcc;
    private Accounts dimaAcc;
    Map<User, List<Accounts>> db;

    @Before

    public void loadBank() {
        bank = new Bank();
        pasha = new User("Pavel", "123456");
        vika = new User("Vika", "654321");
        dima = new User("Dima", "109876");
        pashaAcc = new Accounts(1000.0, "421056744321234");
        vikaAcc = new Accounts(1200.5, "544564845657236");
        dimaAcc = new Accounts(700.9, "854236548965258");
        db = bank.getDb();

    }

    @Test
    public void whenAddUserInDb() {
        bank.addUser(pasha);
        assertTrue(db.containsKey(pasha));
    }

    @Test
    public void whenRemoveUserFromDB() {
        bank.addUser(pasha);
        assertEquals(1, db.keySet().size());
        assertTrue(db.containsKey(pasha));
        bank.deleteUser(pasha);
        assertEquals(0, db.keySet().size());
        assertFalse(db.containsKey(pasha));
    }

    @Test
    public void whenAddAccountForUser() {
        bank.addUser(pasha);
        bank.addAccountToUser("123456", pashaAcc);
        assertTrue(db.get(pasha).contains(pashaAcc));
        List<Accounts> acc = db.get(pasha);
        assertThat("421056744321234", is(acc.get(0).getRequisites()));
        assertThat(1000.0, is(acc.get(0).getValue()));
    }

    @Test
    public void whenRemoveAccountFromUser() {
        bank.addUser(pasha);
        bank.addUser(vika);
        bank.addAccountToUser("123456", pashaAcc);
        assertTrue(db.get(pasha).contains(pashaAcc));
        bank.addAccountToUser("654321", vikaAcc);
        assertTrue(db.get(vika).contains(vikaAcc));
        bank.deleteAccountFromUser("654321", vikaAcc);
        assertFalse(db.get(vika).contains(vikaAcc));
        assertTrue(db.get(pasha).contains(pashaAcc));
    }

    @Test
    public void whenTransferMoneyIsTrue() {
        bank.addUser(pasha);
        bank.addUser(vika);
        bank.addAccountToUser("123456", pashaAcc);
        bank.addAccountToUser("654321", vikaAcc);
        assertTrue(bank.transferMoney("123456",
                "421056744321234",
                "654321",
                "544564845657236",
                600.0
                )
        );
        assertThat(pashaAcc.getValue(), is(400.0));
        assertThat(vikaAcc.getValue(), is(1800.5));
    }

    @Test
    public void whenTransferMoneyIsFalseNotEnoughMoney() {
        bank.addUser(pasha);
        bank.addUser(vika);
        bank.addAccountToUser("123456", pashaAcc);
        bank.addAccountToUser("654321", vikaAcc);
        assertFalse(bank.transferMoney("123456",
                "421056744321234",
                "654321",
                "544564845657236",
                1600.0
                )
        );
        assertThat(pashaAcc.getValue(), is(1000.0));
        assertThat(vikaAcc.getValue(), is(1200.5));
    }

    @Test
    public void whenTransferMoneyIsFalseThenUserNotFound() {
        bank.addUser(pasha);
        bank.addUser(vika);
        bank.addAccountToUser("123456", pashaAcc);
        bank.addAccountToUser("654321", vikaAcc);
        assertFalse(bank.transferMoney("109876", //Dima
                "854236548965258", //Dima requisite
                "654321",
                "544564845657236",
                1600.0
                )
        );
        assertThat(pashaAcc.getValue(), is(1000.0));
        assertThat(vikaAcc.getValue(), is(1200.5));
    }

    @Test
    public void whenGetUserAcc() {
        bank.addUser(pasha);
        bank.addAccountToUser("123456", pashaAcc);
        List<Accounts> userAcc = bank.getUserAccounts("123456");
        List<Accounts> assertAcc = new ArrayList<>();
        assertAcc.add(pashaAcc);
        assertThat(assertAcc, is(userAcc));
     }
}

