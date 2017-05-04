package fi.softala.test.innovation;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fi.softala.vote.controller.MainCtrl;
import fi.softala.vote.dao.InnovationDAO;
import fi.softala.vote.model.*;

@RunWith(MockitoJUnitRunner.class)
public class InnovationDAOSpringJdbcImplTest {
	
	@Inject
	private MockMvc mockMvc;
	@InjectMocks
	private MainCtrl controller;
	@Mock
	private InnovationDAO mockDao;
	
	@Before
	public void setup() {

		//controller unders
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

		//configure the return values of the mock object 
		Mockito.when(mockDao.addNew(new Innovation())).thenReturn(new Innovation());
	}
	
	@Test
	public void testAddNew() {
		/*String innoName = "uusi nimi";
		String innoDesc = "uusi kuvaus";
		long teamID = 1;
		
		Innovation mockInno = new Innovation();
		mockInno.setInnoName(innoName);
		mockInno.setInnoDesc(innoDesc);
		Team team = new Team();
		team.setTeamId(teamID);
		mockInno.setTeam(team);
		
		verify(mockDao, times(1)).addNew(mockInno);*/
	}
	
	@Test
	public void testFind() {
		
	}
	
	@Test
	public void testFindAll() {
		
	}
}
