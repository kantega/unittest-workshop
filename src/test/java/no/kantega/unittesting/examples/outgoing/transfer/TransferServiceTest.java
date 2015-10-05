package no.kantega.unittesting.examples.outgoing.transfer;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TransferServiceTest {

    private TransferService transferService;
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        transferService = new TransferService(accountRepository);
    }

    @Test
    public void happyDayScenario() {

        //given
        AccountNumber fromAccount = new AccountNumber();
        AccountNumber toAccount = new AccountNumber();
        int amount = 50;

        //when
        transferService.transfer(fromAccount, toAccount, amount);

        //then
        verify(accountRepository, times(1)).withdraw(same(fromAccount), eq(amount));
        verify(accountRepository, times(1)).deposit(same(toAccount), eq(amount));

    }

}
