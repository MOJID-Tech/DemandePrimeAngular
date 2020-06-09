package com.gta.remuniration.service;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.exception.InsufficientRightException;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class DemandeService {
    @Autowired
    private DemandeRepository repository;
    @Autowired
    private EtatService etatService;
    @Autowired
    private EtatDemandeService etatDemandeService;
    @Autowired
    private SalarieService salarieService;


    //findAll
  /* @Transactional(readOnly = true)
    public Page<Demande> findAll(int pageIndex, int size) {
        Pageable pageable = new PageRequest(pageIndex, size );
        return repository.findAll(pageable);
    }*/
    //create
   @Transactional(readOnly = false)
    public Demande create(String  Montant_nett , String login) {
      Double  Montant_net=Double.parseDouble(Montant_nett );
        Demande demande =new Demande();
        if ( Montant_net  == null) {
            throw new NullValueException("Montant_net");
        }
        demande.setMontant_net(Montant_net);
        validate(demande,login);
        Date datedemande = java.util.Calendar.getInstance().getTime();
        demande.setDate_debut(datedemande);
        Double brutprim= calculateBrutprim(demande.getMontant_net());
      demande.setMontant_brut(brutprim);
      Salarie salarie = salarieService.findbylogin(login);
      demande.setSalarie(salarie );
        Double maxPrim = calculateMaxprim(demande.getSalarie());
        demande.setPrime_maximale(maxPrim);
        Etat etat  = etatService.finfbyid(1);

       demande= repository.save(demande);
       EtatDemande etatDemande= new  EtatDemande(datedemande,etat,demande);

       etatDemandeService.create(etatDemande);

        return demande;
    }
    //Update
    @Transactional(readOnly = false)
    public Demande update(Demande demande,String login) {
        if (demande == null) {
            throw new NullValueException("demande");
        }
        if (demande.getId() == null) {
            throw new NullValueException("id");
        }
        if (!repository.existsById(demande.getId())) {
            throw new NotFoundException(Demande.class, demande.getId());
        }

        validate(demande,login);
         repository.save(demande);
        return demande;
    }
    //validate
    private void validate(Demande demandeToSave ,String login) {
        if (login == null) {
            throw new InsufficientRightException();
        }
        List<String> demandeNullFieldList = new ArrayList<>();
        if (demandeToSave.getMontant_net() == null) {
            demandeNullFieldList .add("Montant_net");
        }

        if (!demandeNullFieldList .isEmpty()) {
            throw new NullValueException(demandeNullFieldList );
        }
    }
    //calculate maxprim
    private Double calculateMaxprim(Salarie salarie){
        return (null);
    }
    //calculate brutprim
    private Double calculateBrutprim(Double netprim){
        return (netprim);
    }

    //demandes manager
    public List<Demande> getDemandeListMg() {
        return repository.findByValideM( false);

    }

    //demmandes DG
    public List<Demande> getDemandeListDg() {
        return repository.findByValideM( true);

    }
}


