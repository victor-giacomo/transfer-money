package br.com.bank.transfer;

import br.com.bank.transfer.domain.Transfer;
import br.com.bank.transfer.domain.User;
import br.com.bank.transfer.dto.TransferHistory;
import br.com.bank.transfer.exception.BusinessException;
import br.com.bank.transfer.service.AuthorizationService;
import br.com.bank.transfer.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TransferTests {

    @Autowired
    private TransferServiceImpl transferService;

	@Mock
	private AuthorizationService authorizationService;

	@InjectMocks
	private TransferServiceImpl mockTransferService;

	@Test
	void transferSuccess() {
		User sender = getUser();
		User receiver = getStore();

		Transfer transfer = buildTransfer(sender, receiver, new BigDecimal(100.00));

		transferService.transfer(transfer);
	}

	@Test
	void transferFromStore() {
		User sender = getStore();
		User receiver = getUser();

		Transfer transfer = buildTransfer(sender, receiver, new BigDecimal(100.00));

		Exception exception = assertThrows(BusinessException.class, () -> transferService.transfer(transfer));
		assertThat(exception.getMessage()).isNotNull();
	}

	@Test
	void transferInsufficientFunds() {
		User sender = getUser();
		User receiver = getStore();

		Transfer transfer = buildTransfer(sender, receiver, new BigDecimal(9999999.00));

		Exception exception = assertThrows(BusinessException.class, () -> transferService.transfer(transfer));
		assertThat(exception.getMessage()).isNotNull();
	}

	@Test
	void transferNegativeAmount() {
		User sender = getUser();
		User receiver = getStore();

		Transfer transfer = buildTransfer(sender, receiver, new BigDecimal(-100.00));

		Exception exception = assertThrows(BusinessException.class, () -> transferService.transfer(transfer));
		assertThat(exception.getMessage()).isNotNull();
	}

	@Test
	void transferNotAuthorized() {
		User sender = getUser();
		User receiver = getStore();

		Transfer transfer = buildTransfer(sender, receiver, new BigDecimal(100.00));

		Mockito.when(authorizationService.authorize()).thenReturn(false);

		Exception exception = assertThrows(BusinessException.class, () -> mockTransferService.transfer(transfer));
		assertThat(exception.getMessage()).isNotNull();
	}

    @Test
    void transferHistory() {
	    this.transferSuccess();
        List<TransferHistory> history = transferService.history(getUser().getId());

        assertThat(history).isNotNull();
        assertThat(history).isNotEmpty();
    }

	private User getUser() {
		return new User(1L);
	}

	private User getStore() {
		return new User(2L);
	}

	private Transfer buildTransfer(User sender, User receiver, BigDecimal amount) {
		Transfer transfer = new Transfer();
		transfer.setSender(sender);
		transfer.setReceiver(receiver);
		transfer.setAmount(amount);
		return transfer;
	}
}
