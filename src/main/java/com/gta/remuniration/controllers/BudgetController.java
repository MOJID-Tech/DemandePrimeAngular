package com.gta.remuniration.controllers;


import com.gta.remuniration.entity.BudgetDepartement;
import com.gta.remuniration.service.BudgetDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/budget")
public class BudgetController {

    @Autowired
    private  BudgetDepartService budgetDepartService;

    @PostMapping
    //@RequestMapping(value="/demande",method=RequestMethod.POST)
    public ResponseEntity<BudgetDepartement> createBudgetDep(@RequestParam String montant, @RequestParam String IDDepartement ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDepartService.create(montant ,IDDepartement));
    }


    @GetMapping("/BudgetDepartement/{id}")
    public ResponseEntity<BudgetDepartement> getBudgetByIDdepartement(@PathVariable(value = "id") Long IDdepar)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDepartService.getByID(IDdepar));
    }


}
