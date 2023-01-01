package com.example.vvam.controllers;

import com.example.vvam.dto.SaleDetailRegistrationDto;
import com.example.vvam.dto.SaleRegistrationDto;
import com.example.vvam.model.*;
import com.example.vvam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.Style;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleServiceImpl saleService;

    @Autowired
    private Sale_DetailServiceImpl sale_detailService;

    @Autowired
    private SellerServiceImpl sellerService;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("")
    public String listSales(Model model){
        List<Sale> sales = saleService.listAll();
        model.addAttribute("listSales",sales);

        return "dashboard/sales/list-sale";
    }

    @GetMapping("/add")
    public String addSale(Sale sale, Model model){

        //Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //UserDetails userDetail = (UserDetails) auth;
        //String email = userDetail.getUsername();
        //Object[] arr = userDetail.getAuthorities();

        //System.out.println(email);
        //System.out.println(userDetail.getAuthorities().toString().equals("[ROLE_ADMIN]"));

        model.addAttribute("sale", sale);
        List<Client> clients = clientService.listAll();
        List<Seller> sellers = sellerService.listAll();
        List<Product> products  = productService.listAll();

        model.addAttribute("listClients",clients);
        model.addAttribute("listSellers", sellers);
        model.addAttribute("listProducts",products);
        model.addAttribute("size", false);

        return "dashboard/sales/save-update-sale";
    }

    @GetMapping("/edit/{id}")
    public String editSale(@PathVariable("id") Long id, Model model){
        Sale sale = saleService.getById(id).get();

        List<Client> clients = clientService.listAll();
        List<Seller> sellers = sellerService.listAll();
        List<Product> products  = productService.listAll();
        model.addAttribute("sale", sale);
        model.addAttribute("listClients",clients);
        model.addAttribute("listSellers", sellers);
        model.addAttribute("listProducts",products);
        model.addAttribute("size", true);

        return "dashboard/sales/save-update-sale";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "idSale") Long idSale, @RequestParam(value = "docIdentidadClient") String docIdentidadClient, @RequestParam(value = "dniSeller") String dniSeller,
                       @RequestParam(value = "idDetail[]") List<Long> idDetails,@RequestParam(value = "product[]") List<String> products,
                       @RequestParam(value = "quantity[]") List<Integer> quantities, @RequestParam(value = "eliminado[]") List<Boolean> eliminados,
                       RedirectAttributes redirectAttributes) throws ParseException{

        Date date = new Date();
        Collection<Sale_Detail> items = new ArrayList<Sale_Detail>();
        Client client = clientService.findByDocumentoIdentidad(docIdentidadClient);
        Seller seller = sellerService.findByDni(dniSeller);
        Sale sale = null;
        if(idSale==null || idSale==0){
            sale = saleService.save(new SaleRegistrationDto(client,seller,date));
        }else {
            sale = saleService.save(idSale, new SaleRegistrationDto(client,seller,date));
        }
        System.out.println(eliminados);
        for (int i=0;i<=products.size()-1; i++){
            Sale_Detail sale_detail = null;
            Product p = productService.findByName(products.get(i));
            int q = quantities.get(i);
            boolean e = eliminados.get(i);
            Long id = idDetails.get(i);
            if(!(id==0 && e==true)){
                if(id==0){
                    sale_detail = sale_detailService.save(new SaleDetailRegistrationDto(sale,p,q,e));
                }else {
                    sale_detail = sale_detailService.save(id,new SaleDetailRegistrationDto(sale,p,q,e));
                }

                items.add(sale_detail);
            }

        }
        sale.setItems(items);
        redirectAttributes.addFlashAttribute("message", "Venta agregada correctamente");
        return "redirect:/sale/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Sale sale = saleService.getById(id).get();
        saleService.delete(id,new SaleRegistrationDto(sale.getClient(),sale.getSeller(), sale.getSaleDate()));

        return "redirect:/sale/";

    }
}
