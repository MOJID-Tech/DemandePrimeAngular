package com.gta.remuniration.controllers;
import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.service.DepartementService;
import com.gta.remuniration.service.BeneficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/departements")
public class DepartementController {

    @Autowired
    private DepartementService service;
    @Autowired
    BeneficeService beneficeservice ;
    /// Get all departements
    @GetMapping(value = "/ListDepar")
    public ResponseEntity<List<Departement>> demandesDg( ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDepartements());
    }
   
    /// Get Benefice by departement
    @GetMapping(value = "/Benefice")
    public ResponseEntity<Double> getBeneficeByIDdepartement(@RequestParam String IDdepar) {
        Long IDdepartement = Long.parseLong(IDdepar);
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficeservice.getBeneficeDepartement(IDdepartement));
    }



    @GetMapping("/Benefi/{id}")
    public ResponseEntity<Double> getBeneficeById(@PathVariable(value = "id") Long IDdepartement)
    {  
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficeservice.getBeneficeDepartement(IDdepartement));
    }


    @GetMapping("/equipes/{id}")
    public ResponseEntity<ArrayList<Equipe>> getEquipesById(@PathVariable(value = "id") Long IDdepartement)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getEquipesByDepartement(IDdepartement));

    }


}
