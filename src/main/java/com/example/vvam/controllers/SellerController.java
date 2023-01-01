package com.example.vvam.controllers;

import com.example.vvam.dto.ClientRegistrationDto;
import com.example.vvam.dto.SellerRegistrationDto;
import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.Client;
import com.example.vvam.model.Seller;
import com.example.vvam.model.User;
import com.example.vvam.service.SellerServiceImpl;
import com.example.vvam.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerServiceImpl sellerService;

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("")
	public String listSellers(Model model) {
		List<Seller> sellers = sellerService.listAll();
		model.addAttribute("listSellers", sellers);
		return "dashboard/seller/list-seller";
	}
	
	@GetMapping("/add")
	public String addSeller(Seller seller, Model model) {
		model.addAttribute("seller", seller);
		model.addAttribute("size", false);
		return "dashboard/seller/save-update-seller";
	}
	
	@GetMapping("/edit/{id}")
	public String editSeller(@PathVariable("id") long id, Model model) {
		Seller seller = sellerService.getById(id).get();
		model.addAttribute("seller", seller);
		model.addAttribute("size", true);

		return "dashboard/seller/save-update-seller";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(value = "idSeller") Long id, @RequestParam(value = "dni") String dni,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "email") String email,@RequestParam(value="password", required = false) String password, RedirectAttributes redirect) throws ParseException {
		Seller seller = null;
		User user = null;
		if (id == null || id == 0) {
			seller = sellerService.save(new SellerRegistrationDto(dni, firstName, lastName, email));
			user = userService.saveSeller(new UserRegistrationDto(firstName,lastName,email,password,seller));
			redirect.addFlashAttribute("message", "Vendedor agregado correctamente.");
		} else {
			seller = sellerService.save(id, new SellerRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Vendedor" + seller.toString() + " actualizado correctamente.");
		}

		return "redirect:/seller/";
	}
	
	@PostMapping("/saveAjax")
	public String saveAjax(@RequestParam(value = "idClient") Long id, @RequestParam(value = "dni") String dni,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "email") String email, RedirectAttributes redirect) throws ParseException {
		Seller seller = null;
		if (id == null || id == 0) {
			seller = sellerService.save(new SellerRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Vendedor agregado correctamente.");
		} else {
			seller = sellerService.save(id, new SellerRegistrationDto(dni, firstName, lastName, email));
			redirect.addFlashAttribute("message", "Vendedor" + seller.toString() + " actualizado correctamente.");
		}

		List<Seller> sellers = sellerService.listAll();
		return "redirect:/client/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Seller seller = sellerService.getById(id).get();
		sellerService.delete(id, new SellerRegistrationDto(seller.getDni(), seller.getFirstName(), seller.getLastName(),
				seller.getEmail()));
		return "redirect:/seller/";
	}
}
