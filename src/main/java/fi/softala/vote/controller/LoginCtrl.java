package fi.softala.vote.controller;

import FormValidators.LoginForm;
import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.dao.VoterDAOJdbcImpl;
import fi.softala.vote.model.Team;
import fi.softala.vote.model.Voter;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginCtrl {

	@Inject
	private VoterDAOJdbcImpl voterdao;
	@Inject
	private TeamDAOJdbcImpl dao;

	// log in, find all teams except not_in_team(1)
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String viewLogin(LoginForm loginForm, Model model) {

		List<Team> teams = dao.findAll();
		teams.remove(0);
		model.addAttribute("teams", teams);

		return "login";
	}

	// log in
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String handleLogin(@Valid LoginForm loginForm, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "redirect:/login";
		}
		System.out.println(loginForm.getVoterTeam() + " hello");
		Voter voter;
		if (!loginForm.getVoterTeam().equalsIgnoreCase("not_in_team")) {
			try {
				voter = voterdao.findByVoterTeam(loginForm.getVoterFirstName(),
						loginForm.getVoterSirName(), loginForm.getVoterTeam());

				session.setAttribute("voter", voter);
				System.out.print(voter);
				return "redirect:/innovations";
			} catch (Exception e) {
				result.rejectValue("voterTeam", "403",
						"You are not invited to vote");
				System.out.println(loginForm.getVoterTeam() + " hello");
				System.out.println("No Vote");
				return "redirect:/login";
			}
		}
		try {
			voter = voterdao.findByVoterName(loginForm.getVoterFirstName(),
					loginForm.getVoterSirName());

			session.setAttribute("voter", voter);
			System.out.print(voter);
			return "redirect:/innovations";
		} catch (Exception e) {
			result.rejectValue("voterFirstName", "403",
					"You are not invited to vote");
			System.out.println("No Vote");
			return "redirect:/login";
		}
	}
}
