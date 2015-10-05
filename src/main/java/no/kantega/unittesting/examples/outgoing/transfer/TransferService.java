package no.kantega.unittesting.examples.outgoing.transfer;

public class TransferService {

    private AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(AccountNumber fromAccount, AccountNumber toAccount, int amount) {

        //transactions and other stuff omitted

        accountRepository.deposit(toAccount, amount);
        accountRepository.withdraw(fromAccount, amount);

    }

}
