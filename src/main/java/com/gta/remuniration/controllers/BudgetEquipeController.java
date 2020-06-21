package com.gta.remuniration.controllers;

import com.gta.remuniration.entity.BudgetEquipe;
import com.gta.remuniration.service.BeneficeService;
import com.gta.remuniration.service.BudgetEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/budgetequipe")
public class BudgetEquipeController {

    @Autowired
    private BudgetEquipeService budgetEquipeService;
    @Autowired
    BeneficeService beneficeservice ;
    @PostMapping
    public ResponseEntity<BudgetEquipe> createBudgetEqui(@RequestParam String montantH, @RequestParam String montantM, @RequestParam String IDEquipe ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetEquipeService.create(montantH ,montantM,IDEquipe));
    }

    @GetMapping("/BeneficeManager/{id}")
    public ResponseEntity<Double> getBeneficeManagerById(@PathVariable(value = "id") Long IDEQUIPE)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficeservice.BeneficeManagerEquipe(IDEQUIPE));
    }

    @GetMapping("/BeneficeHorsManager/{id}")
    public ResponseEntity<Double> getBeneficeHorsManagerById(@PathVariable(value = "id") Long IDEQUIPE)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficeservice.BeneficeHorsManagerEquipe(IDEQUIPE));
    }

    @GetMapping("/BudgetEquipe/{id}")
    public ResponseEntity<BudgetEquipe> getBudgetByIDdepartement(@PathVariable(value = "id") Long IDdepar)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetEquipeService.getByID(IDdepar));
    }

}
