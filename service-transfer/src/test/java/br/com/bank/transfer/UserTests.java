package br.com.bank.transfer;

import br.com.bank.transfer.domain.User;
import br.com.bank.transfer.domain.UserType;
import br.com.bank.transfer.exception.BusinessException;
import br.com.bank.transfer.exception.NotFoundException;
import br.com.bank.transfer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserTests {

	@Autowired
	private UserService userService;

	@Test
	void findByIdSuccess() {
		Long idTest = 1L;
		User user = userService.find(idTest);
		assertThat(user).isNotNull();
		assertThat(user.getName()).isNotNull();
		assertThat(user.getUserType()).isNotNull();
		assertThat(user.getDocument()).isNotNull();
		assertThat(user.getEmail()).isNotNull();
		assertThat(user.getPassword()).isNotNull();
		assertThat(user.getAmount()).isNotNull();
		assertThat(user.getId()).isEqualTo(idTest);
	}

	@Test
	void findByIdNotFound() {
		Long idTest = 0L;
		Exception exception = assertThrows(NotFoundException.class, () -> userService.find(idTest));
		assertThat(exception.getMessage()).isNotNull();
	}

	@Test
	void findByDocumentSuccess() {
		Long idTest = 1L;
		User user = userService.find(idTest);
		assertThat(user).isNotNull();
		assertThat(user.getDocument()).isNotNull();

		String document = user.getDocument();

		user = userService.find(document);
		assertThat(user).isNotNull();
		assertThat(user.getDocument()).isNotNull();
		assertThat(user.getName()).isNotNull();
		assertThat(user.getUserType()).isNotNull();
		assertThat(user.getEmail()).isNotNull();
		assertThat(user.getPassword()).isNotNull();
		assertThat(user.getAmount()).isNotNull();
		assertThat(user.getDocument()).isEqualTo(document);
	}

	@Test
	void findByDocumentNotFound() {
		String document = "invalid_document";
		Exception exception = assertThrows(NotFoundException.class, () -> userService.find(document));
		assertThat(exception.getMessage()).isNotNull();
	}

	@Test
	void saveSuccess() {
		User user = new User();
		user.setName("user test");
		user.setUserType(UserType.COMMON);
		user.setDocument("11235223455");
		user.setEmail("email@server.com");
		user.setPassword("23423");
		user.setAmount(BigDecimal.TEN);

		userService.save(user);

		assertThat(user).isNotNull();
		assertThat(user.getId()).isNotNull();
	}

	@Test
	void saveRequiredFields() {
		User user = new User();
		user.setName("user test");
		user.setAmount(BigDecimal.TEN);

		Exception exception = assertThrows(Exception.class, () -> userService.save(user));
		assertThat(exception.getMessage()).isNotNull();
	}
}
