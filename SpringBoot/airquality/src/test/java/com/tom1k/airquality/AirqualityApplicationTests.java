package com.tom1k.airquality;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AirqualityApplicationTests {

	@Test
	void contextLoads() {
	}

	// Test class added ONLY to cover main() invocation not covered by application tests.
	//Removed this test, since there would already be an app running on port 8080
	/*@Test
	public void main() {
		AirqualityApplication.main(new String[] {});
	}*/

}
