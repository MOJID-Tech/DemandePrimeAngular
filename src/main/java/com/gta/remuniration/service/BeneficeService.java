package com.gta.remuniration.service;
import com.gta.remuniration.entity.*;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.AppartientRepository;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EtatDemandeRepository;
import com.gta.remuniration.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class BeneficeService {


    @Autowired
    SalarieRepository salarieRepository;
    @Autowired
    SalarieService salarieService;
    @Autowired
    AppartientRepository appartientRepository;


    public Double ChiffreAffaireSalarieparEquipe (Long idSalarie , Long idEquipe , Integer annee ) {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
        if (idEquipe == null) {
            throw new NullValueException("idEquipe");
        }
        if (annee == null) {
            throw new NullValueException("annee");
        }
    // taritemnt
        Random r = new Random();
        int valeur = 0 + r.nextInt(50 - 0);


        return ((double)100);
    }
    public Double ChiffreAffaireEquipe (Long idEquipe, Integer annee ) {
        if (idEquipe == null) {
            throw new NullValueException("idEquipe");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }
        // taritemnt
        Random r = new Random();
        int valeur = 50 + r.nextInt(100 - 50);


        return ((double)1000);
    }
    public Double ChiffreAffaireDepartement(Long idDepartement, Integer annee ) {
        if (idDepartement == null) {
            throw new NullValueException("idDepartement");
        }
        if (annee == null) {
            throw new NullValueException("annee");
        }
        // taritemnt
        Random r = new Random();
        int valeur = 100 + r.nextInt(1000 - 100);


        return ((double)3000);
    }
    public Double ChiffreAffaireSociete(Long idSociete ,Integer annee ) {
        if (idSociete == null) {
            throw new NullValueException("idSociete");
        }
        if (annee == null) {
            throw new NullValueException("annee");
        }
        // taritemnt
        return (40000.0);
    }
    /*******************/
    public Double ChargeSalarie(Long idSalarie ,Integer annee ) {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
        if (annee == null) {
            throw new NullValueException("annee");
        }

        // taritemnt
        return (50.0);
    }
    public Double ChargeEquipe (Long idEquipe, Integer annee ) {
        if (idEquipe == null) {
            throw new NullValueException("idEquipe");
        }

        if (annee == null) {
            throw new NullValueException("annee");
        }
        // taritemnt
        return (100.0);
    }
    public Double  ChargeDepartement(Long idDepartement, Integer annee ) {
        if (idDepartement == null) {
            throw new NullValueException("idDepartement");
        }
        // taritemnt
        return (1000.0);
    }
    public Double ChargerSociete(Long idSociete , Integer annee ) {
        if (idSociete == null) {
            throw new NullValueException("idSociete");
        }
        // taritemnt
        return (10000.00);
    }



    /****************     Benefices         *****************/



    public Double getBeneficeDepartement(Long idDepartement) {

        Double Beneficede;
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();
        System.out.println("Current month: "+currentYear);

        Beneficede = this.ChiffreAffaireDepartement(idDepartement,currentYear)-this.ChargeDepartement(idDepartement,currentYear);
        return Beneficede;
    }





    public Double BeneficeEquipe(Long IDEQUIPE) {
        Double Beneficede;
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();

        Beneficede = this.ChiffreAffaireEquipe(IDEQUIPE,currentYear)-this.ChargeEquipe(IDEQUIPE,currentYear);
        return Beneficede;
    }


    public Double BeneficeManagerEquipe(Long IDEQUIPE) {

        if (IDEQUIPE == null) {
            throw new NullValueException("IDEquipe");
        }



        //Long IDSalarie = new Long(1);
        Double beneficeManager = 0.0;
        Double ChargeManager;

        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();

        int annee =currentYear ;

        LocalDate date1= LocalDate.of( annee , 12 , 30);
        LocalDate date2= LocalDate.of( annee , 01 , 01);
        String date11 =date1.toString();
        String date12 =date2.toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date3=null;
        System.out.print("date avant"+date11 + " "+date12);
        System.out.print(" date "+df);
        Date date4=null;


        try {
            date3=new SimpleDateFormat("dd/MM/yyyy").parse(date11);
            System.out.print(" date avec tran"+date3);
        } catch (ParseException e){

        }


        try {
            date4= df.parse(date12);
        } catch (ParseException e){

        }
        System.out.print("date"+date4);
        System.out.println("hello ");
         /*
         List<Appartient> managers = appartientRepository.findManager(IDEQUIPE);

         for(int i=0;i<managers.size();i++)
         {
             System.out.println("manager "+managers.get(i).getSalarie().getNom_salarie());
             Long idsal=managers.get(i).getSalarie().getId();

             beneficeManager = beneficeManager +salarieService.PourcentageContributionparEquipe(idsal,IDEQUIPE,annee);
         }
        System.out.println("somme"+beneficeManager);
        */
        Long IDSalarie;
        List<Appartient> managers=appartientRepository.findManager(IDEQUIPE);
        for(int i=0;i<managers.size();i++)
        {

            //  System.out.println("manager"+managers.get(i).getSalarie());
            IDSalarie=managers.get(i).getSalarie().getId();
            System.out.println("manger"+managers.get(i).getSalarie());
            beneficeManager = beneficeManager+salarieService.PourcentageContributionparEquipe(IDSalarie,IDEQUIPE,currentYear);

        }

        System.out.println("somme"+beneficeManager);

         /*
       // beneficeManager = this.ChiffreAffaireSalarieparEquipe(IDSalarie,IDEQUIPE,currentYear)-ChargeManager;
          */
        return (beneficeManager);
    }


    public Double BeneficeHorsManagerEquipe(Long IDEQUIPE) {
     /*
        if (IDEQUIPE == null) {
            throw new NullValueException("IDEquipe");
        }

        Long IDSalarie = new Long(1);

        Double BeneficehorsManager;
        BeneficehorsManager=this.BeneficeEquipe(IDEQUIPE)-this.BeneficeManagerEquipe(IDEQUIPE);
        return  (BeneficehorsManager);
    }

    */
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();
        Double BeneficehorsManager = 0.0;
        Long IDSalarie;
        List<Appartient> salaries = appartientRepository.findsalaries(IDEQUIPE);
        for (int i = 0; i < salaries.size(); i++) {

            //  System.out.println("manager"+managers.get(i).getSalarie());
            IDSalarie = salaries.get(i).getSalarie().getId();
            System.out.println("manger" + salaries.get(i).getSalarie());
            BeneficehorsManager = BeneficehorsManager + salarieService.PourcentageContributionparEquipe(IDSalarie, IDEQUIPE, currentYear);

        }

        System.out.println("somme" + BeneficehorsManager);
        return  BeneficehorsManager;
    }

}



