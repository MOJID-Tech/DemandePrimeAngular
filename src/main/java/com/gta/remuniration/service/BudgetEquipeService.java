package com.gta.remuniration.service;
import com.gta.remuniration.entity.*;
import com.gta.remuniration.exception.InsufficientRightException;
import com.gta.remuniration.exception.MontantDepasseException;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.BudgetDepartementRepository;
import com.gta.remuniration.repository.BudgetEquipeRepository;

import com.gta.remuniration.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service

public class BudgetEquipeService {

    @Autowired
    private EquipeRepository equipesRepository;
    @Autowired
    private BudgetDepartementRepository budgetDepartementRepository;
    @Autowired
    private BudgetEquipeRepository repository;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private BudgetDepartService budgetDepartService;



    @Transactional(readOnly = false)
    public BudgetEquipe create(String  BudgetH , String BudgetM , String IDEquipe) {

        Double montant_budgetm = Double.parseDouble(BudgetM);
        Double montant_budgeth = Double.parseDouble(BudgetH);
        long nequipe = Long.parseLong(IDEquipe);
        Date datebudget = java.util.Calendar.getInstance().getTime();
        Double consomretire=0.0;
        //  Integer nequipe = Integer.valueOf(IDEquipe);
        if (montant_budgetm == null || montant_budgeth == null ) {
            throw new NullValueException("montant_budget");
        }
        BudgetEquipe budgetequipe = this.getByID(nequipe);
        Equipe equipe=null;
        BudgetDepartement bd =null;
        System.out.println("etape 1");

        if(budgetequipe==null)
        {
            budgetequipe=new BudgetEquipe();
            budgetequipe.setDate_debut(datebudget);
            budgetequipe.setConsommation_horsmanager(0);
            budgetequipe.setConsommation_manager(0);
            equipe=equipeService.getByID(nequipe);
            budgetequipe.setEquipe(equipe);
            Long iddepar=equipe.getDepartement().getId();
            bd=budgetDepartService.getByID(iddepar);
            budgetequipe.setBudgetDepartement(bd);
            budgetDepartementRepository.save(bd);
            System.out.println(" INSERT NEW ROW ");
            System.out.println("etape 2 : Equipe"+equipe.getNom_equipe()+" Budget Departement "+bd.getId()+" mon"+bd.getMontant());

        }
        else {


            budgetequipe.setDate_fin(datebudget);
            equipe=budgetequipe.getEquipe();
            bd=budgetequipe.getBudgetDepartement();
            System.out.println("***************UPDATE ROW ***************** ");
            System.out.println("etape 2"+bd.getId()+" mon"+bd.getMontant());
            consomretire=budgetequipe.getMontant_horsmanager()+budgetequipe.getMontant_manager();
            System.out.println("consommation de budget equuoe "+consomretire);
        }

        budgetequipe.setMontant_horsmanager(montant_budgeth);
        budgetequipe.setMontant_manager(montant_budgetm);

        validate(budgetequipe,IDEquipe,consomretire);
        System.out.println("etape 3 : validation ");

        // calcul de pourcentage
        Double somme_montant = montant_budgeth+montant_budgetm;

        // pourcentage Hors Mangaer
        Double pourcentageh =(double)(montant_budgeth/somme_montant);


        //pourcentage Manager
        Double pourcentagem =(double)(montant_budgetm/somme_montant);
        // pourcentagem=pourcentagem*100;

        System.out.println(" etape 3 : calcul est termine");

        budgetequipe.setPourcentage_manager(pourcentagem);
        budgetequipe.setPourcentage_horsmanager(pourcentageh);


        somme_montant=somme_montant+bd.getConsommation();
        //modifier somme montant selon udpate

        somme_montant=somme_montant-consomretire;

        bd.setConsommation(somme_montant);
        repository.save(budgetequipe);
        System.out.println("etape 4");


        return budgetequipe;


    }



    private void validate(BudgetEquipe budgetToSave , String IDEquipe,Double retire) {
        if (IDEquipe == null) {
            throw new InsufficientRightException();
        }

        List<String> budgetNullFieldList = new ArrayList<>();
        if (budgetToSave.getMontant_horsmanager() == 0 || budgetToSave.getMontant_manager() ==0 ) {
            budgetNullFieldList.add("Montant_budget");
        }

        if (!budgetNullFieldList .isEmpty()) {
            throw new NullValueException(budgetNullFieldList );
        }
        long nequipe = Long.parseLong(IDEquipe);
        Double summontant,rest;
        summontant =budgetToSave.getMontant_horsmanager()+budgetToSave.getMontant_manager();
        rest=budgetToSave.getBudgetDepartement().getMontant()-budgetToSave.getBudgetDepartement().getConsommation();
        rest=rest+retire;
        if(summontant>rest)
        {
            throw new MontantDepasseException("Montant Depassé");

        }

    }




    public BudgetEquipe getByID(Long id)
    {

        List<BudgetEquipe> budgetEquipes = repository.findAll();
        BudgetEquipe budgetEquipe = null;
        System.out.println("n : "+id);



        for(int i=0;i<budgetEquipes.size();i++)
        {
            //   long idtrouve =   budgetEquipes.get(i).getEquipe().getId();

            System.out.println("n parcourir : "+budgetEquipes.get(i).getEquipe().getId());

            System.out.println("compare id estime "+id+"avec id equipe parcourir "+budgetEquipes.get(i).getEquipe().getId());
            if(Long.compare(id,budgetEquipes.get(i).getEquipe().getId())==0)
            {
                budgetEquipe = budgetEquipes.get(i);

                return budgetEquipe;
            }
        }

        return null;
    }





    @Transactional(readOnly = true)
    public  BudgetEquipe finfbyid(Integer  id ){
        BudgetEquipe  budgetEquipe;
        //exception
        return budgetEquipe  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BudgetEquipe.class, id));
    }
    @Transactional(readOnly = true)
    public Integer BudgetEquipe(Date date_fin , Long equipeid ) {
       /* if (date_fin == null) {
            throw new NullValueException("date_fin");
        }*/
        if (equipeid== null) {
            throw new NullValueException("equipeid");
        }


        return (repository.findActiveBudgetEquipe(date_fin, equipeid));


    }

}
