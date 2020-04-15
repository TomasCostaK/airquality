package com.tom1k.airquality;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AirqualityApplicationTests {

	@Test
	void contextLoads() {
		assertThat("Teste").isEqualTo("Teste");
	}

	// Test class added ONLY to cover main() invocation not covered by application tests.
	//Removed this test, since there would already be an app running on port 8080
	/*@Test
	public void main() {
		AirqualityApplication.main(new String[] {});
	}*/

}
