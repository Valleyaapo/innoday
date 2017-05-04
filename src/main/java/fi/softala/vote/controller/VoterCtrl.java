package fi.softala.vote.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import FormValidators.VoterForm;
import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.dao.VoterDAOJdbcImpl;
import fi.softala.vote.model.Team;
import fi.softala.vote.model.Voter;

@Controller
public class VoterCtrl {
	@Inject
	private VoterDAOJdbcImpl dao;

	@Inject

    private TeamDAOJdbcImpl teamdao;
	
    public VoterDAOJdbcImpl getDao() { return dao; }
    
    public void setDao(VoterDAOJdbcImpl dao) { this.dao = dao; }
    
    @RequestMapping(path="/addVoter", method=RequestMethod.POST)
    public String addNewVoter(@Valid @ModelAttribute(value="VoterForm") VoterForm voterForm, BindingResult result,
    		HttpSession session) {
    	
//    	if(result.hasErrors()){
//    		return "redirect:" + src;
//    		@RequestParam("src") String src
//    	}
    	
    	Voter voter = new Voter();
    	
    	
    	voter.setFirstName(voterForm.getfName());
    	voter.setLastName(voterForm.getsName());
    	voter.setType(voterForm.getvType());

    	Team team = new Team();
    	
    	if (voter.getType().equals("INNOMEM")){
    		team = teamdao.findByTeamName(voterForm.gettName());
    		voter.setTeam(team);
    	} 
    	else {
	    	team.setTeamId(1);
	    	voter.setTeam(team);
    	}
    
    	dao.addVoter(voter); 
    	
    	return "redirect:/admin";
    }

}
