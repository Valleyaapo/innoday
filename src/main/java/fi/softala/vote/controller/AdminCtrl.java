package fi.softala.vote.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import FormValidators.InnovationForm;
import FormValidators.TeamForm;
import FormValidators.VoterForm;
import fi.softala.vote.dao.InnoDAOJdbcImpl;
import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.dao.VoteDAOJdbcImpl;
import fi.softala.vote.dao.VoterDAOJdbcImpl;
import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Team;
import fi.softala.vote.model.Vote;
import fi.softala.vote.model.Voter;

@Controller
public class AdminCtrl {

	@Inject
	private VoteDAOJdbcImpl votedao;
	@Inject
	private VoterDAOJdbcImpl voterdao;
	@Inject
	private TeamDAOJdbcImpl teamdao;
	@Inject
	private InnoDAOJdbcImpl innodao;

	// get all data for admin page
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public String showAdmin(Model model, InnovationForm innovationForm,
			VoterForm voterForm, TeamForm teamForm, BindingResult results,
			HttpServletRequest request) {

		List<Team> teams = teamdao.findAll();
		List<Innovation> innovations = innodao.findAll();
		List<Voter> voters = voterdao.findAll();

		teams.remove(0);
		model.addAttribute("teams", teams);
		model.addAttribute("innovations", innovations);
		model.addAttribute("voters", voters);
		model.addAttribute("src", request.getRequestURI());

		return "admin";
	}

	// get data for teamadmin page
	@RequestMapping(path = "/teamadmin", method = RequestMethod.GET)
	public String showTeamAdmin(Model model, HttpSession session,
			InnovationForm innovationForm, VoterForm voterForm,
			BindingResult results, HttpServletRequest request) {

		Voter voter = (Voter) session.getAttribute("voter");

		if (voter == null || voter.getTeam().getTeamName() == "not_in_team") {
			return "redirect:/";
		}

		Team team = new Team();

		team = teamdao.find(voter.getTeam().getTeamId());

		List<Innovation> innovations = innodao.findByTeamId(team.getTeamId());

		List<Voter> voters = voterdao.findByTeamId(team.getTeamId());

		model.addAttribute("innovations", innovations);
		model.addAttribute("team", team);
		model.addAttribute("voters", voters);
		model.addAttribute("src", request.getRequestURI());

		return "teamadmin";
	}

	// log in admin
	@RequestMapping(path = "/admin/login", method = RequestMethod.POST)
	public String adminAuth() {
		return "redirect:/admin";
	}

	// get all votes for admin page
	@RequestMapping(path = "/admin/votes", method = RequestMethod.GET)
	public String showVotes(Model model) {
		List<Vote> votes = votedao.findAllVotes();
		model.addAttribute("votes", votes);

		return "admin";
	}
}