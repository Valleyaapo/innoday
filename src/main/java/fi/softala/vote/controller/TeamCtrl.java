package fi.softala.vote.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import FormValidators.TeamForm;
import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.model.Team;

@Controller
public class TeamCtrl {

	@Inject
	private TeamDAOJdbcImpl teamdao;

	// add new team
	@RequestMapping(path = "/team/add", method = RequestMethod.POST)
	public String create(@RequestParam("src") String src,
			@Valid TeamForm teamform, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:" + src;
		}

		Team team = new Team();
		team.setTeamName(teamform.getName());
		teamdao.addNew(team);

		return "redirect:" + src;
	}

}
