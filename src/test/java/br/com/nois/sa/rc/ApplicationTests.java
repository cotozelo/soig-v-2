package br.com.nois.sa.rc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(locations = "classpath:application.properties")
@TestPropertySource(locations = "classpath:testeApplication.properties")
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
}
