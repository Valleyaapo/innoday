package fi.softala.test.vote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import fi.softala.test.innovation.InnovationDAOSpringJdbcImplTest;
import fi.softala.vote.VotingEngineApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotingEngineApplication.class)

public class VotingEngineApplicationTests {
	@Test
	public void contextLoads() {
		
	}
}
