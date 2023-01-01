package com.example.vvam.controllers;
import com.example.vvam.dto.ClientRegistrationDto;
import com.example.vvam.model.Client;
import com.example.vvam.service.ClientServiceImpl;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:8090")
@RequestMapping("api/client")
public class ClientApiController {
    @Autowired
    private ClientServiceImpl clientService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<Object> saveAjax(@RequestParam(value = "documentoIdentidad") String documentoIdentidad, @RequestParam(value = "razonSocial") String razonSocial,
                                  @RequestParam(value = "email") String email) {
        System.out.println("hola");
        List<Object> res = new ArrayList<>();
        Client cliente = null;
        cliente = clientService.findByDocumentoIdentidad(documentoIdentidad);
        if(cliente==null){
            cliente = clientService.save(new ClientRegistrationDto(documentoIdentidad, razonSocial,email));
            List<Client> clients = clientService.listAll();
            res.add(documentoIdentidad);
            res.add(clients);
        }


        //res.add("status: success");
        //return "{\"status\":\"}";
        return res;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Object> listClientsAjax(){
        //try{
//
        //    List<Client> clients = clientService.listAll();
        //    List<Object> res = new ArrayList<>();
//
//
        //    res.add("{'dni': '1234'}");
        //    res.add(clients);
        //    //Object[] res = {clients};
        //    ResponseEntity<List<Object>> responseEntity = new ResponseEntity<List<Object>>(res,HttpStatus.OK);
        //    return responseEntity;
        //}catch (Exception e){
        //    return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
        //}

        //model.addAttribute("listClients",clients);
        //Object[] res = {clients};
        //return clients;
        List<Client> clients = clientService.listAll();
        List<Object> res = new ArrayList<>();
        res.add(clients);
        return res;
    }
}
