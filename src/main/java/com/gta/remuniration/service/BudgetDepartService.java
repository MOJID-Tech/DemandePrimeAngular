package com.gta.remuniration.service;

import com.gta.remuniration.entity.Budget;
import com.gta.remuniration.entity.BudgetDepartement;
import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.exception.InsufficientRightException;
import com.gta.remuniration.exception.MontantDepasseException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.BudgetDepartementRepository;
import com.gta.remuniration.repository.BudgetRepository;
import com.gta.remuniration.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BudgetDepartService {

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private BudgetDepartementRepository BudgetDepartRepository;
    @Autowired
    private DepartementRepository DepartRepository;
    @Autowired
    BeneficeService beneficeservice;
    @Autowired
    DepartementService departementService;

    public Double calculPourcentage(Double montant_budget,Double benefice)
    {

        Double pourcentage = (double)(montant_budget/benefice);
        System.out.println("benefice :"+benefice + " pourcentage"+pourcentage);
        // pourcentage = ((double)(montant_budget/benefice))*100; /* casting your division result to (double) means
        System.out.println("etape 4 budget departement");

        return  pourcentage;

    }



    @Transactional(readOnly = false)
    public BudgetDepartement create(String  Budget , String IDdepart) {


        Double montant_budget = Double.parseDouble(Budget);
        com.gta.remuniration.entity.Budget budget = null;
        Departement departement = null;


        long ndepartement = Long.parseLong(IDdepart);
        if (montant_budget == null) {
            throw new NullValueException("montant_budget");
        }

        System.out.println(
                "etape 1 " +montant_budget +" , " +ndepartement

        );

        Date datebudget = java.util.Calendar.getInstance().getTime();
        BudgetDepartement budgetDepartement =this.getByID(ndepartement);

        if(budgetDepartement==null) {
            budgetDepartement = new BudgetDepartement();
            budget = new Budget();
            budget.setDate_debut(datebudget);
            budget.setConsommation(0);
            departement = departementService.getByID(ndepartement);
            budget.setSociete(departement.getSociete());

            budgetRepository.save(budget);
            budgetDepartement.setDepartement(departement);
            budgetDepartement.setBudget(budget);
            System.out.println("fin etape1 insertion");
        }

        else {
            budget = budgetDepartement.getBudget();
            departement = budgetDepartement.getDepartement();
            budgetDepartement.setDate_fin(datebudget);
        }
        budget.setMontant(montant_budget);
        budgetDepartement.setMontant(montant_budget);

        System.out.println("avant validationn");

        validate(budget, IDdepart);
        System.out.println("etape 2" +budget.getMontant());
        System.out.println("apres validationn");


        System.out.println(
                "etape 3 " +departement.getId() +" nom " +departement.getNom_depar()

        );


        Double benefice =beneficeservice.getBeneficeDepartement(departement.getId());
        Double pourcentage = this.calculPourcentage(montant_budget,benefice);




        budgetDepartement.setConsommation(0);
        budgetDepartement.setPourcentage(pourcentage);
        BudgetDepartRepository.save(budgetDepartement);

        System.out.println("etape 5 budget enregs");

        return budgetDepartement;
    }



    //validate
    private void validate(Budget budgetToSave , String IDdepartement) {
        if (IDdepartement == null) {
            throw new InsufficientRightException();
        }

        List<String> budgetNullFieldList = new ArrayList<>();
        if (budgetToSave.getMontant() == 0) {
            budgetNullFieldList.add("Montant_budget");
        }

        if (!budgetNullFieldList .isEmpty()) {
            throw new NullValueException(budgetNullFieldList );
        }
        long ndepartement = Long.parseLong(IDdepartement);

        if(budgetToSave.getMontant()>=beneficeservice.getBeneficeDepartement(ndepartement))
        {
            throw new MontantDepasseException("Montant Depassé");
        }

    }


    public BudgetDepartement getByID(Long id)
    {

        List<BudgetDepartement> budgetDepartements = BudgetDepartRepository.findAll();
        BudgetDepartement budgetDepartement = null;
        System.out.println("departement "+id);

        for(int i=0;i<budgetDepartements.size();i++)
        {
            System.out.println("departement trouve   "+budgetDepartements.get(i).getDepartement().getId());

            if(budgetDepartements.get(i).getDepartement().getId().equals(id))
            {
                budgetDepartement = budgetDepartements.get(i);
                System.out.println("departement possede budget departement "+id);
                return budgetDepartement;
            }
        }

        return null;
    }
  /*
   public BudgetDepartement getBudget(Long id)
   {
       String idd = null;

       BudgetDepartement budgetDepartement;
       return  BudgetDepartRepository.findByDepartement(id).orElseThrow(() -> new NotFoundException(BudgetDepartement.class, "id", idd));
   }

 */

}
