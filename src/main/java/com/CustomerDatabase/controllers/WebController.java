package com.CustomerDatabase.controllers;


import com.CustomerDatabase.repository.CustomerSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.CustomerDatabase.repository.CustomerRepository;
import com.CustomerDatabase.model.Customer;

@Controller
public class WebController {
    boolean idSortASC= false;
    boolean nameSortASC = false;


    @Autowired
    CustomerRepository custRepo;
    @Autowired
    CustomerSearchRepository custSearchRepo;

    @RequestMapping("/")
    public String blank(){
        return "redirect:home";
    }

    @RequestMapping("/home")
    public String tablePage(Model model){
        model.addAttribute("custList",custRepo.findAll());
        return "home";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Customer customer) {
        custRepo.save(customer);
        return "redirect:home";
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String search){
        model.addAttribute("custList", custSearchRepo.searchCust(search));
        model.addAttribute("search",search);
        return "home";
    }

    @RequestMapping(value="/deleteCustomer")
    public String deleteCust(@RequestParam String deleteCustomer){
        custRepo.deleteById(deleteCustomer);
        return "redirect:home";
    }

    @RequestMapping(value="/editRedirectCustomer")
    public String editRedirectCust(Model model, @RequestParam String search){
        model.addAttribute("search",search);
        return "edit";
    }

    @RequestMapping(value="/editCustomer", method = RequestMethod.GET)
    public String editCust(@RequestParam String id, @ModelAttribute Customer customer){
        customer.setId(id);
        custRepo.save(customer);
        return "redirect:home";
    }

    @RequestMapping(value="/sortID")
    public String sortID(Model model){
        if(!idSortASC){
        model.addAttribute("custList",custSearchRepo.sortIDQueryASC());
        nameSortASC=false;
        idSortASC=true;
        }
        else{
            model.addAttribute("custList",custSearchRepo.sortIDQueryDESC());
            nameSortASC=false;
            idSortASC=false;
        }
        return "home";
    }
    @RequestMapping(value="/sortName")
    public String sortName(Model model){
        if(!nameSortASC){
            model.addAttribute("custList",custSearchRepo.sortNameQueryASC());
            nameSortASC=true;
            idSortASC=false;
        }
        else{
            model.addAttribute("custList",custSearchRepo.sortNameQueryDESC());
            nameSortASC=false;
            idSortASC=false;
        }
        return "home";
    }

}
