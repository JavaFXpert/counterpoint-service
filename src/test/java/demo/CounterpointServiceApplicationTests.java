package demo;

import com.culturedear.counterpoint.CounterpointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CounterpointService.class)
@WebAppConfiguration
public class CounterpointServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
