package com.gta.remuniration.service;

import com.gta.remuniration.entity.*;
import com.gta.remuniration.exception.InsufficientRightException;
import com.gta.remuniration.exception.MontantDepasseException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.BudgetDepartementRepository;
import com.gta.remuniration.repository.BudgetEquipeRepository;
import com.gta.remuniration.repository.EquipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetEquipeService {

    @Autowired
    private EquipesRepository equipesRepository;
    @Autowired
    private BudgetDepartementRepository budgetDepartementRepository;
    @Autowired
    private BudgetEquipeRepository budgetEquipeRepository;
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

        }

        budgetequipe.setMontant_horsmanager(montant_budgeth);
        budgetequipe.setMontant_manager(montant_budgetm);

        validate(budgetequipe,IDEquipe);
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



        bd.setConsommation(somme_montant);
        budgetEquipeRepository.save(budgetequipe);
        System.out.println("etape 4");


        return budgetequipe;


    }


    private void validate(BudgetEquipe budgetToSave , String IDEquipe) {
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
        if(summontant>=rest)
        {
            throw new MontantDepasseException("Montant Depassé");

        }

    }


    

    public BudgetEquipe getByID(Long id)
    {

        List<BudgetEquipe> budgetEquipes = budgetEquipeRepository.findAll();
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


}
