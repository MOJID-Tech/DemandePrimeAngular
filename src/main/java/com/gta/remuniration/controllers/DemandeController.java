package com.gta.remuniration.controllers;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.User;
import com.gta.remuniration.repository.EtatDemandeRepository;
import com.gta.remuniration.service.AppartientService;
import com.gta.remuniration.service.DemandeService;
import com.gta.remuniration.service.SalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin ("*")
@RestController
@RequestMapping(value="/demande")
public class DemandeController  {
    @Autowired
    private DemandeService service;
    @Autowired

    private SalarieService salarieService;

    @Autowired
    private AppartientService appartientService;


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
        return ResponseEntity.status(HttpStatus.OK).body(service.getDemandeListMg());
    }
    @GetMapping (value = "/DG")
    //@RequestMapping(value="/demande/DG",method=RequestMethod.POST)
    public ResponseEntity<List<Demande>> demandesDg( ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDemandeListDg());
    }

    @PostMapping(value = "/validate/{id}")

    public ResponseEntity<Boolean> validate(@PathVariable Integer id, @RequestParam String Prim_Manager,@RequestParam String  Prim_DG, @RequestParam String login ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.validerPrime(id, Prim_Manager,Prim_DG,login));

    }
    @PostMapping(value = "/refuse/{id}")

    public ResponseEntity<Boolean> refuse(@PathVariable Integer id, @RequestParam String Prim_Manager,@RequestParam String Prim_DG, @RequestParam String login ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.refuserPrime(id, Prim_Manager,Prim_DG,login));

    }
    @PostMapping(value = "/PrimMax")

    public ResponseEntity<Double> PrimMax(@RequestParam Long id ,@RequestParam Integer annee ) {
        return ResponseEntity.status(HttpStatus.OK).body(salarieService.PrimeMaxSalarieParSociete(id  , annee));

    }
    @PostMapping(value = "/Pourcentage")

    public ResponseEntity<Double> Pourcentage(@RequestParam Long id ,@RequestParam Integer annee ) {
        return ResponseEntity.status(HttpStatus.OK).body(salarieService.PourcentageContributionparSociete(id  , annee));

    }
    @PostMapping(value = "/Active")

    public ResponseEntity<List<Long>> Active(@RequestParam String date1, @RequestParam String date2 , @RequestParam  Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(appartientService.EquipeActiveparSalarie(date1,date2,id));

    }
    @GetMapping (value = "/salaries")
    public ResponseEntity<Page<Salarie>> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageIndex,
            @RequestParam(required = false, defaultValue = "6") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(salarieService.findAll(pageIndex, size));
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


    @GetMapping (value = "/historique")
    //@RequestMapping(value="/demande/DG",method=RequestMethod.POST)
    public ResponseEntity<List<Demande>> gethistorique( ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDemandes());
    }

}
