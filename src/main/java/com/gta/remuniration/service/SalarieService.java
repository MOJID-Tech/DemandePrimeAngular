package com.gta.remuniration.service;
import com.gta.remuniration.entity.*;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalarieService {
    @Autowired
    private SalarieRepository repository;
    @Autowired
    private BeneficeService beneficeService;
    @Autowired
    private AppartientService appartientService;
    @Autowired
    private BudgetEquipeService budgetEquipeService;
    @Autowired
    private EquipeService EquipeService;



    //findAll
    @Transactional(readOnly = true)
    public Page<Salarie> findAll(int pageIndex, int size) {

        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "nomsalarie");
        return repository.findAll(pageable);

    }

    //find by id
    @Transactional(readOnly = true)
    public Salarie finfbyid(Long  id ){
        Salarie salarie;
        return  salarie  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Salarie.class, id));
    }

    //find by login
    @Transactional(readOnly = true)
    public Salarie findbylogin(String login) {
        if (login == null) {
            throw new NullValueException("login");
        }
        Salarie salarie;
        return salarie = repository.findByLogin(login)
                .
                        orElseThrow(() -> new NotFoundException(Salarie.class, "login", login));
    }
    @Transactional(readOnly = true)
    public Salarie findbyEmail(String email) {
        if (email == null) {
            throw new NullValueException("login");
        }
        Salarie salarie;
        return salarie = repository.findByEmailsalarie(email)
                .
                        orElseThrow(() -> new NotFoundException(Salarie.class, "email", email));
    }

    //benefice Equipe
    @Transactional(readOnly = true)
    public Double BeneficeEquipe (Long idEquipe , Integer annee){
        if (idEquipe  == null) {
            throw new NullValueException("idEquipe");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }

        return (beneficeService.ChiffreAffaireEquipe(idEquipe, annee)-beneficeService.ChargeEquipe(idEquipe, annee));
    }
    //benefice departement
    @Transactional(readOnly = true)
    public Double BeneficeDepartement (Long idDepartement , Integer annee){
        if (idDepartement  == null) {
            throw new NullValueException("idDepartement");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }

        return (beneficeService.ChiffreAffaireDepartement(idDepartement, annee)-beneficeService.ChargeDepartement(idDepartement, annee));
    }
    //benefice societe
    @Transactional(readOnly = true)
    public Double BeneficeSociete (Long idDSociete , Integer annee){
        if (idDSociete  == null) {
            throw new NullValueException("idDSociete");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }

        return (beneficeService.ChiffreAffaireSociete(idDSociete, annee)-beneficeService.ChargerSociete(idDSociete, annee));
    }
    //pourcentage de contribution d'un salarie dans un equipe
    @Transactional(readOnly = true)
    public Double PourcentageContributionparEquipe(Long idSalarie  , Long idEquipe ,Integer  annee)
    {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
        if (idEquipe == null) {
            throw new NullValueException("idEquipe");
        }
        if (annee == null) {
            throw new NullValueException("annee");
        }


        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();

        int   NombreEquipeActive=appartientService.EquipeActiveparSalarie(date11,date12,idSalarie).size();

        /**************************!!!!!!!!refus de demande  *******************************************/
        if (NombreEquipeActive == 0) {

            throw new NullValueException("NombreEquipeActive");
        }
        /***********************!!!!!!!!!!!refus de demande ********************************************/
        Double BeneficeSalarieparEquipe = ((beneficeService.ChiffreAffaireSalarieparEquipe(idSalarie,idEquipe,annee))-(beneficeService.ChargeSalarie(idSalarie,annee)/(NombreEquipeActive)));
        Double BeneficeEquipe =( beneficeService.ChiffreAffaireEquipe(idEquipe, annee))-(beneficeService.ChargeEquipe(idEquipe, annee));
        return ((BeneficeSalarieparEquipe*100)/BeneficeEquipe);
    }
    //pourcentage contribution d'un equipe par raport au departemet
    @Transactional(readOnly = true)
    public  Double PourcentageContributionEquipeParDepartement(Long idequipe , Long iddepartement , Integer annee)
    {
        if (idequipe == null) {
            throw new NullValueException("idequipe");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }
        if (iddepartement == null) {
            throw new NullValueException("iddepartement");
        }
        return ((this.BeneficeEquipe(idequipe,annee)*100)/(this.BeneficeDepartement(iddepartement, annee)));

    }
    //pourcentage de contibution d'un departement par raport au socite
    @Transactional(readOnly = true)
    public  Double PourcentageContributionDepartementParSociete( Long iddepartement , Long idsocite, Integer annee)
    {
        if (iddepartement == null) {
            throw new NullValueException("iddepartement");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }
        if (idsocite == null) {
            throw new NullValueException("idsocite");
        }
        return ((this.BeneficeDepartement(iddepartement, annee)*100)/(this.BeneficeSociete(idsocite,annee)));

    }
    //pourcentage de contribution d'un salarie dans la socite
    @Transactional(readOnly = true)
    public Double PourcentageContributionparSociete(Long idSalarie  ,Integer  annee )
    {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }

        Double totaleContrubition = 0.0 ;

        List<Long> Equipes  ;
        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();

        Equipes=appartientService.EquipeActiveparSalarie(date11,date12,idSalarie);
        //Double BeneficeSociete = (beneficeService.ChiffreAffaireSociete(idSociete, annee))-(beneficeService.ChargerSociete(idSociete, annee));

        for(Long equipeid : Equipes)
        {
            Equipe equipe = EquipeService.finfbyid(equipeid);
            totaleContrubition +=((PourcentageContributionparEquipe(idSalarie , equipeid , annee)*(this.PourcentageContributionEquipeParDepartement(equipe.getId(),equipe.getDepartement().getId(),annee))*(this.PourcentageContributionDepartementParSociete(equipe.getDepartement().getId(),equipe.getDepartement().getSociete().getId_societe(),annee)))/10000);
        }

        return totaleContrubition ;


    }
    @Transactional(readOnly = true)
    //calculate
    public  Double MaxprimSalarieParEquipe ( Salarie salarie , Equipe equipe , Integer annee, Date date11 ){
        if (annee == null) {
            throw new NullValueException("annee");
        }

        if (salarie == null) {
            throw new NullValueException("salarie");
        }
        if (equipe == null) {
            throw new NullValueException("equipe");
        }
        Double budgetEquipe;
        Double pourcentageContribution = this.PourcentageContributionparEquipe(salarie.getId(),equipe.getId(),annee);

        if (appartientService.estResponsable(true,salarie.getId(),equipe.getId()))
        {

            Integer idbudget=  budgetEquipeService.BudgetEquipe(date11 , equipe.getId());
            budgetEquipe=budgetEquipeService.finfbyid(idbudget).getMontant_manager();

            return  ((budgetEquipe*pourcentageContribution)/100);
        }
        Integer idbudget=  budgetEquipeService.BudgetEquipe(date11 , equipe.getId());
        budgetEquipe=budgetEquipeService.finfbyid(idbudget).getMontant_horsmanager();

        return  ((budgetEquipe*pourcentageContribution)/100);


    }
    @Transactional(readOnly = true)
    public  Boolean Consommation(Salarie salarie , Integer annee , Double Primfinale)
    {
        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date3=null;
        try {
            date3= df.parse(date11);
        } catch (ParseException e){}
        List<Long> Equipes  ;
        Equipes=appartientService.EquipeActiveparSalarie(date11,date12,salarie.getId());
        Double budgetEquipe;
        Double Consomation = 0.0;
        Double pourcentageContribution ;
        for(Long equipeid : Equipes) {
            Equipe equipe1 = EquipeService.finfbyid(equipeid);
            pourcentageContribution = this.PourcentageContributionparEquipe(salarie.getId(),equipe1.getId(),annee);
            if (appartientService.estResponsable(true, salarie.getId(), equipe1.getId())) {
                Integer idbudget = budgetEquipeService.BudgetEquipe(date3, equipe1.getId());
                budgetEquipe = budgetEquipeService.finfbyid(idbudget).getMontant_manager();
                Consomation = (Primfinale*pourcentageContribution)/100;

                BudgetEquipe BDE = budgetEquipeService.finfbyid(idbudget);
                BDE.setConsommation_manager(Consomation+BDE.getConsommation_manager());
                BDE.getBudgetDepartement().setConsommation(Consomation+ BDE.getBudgetDepartement().getConsommation());
                BDE.getBudgetDepartement().getBudget().setConsommation(Consomation+ BDE.getBudgetDepartement().getBudget().getConsommation());
                return true;
            }
            Integer idbudget = budgetEquipeService.BudgetEquipe(date3, equipe1.getId());
            budgetEquipe = budgetEquipeService.finfbyid(idbudget).getMontant_horsmanager();
            Consomation =( Primfinale*pourcentageContribution)/100;
            BudgetEquipe BDE = budgetEquipeService.finfbyid(idbudget);
            BDE.setConsommation_horsmanager(Consomation+BDE.getConsommation_horsmanager());
            BDE.getBudgetDepartement().setConsommation(Consomation+ BDE.getBudgetDepartement().getConsommation());
            BDE.getBudgetDepartement().getBudget().setConsommation(Consomation+ BDE.getBudgetDepartement().getBudget().getConsommation());

            return true;
        }
        return false ;
    }
    @Transactional(readOnly = true)
    public Double  PrimeMaxSalarieParSociete  (Long idsalarie , Integer annee ) {
        if (idsalarie == null) {
            throw new NullValueException("idsalarie");
        }
        if (annee  == null) {
            throw new NullValueException("annee ");
        }
        List<Long> Equipes  ;
        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date3=null;
        try {
            date3= df.parse(date11);
        } catch (ParseException e){

        }
        Salarie salarie = this.finfbyid(idsalarie);

        Equipes=appartientService.EquipeActiveparSalarie(date11,date12,salarie.getId());
        Double PrimMax = 0.0;
        for(Long equipeid : Equipes)
        { Equipe equipe1 = EquipeService.finfbyid(equipeid);
            PrimMax += this.MaxprimSalarieParEquipe(salarie,equipe1 ,annee,date3);
        }
        return PrimMax;

    }
}
