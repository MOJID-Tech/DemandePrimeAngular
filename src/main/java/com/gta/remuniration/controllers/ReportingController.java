package com.gta.remuniration.controllers;

import com.gta.remuniration.service.AppartientService;
import com.gta.remuniration.service.EquipeService;
import com.gta.remuniration.service.SalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/benefices")
public class ReportingController {

    @Autowired
    SalarieService salarieService;
    @Autowired
    EquipeService equipeService;
    @Autowired
    AppartientService appartientService;
    @GetMapping("/ContributionEquipe/{id}")
    public ResponseEntity<Double> getBeneficeHorsManagerById(@PathVariable(value = "id") Long IDEQUIPE)
    {
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();
        Long IDDepartement = equipeService.getByID(IDEQUIPE).getDepartement().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(salarieService.PourcentageContributionEquipeParDepartement(IDEQUIPE,IDDepartement,currentYear));
    }

    @GetMapping("/Equipes/{id}")
    public ResponseEntity<List<Long>> getEquipes(@PathVariable(value = "id") Long IDSalarie)
    {
        LocalDate currentdate = LocalDate.now();
        int annee = currentdate.getYear();
        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(appartientService.EquipeActiveparSalarie(date11,date12,IDSalarie));
    }

    @GetMapping("/ContributionSalarie/{id}/{idequipe}")
    public ResponseEntity<Double> getPourcentage(@PathVariable(value = "id") Long IDSalarie,@PathVariable(value = "idequipe") Long IDEquipe)
    {
        LocalDate currentdate = LocalDate.now();
        int annee = currentdate.getYear();
        return ResponseEntity.status(HttpStatus.CREATED).body(salarieService.PourcentageContributionparEquipe(IDSalarie,IDEquipe,annee));
    }




}
