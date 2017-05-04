package fi.softala.vote.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.model.Team;

@Controller
public class MainCtrl {

	@Inject
	private TeamDAOJdbcImpl dao;

	// front page, get teams except not_in_team
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String showLogin(HttpSession session, Model model) {
		if (session.getAttribute("voter") != null) {
			return "redirect:/innovations";
		}

		List<Team> teams = dao.findAll();
		teams.remove(0);
		model.addAttribute("teams", teams);

		return "redirect:/login";
	}

}