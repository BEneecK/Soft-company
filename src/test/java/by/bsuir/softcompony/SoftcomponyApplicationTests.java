package by.bsuir.softcompony;

import by.bsuir.softcompony.controller.MainController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class SoftcomponyApplicationTests {

	@Autowired
	private MainController mainController;

	@Test
	public void test() {
		assertThat(mainController).isNotNull();
	}
}
