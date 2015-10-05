package no.kantega.unittesting.examples.outgoing.transfer;

public interface AccountRepository {

    void deposit(AccountNumber accountNumber, int amount);
    void withdraw(AccountNumber accountNumber, int amount);

}
