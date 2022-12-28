package com.example.vvam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.User;
import com.example.vvam.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService ;

    @GetMapping("")
    public String listUsers(Model model){
        List<User> users = userService.listAll();
        model.addAttribute("listUsers",users);
        return "dashboard/user/list-user";
    }
    @GetMapping("/add")
    public String addUser(User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("size", false);
        return "dashboard/user/save-update-user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model){
        User user = userService.getById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("size", true);
//
        return "dashboard/user/save-update-user";
    }

   @PostMapping("/save")
   public String save(@RequestParam(value = "idUser") Long id, @RequestParam(value = "nombre") String nombre,
                      @RequestParam(value = "apellidos") String apellidos, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password, RedirectAttributes redirect) throws ParseException {
       User user = null;
       if(id==null || id==0){
           user = userService.saveUser(new UserRegistrationDto(nombre,apellidos,email,password));
           redirect.addFlashAttribute("message", "Usuario agregado correctamente.");
       }else{
           user = userService.saveUser(id,new UserRegistrationDto(nombre,apellidos,email,password));
           redirect.addFlashAttribute("message", "Usuario" + user.toString() + " actualizado correctamente.");
       }

       return "redirect:/user/";
   }

  // @PostMapping("/saveAjax")
  // public String saveAjax(@RequestParam(value = "idClient") Long id, @RequestParam(value = "dni") String dni, @RequestParam(value = "firstName") String firstName,
  //                        @RequestParam(value = "lastName") String lastName, @RequestParam(value = "email") String email, RedirectAttributes redirect) throws ParseException {
  //     Client client = null;
  //     if(id==null || id==0){
  //         client = clientService.save(new ClientRegistrationDto(dni,firstName,lastName,email));
  //         redirect.addFlashAttribute("message", "Cliente agregado correctamente.");
  //     }else{
  //         client = clientService.save(id,new ClientRegistrationDto(dni,firstName,lastName,email));
  //         redirect.addFlashAttribute("message", "Cliente" + client.toString() + " actualizado correctamente.");
  //     }

  //     List<Client> clients = clientService.listAll();
  //     return "redirect:/client/";
  // }

   @GetMapping("/delete/{id}")
   public String delete(@PathVariable("id") Long id){
       User user = userService.getById(id).get();
       userService.delete(id, new UserRegistrationDto(user.getNombre(),user.getApellidos(),user.getEmail(),user.getPassword()));
       return "redirect:/user/";
   }
}
