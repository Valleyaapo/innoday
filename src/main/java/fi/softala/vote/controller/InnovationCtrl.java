package fi.softala.vote.controller;

import FormValidators.InnovationForm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.softala.vote.dao.InnoDAOJdbcImpl;
import fi.softala.vote.dao.TeamDAOJdbcImpl;
import fi.softala.vote.dao.VoterDAOJdbcImpl;
import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Team;
import fi.softala.vote.model.Voter;

@Controller
public class InnovationCtrl {

	@Inject
	private TeamDAOJdbcImpl teamdao;

	@Inject
	private VoterDAOJdbcImpl voterdao;

	@Inject
	private InnoDAOJdbcImpl dao;

	public InnoDAOJdbcImpl getDao() {
		return dao;
	}

	public void setDao(InnoDAOJdbcImpl dao) {
		this.dao = dao;
	}

	// retrieve innovations to user, hide the innovation that belongs to the
	// same team as the user
	@RequestMapping(path = "/innovations", method = RequestMethod.GET)
	public String viewInnovations(InnovationForm innovationForm, Model model,
			HttpSession session) {

		if (session.getAttribute("voter") == null) {
			return "redirect:/";
		}
		Voter voter = new Voter();
		voter = (Voter) session.getAttribute("voter");
		System.out
				.println("tämän äänestäjän tiimi on " + voter.getTeam());

		List<Innovation> innovations = dao.findAll();
		for (int i = 0; i < innovations.size(); i++) {
			if (voter.getTeam().getTeamId() == innovations.get(i).getTeam()
					.getTeamId()) {
				innovations.remove(i);
			}
		}
		model.addAttribute("innovations", innovations);
		return "innovations";
	}

	// add new innovation, team and it's members (member addition not
	// implemented on front-end yet)
	@RequestMapping(path = "/innovationAdd", method = RequestMethod.POST)
	public String addNew(
			@ModelAttribute(value = "InnovationForm") InnovationForm innovationform) {
		Innovation inno = new Innovation();
		inno.setInnoName(innovationform.getInnoName());
		Team team = new Team();
		Voter testVoter1 = new Voter(); // placeholder
		testVoter1.setFirstName("Testi");
		testVoter1.setLastName("Kayttaja1");
		testVoter1.setTeam(team);
		testVoter1.setType("INNOMEM");
		Voter testVoter2 = new Voter(); // placeholder
		testVoter2.setFirstName("Testii");
		testVoter2.setLastName("Kayttaja2");
		testVoter2.setTeam(team);
		testVoter2.setType("INNOMEM");
		List<Voter> votersToAdd = new ArrayList<Voter>();
		votersToAdd.add(testVoter1);
		votersToAdd.add(testVoter2);
		team.setTeamName(innovationform.getTeamName());
		inno.setTeam(team);
		inno.setInnoDesc(innovationform.getInnoDesc());
		inno.setInnoOwner(innovationform.getInnoName());

		List<Team> teams = teamdao.findAll();
		List<Voter> voters = voterdao.findAll();

		for (int i = 0; i < teams.size(); i++) {
			if (team.getTeamName().equalsIgnoreCase(teams.get(i).getTeamName())) {
				team.setTeamId(teams.get(i).getTeamId());
			}
		}

		for (int i = 0; i < votersToAdd.size(); i++) {
			Voter voterToAdd = votersToAdd.get(i);
			boolean found = false;

			for (int j = 0; j < voters.size(); j++) {
				Voter voter = voters.get(j);

				if (voterToAdd.getFirstName().equals(voter.getFirstName())
						&& voterToAdd.getLastName().equals(voter.getLastName())) {
					if (voter.getTeam().getTeamId() != 1) { // if the found
															// voter belongs to
															// a team (not
															// not_in_team), the
															// team cannot be
															// changed
						System.out.println("" + voterToAdd.getFirstName() + " "
								+ voterToAdd.getLastName()
								+ " is already a member of a team.");
					} else { // otherwise update team_id from 1 (not_in_team)
						voterdao.updateTeam(voter, team);
						System.out.println("Updated team of "
								+ voter.getFirstName() + " "
								+ voter.getLastName());
					}
					found = true;
					break;
				}
			}

			if (!found) { // if voter to be added doesn't exist with the same
							// name, add a new one
				voterdao.addVoter(voterToAdd);
			}
		}

		dao.addNew(inno);

		return "redirect:/admin";
	}
}

/*
 * 
 * OPPILAS => opiskelijatunnus
 */
