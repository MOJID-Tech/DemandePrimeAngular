package com.gta.remuniration.controllers;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.repository.EtatDemandeRepository;
import com.gta.remuniration.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin ("*")
@RestController
@RequestMapping(value="/demande")
public class DemandeController  {
    @Autowired
    private DemandeService service;
    @Autowired
    private EtatDemandeRepository etatDemandeRepository;
    @PostMapping
   //@RequestMapping(value="/demande",method=RequestMethod.POST)
    public ResponseEntity<Demande> create(@RequestParam String MN,  @RequestParam String login ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(MN ,login));
    }

   @GetMapping (value = "/Manager")
  // @RequestMapping(value="/demande/Manager",method=RequestMethod.POST)
    public ResponseEntity<List<Demande>> demandesManager( ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDemandeListMg());
    }
    @GetMapping (value = "/DG")
    //@RequestMapping(value="/demande/DG",method=RequestMethod.POST)
    public ResponseEntity<List<Demande>> demandesDg( ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDemandeListDg());
    }


    @GetMapping (value = "/alldemandes")
    //@RequestMapping(value="/demande/DG",method=RequestMethod.POST)
    public ResponseEntity<List<Demande>> demandes( ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDemandes());
    }

    @GetMapping("/etatdemande/{id}")
    public ResponseEntity<List<EtatDemande>> getEquipesById(@PathVariable(value = "id") Long IDdemande)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(etatDemandeRepository.findByDemande(IDdemande));

    }

}
