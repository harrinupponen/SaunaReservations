package hh.swd20.sauna.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.sauna.domain.Reservation;
import hh.swd20.sauna.domain.ReservationRepository;
import hh.swd20.sauna.domain.Sauna;
import hh.swd20.sauna.domain.SaunaRepository;
import hh.swd20.sauna.domain.User;
import hh.swd20.sauna.domain.UserRepository;

@Controller
public class SaunaController {

	@Autowired
	ReservationRepository rRepository;

	@Autowired
	SaunaRepository sRepository;

	@Autowired
	UserRepository uRepository;

	// Show login form
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// RESERVATION METHODS

	// Listing all reservations
	@GetMapping("/")
	public String getReservations(Model model) {
		List<Reservation> reslist = (List<Reservation>) rRepository.findAll();
		model.addAttribute("reslist", reslist);
		return "reslist";

	}

	// Listing only logged in users reservations
	@GetMapping(value = "/ownreservations")
	public String findUserReservations(Model model, Principal principal, User user) {

		String username = principal.getName();
		user = uRepository.findByUsername(username);
		model.addAttribute("reservations", user.getReservationList());
		return "ownreslist";

	}

	// Add a reservation / empty form
	// @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/addreservation")
	public String getNewReservationForm(Model model) {
		model.addAttribute("reservation", new Reservation());
		model.addAttribute("saunas", sRepository.findAll());
		model.addAttribute("users", uRepository.findAll());
		return "addreservation";
	}

	// receive and save data from the form
	@PostMapping(value = "/addreservation")
	public String addReservation(@ModelAttribute Reservation reservation) {
		rRepository.save(reservation);
		return "redirect:/";
	}

	// find res by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/editreservation/{id}")
	public String findReservationById(@PathVariable("id") Long resId, Model model) {
		model.addAttribute("reservation", rRepository.findById(resId));
		return "editreservation";
	}

	// save res by id
	@PostMapping(value = "/editreservation")
	public String editReservationById(@ModelAttribute Reservation reservation) {
		rRepository.save(reservation);
		return "redirect:/";
	}

	// delete res
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/deletereservation/{id}")
	public String deleteReservation(@PathVariable("id") Long resId) {
		rRepository.deleteById(resId);
		return "redirect:../";
	}

	// USER METHODS

	// Listing all users
	@GetMapping("/userlist")
	public String getUsers(Model model) {
		List<User> userlist = (List<User>) uRepository.findAll();
		model.addAttribute("userlist", userlist);
		return "userlist";

	}

	// empty form for a new user
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/adduser")
	public String getNewUserForm(Model model) {
		model.addAttribute("user", new User());
		return "adduser";
	}

	// receive and save data from the form, validation used also
	@PostMapping(value = "/adduser")
	public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "adduser";
		}
		uRepository.save(user);
		return "redirect:/userlist";
	}

	// THIS IS INCOMPLETE ------------------------------------

	// find user by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/edituser/{id}")
	public String findUserById(@PathVariable("id") Long userId, Model model) {
		model.addAttribute("user", uRepository.findById(userId));
		return "edituser";
	}

	// save user by id
	@PostMapping(value = "/edituser")
	public String editUserById(@ModelAttribute User user) {
		uRepository.save(user);
		return "redirect:/userlist";
	}

	// -----------------------------------------------------

	// delete user
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Long userId) {
		uRepository.deleteById(userId);
		return "redirect:../userlist";
	}

	// FETCH ALL SAUNAS

	// Listing all users
	@GetMapping("/saunalist")
	public String getSaunas(Model model) {
		List<Sauna> saunalist = (List<Sauna>) sRepository.findAll();
		model.addAttribute("saunalist", saunalist);
		return "saunalist";

	}

	// **********************REST***************************

	// Fetch all reservations
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/reservations")
	public @ResponseBody List<Reservation> resListRest() {
		return (List<Reservation>) rRepository.findAll();
	}

	// Show one reservation by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/reservation/{id}")
	public @ResponseBody Optional<Reservation> findReservationRest(@PathVariable("id") Long resId) {
		return rRepository.findById(resId);
	}

	// Add a new reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/reservations")
	public @ResponseBody Reservation saveReservationRest(@RequestBody Reservation reservation) {
		return rRepository.save(reservation);
	}

	// Fetch all saunas
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/saunas")
	public @ResponseBody List<Sauna> saunaListRest() {
		return (List<Sauna>) sRepository.findAll();
	}

	// Show one sauna by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/sauna/{id}")
	public @ResponseBody Optional<Sauna> findSaunaRest(@PathVariable("id") Long saunaId) {
		return sRepository.findById(saunaId);
	}

	// Add a new sauna
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/saunas")
	public @ResponseBody Sauna saveSaunaRest(@RequestBody Sauna sauna) {
		return sRepository.save(sauna);
	}

	// Fetch all users
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/users")
	public @ResponseBody List<User> userListRest() {
		return (List<User>) uRepository.findAll();
	}

	// Show one user by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/user/{id}")
	public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) {
		return uRepository.findById(userId);
	}

	// Add a new reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/users")
	public @ResponseBody User saveUserRest(@RequestBody User user) {
		return uRepository.save(user);
	}

	// *************************************************
}
