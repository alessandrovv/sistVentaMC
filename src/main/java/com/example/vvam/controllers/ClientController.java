package com.example.vvam.controllers;

import com.example.vvam.dto.ClientRegistrationDto;
import com.example.vvam.model.Client;
import com.example.vvam.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientServiceImpl clientService;

	@GetMapping("")
	public String listClients(Model model) {
		List<Client> clients = clientService.listAll();
		model.addAttribute("listClients", clients);
		return "dashboard/client/list-client";
	}

	@GetMapping("/add")
	public String addClient(Client client, Model model) {
		model.addAttribute("client", client);
		model.addAttribute("size", false);
		return "dashboard/client/save-update-client";
	}

	@GetMapping("/edit/{id}")
	public String editClient(@PathVariable("id") long id, Model model) {
		Client client = clientService.getById(id).get();
		model.addAttribute("client", client);
		model.addAttribute("size", true);

		return "dashboard/client/save-update-client";
	}

	@PostMapping("/save")
	public String save(@RequestParam(value = "idClient") Long id, @RequestParam(value = "dni") String dni,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "email") String email, RedirectAttributes redirect) throws ParseException {
		Client client = null;
		if (id == null || id == 0) {
			client = clientService.save(new ClientRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Cliente agregado correctamente.");
		} else {
			client = clientService.save(id, new ClientRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Cliente" + client.toString() + " actualizado correctamente.");
		}

		return "redirect:/client/";
	}

	@PostMapping("/saveAjax")
	public String saveAjax(@RequestParam(value = "idClient") Long id, @RequestParam(value = "dni") String dni,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "email") String email, RedirectAttributes redirect) throws ParseException {
		Client client = null;
		if (id == null || id == 0) {
			client = clientService.save(new ClientRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Cliente agregado correctamente.");
		} else {
			client = clientService.save(id, new ClientRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Cliente" + client.toString() + " actualizado correctamente.");
		}

		List<Client> clients = clientService.listAll();
		return "redirect:/client/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Client client = clientService.getById(id).get();
		clientService.delete(id, new ClientRegistrationDto(client.getDni(), client.getFirstName(), client.getLastName(),
				client.getEmail()));
		return "redirect:/client/";
	}
}
