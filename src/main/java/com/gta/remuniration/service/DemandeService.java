package com.gta.remuniration.service;
import com.gta.remuniration.entity.*;
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
import java.util.Calendar;
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
    @Autowired
    private UserService userService;
    @Autowired
    private user_roleService user_roleService ;
    @Autowired
    private RoleService roleService ;




    //findAll
  /* @Transactional(readOnly = true)
    public Page<Demande> findAll(int pageIndex, int size) {
        Pageable pageable = new PageRequest(pageIndex, size );
        return repository.findAll(pageable);
    }*/
    //find by id
    @Transactional(readOnly = true)
    public Demande finfbyid( Integer id ){
        Demande demande;
        return demande = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Etat.class, id));
    }
    //create
   @Transactional(readOnly = false)
    public Demande create(String  Montant_nett , String login) {
      Double  Montant_net=Double.parseDouble(Montant_nett );
        Demande demande =new Demande();
        if ( Montant_net  == null) {
            throw new NullValueException("Montant_net");
        }
        demande.setMontant_net(Montant_net);
       demande.setPrime_finale(0.0);
       demande.setPrime_pdg(0.0);
       demande.setMontant_brut(0.0);
       demande.setPrime_manager(0.0);
       demande.setValide_dG(false);
       demande.setValideM(false);
        validate(demande,login);
        //Date datedemande = java.util.Calendar.getInstance().getTime();
       Date datedemande= new Date() ;
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(datedemande);
       Integer annee = calendar.get(Calendar.YEAR);
        demande.setDate_debut(datedemande);
        demande.setMontant_brut(null);
      Salarie salarie = salarieService.findbylogin(login);
      //test sur l'anne et est ce  salarie a deja pris un prime
      demande.setSalarie(salarie );
        Double maxPrim = salarieService.PrimeMaxSalarieParSociete(demande.getSalarie().getId(), annee-1);
        demande.setPrime_maximale(maxPrim);
        Double pourcentage = salarieService.PourcentageContributionparSociete(demande.getSalarie().getId(), annee-1);
        demande.setPourcentageContribution(pourcentage);
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

    @Transactional(readOnly = true)
    //demandes manager
    public List<Demande> getDemandeListMg() {
        return repository.findByValideM( false);

    }
    @Transactional(readOnly = true)
    //demmandes DG
    public List<Demande> getDemandeListDg() {
        return repository.findByValideDG( false);

    }
    @Transactional(readOnly = false)
    //validation_Manager
    public void validation_Manager (Integer  id_demande , Double Prim_Manager , Double Prim_DG ) {
        Demande demande = this.finfbyid(id_demande);
        Date dateSys= new Date() ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSys);
        //tester si la demande est validé
        Etat etatvalidation = etatService.finfbyid(2);// etat :validé_par_manager
       // EtatDemande etatDemande =
        //si oui c'est dir la demande est refuser par le directeur generale le premier refus
        if (etatDemandeService.findbyEtatAndDemande(etatvalidation, demande) != null) {         // tester si le montant saisi par le manager et different au montant precedant
            if (demande.getPrime_pdg().equals(Prim_Manager)) {     // si oui c'est à dir qu'il a confirmer le changemant de directeur generale
                demande.setPrime_manager(Prim_DG);
                demande.setPrime_finale(Prim_DG);
                demande.setDate_fin(dateSys);
                demande.setDate_validation(dateSys);
                demande.setValide_dG(true);
                demande.setValide_manager(true);
                Etat validationDG = etatService.finfbyid(5);// etat :validé_par_DG et manager
                EtatDemande etatDemande1 = new EtatDemande(dateSys, validationDG, demande);
                etatDemandeService.create(etatDemande1);
                //regler consomation
                //regler notification
            }
            // si non danc il faut une revalidation par le Directeur generale
            else {
                demande.setPrime_manager(Prim_Manager);
                demande.setValide_dG(false);
                demande.setValide_dG(true);
                Etat revalidationManeger = etatService.finfbyid(3);// etat :revalidé_par_Manager
                EtatDemande etatDemande2 = new EtatDemande(dateSys, revalidationManeger, demande);
                etatDemandeService.create(etatDemande2);


            }
            // si non c'est à dir la premiere validation par le manager pour ce demande
        }
         else
             {
            demande.setPrime_manager(Prim_Manager);
            demande.setValide_manager(true);
            Etat validationManeger = etatService.finfbyid(2);// etat validé_par_Manager
            EtatDemande etatDemande3 = new EtatDemande(dateSys, validationManeger, demande);
            etatDemandeService.create(etatDemande3);
        }

    }
    @Transactional(readOnly = false)
    //validation_DG
    public void validation_DG (Integer  id_demande , Double Prim_Manager , Double Prim_DG )
    {
        // si le directeur generale a valider une demande c'est à dire qu'il a accepter le prime proposé par le manager
        Demande demande = this.finfbyid(id_demande);
        Date dateSys= new Date() ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSys);
        demande.setPrime_manager(Prim_Manager );
        demande.setPrime_finale(Prim_Manager );
        demande.setDate_fin(dateSys);
        demande.setDate_validation(dateSys);
        demande.setValide_dG(true);
        Etat validationDG = etatService.finfbyid(5);// etat :validé_par_DG
        EtatDemande etatDemande1=new EtatDemande(dateSys,validationDG,demande );
        etatDemandeService.create(etatDemande1);
        //regler consomation
        //regler notification

    }
    @Transactional(readOnly = false)
    //refu_manager
    public void refus_Manager (Integer  id_demande , Double Prim_Manager , Double Prim_DG )
    {
        Demande demande = this.finfbyid(id_demande);
        Date dateSys= new Date() ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSys);
        demande.setPrime_manager(Prim_Manager );
        demande.setPrime_finale(0.0);
        demande.setDate_fin(dateSys);
        demande.setValide_dG(true);
        demande.setValideM(true);
        Etat refusManager = etatService.finfbyid(4);// etat : refusé par manager
        EtatDemande etatDemande1=new EtatDemande(dateSys,refusManager,demande );
        etatDemandeService.create(etatDemande1);
        //notification de refus

    }
    @Transactional(readOnly = false)
    //refus_DG
    public void refus_DG (Integer  id_demande , Double Prim_Manager , Double Prim_DG )
    {
        Demande demande = this.finfbyid(id_demande);
        Date dateSys= new Date() ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSys);
        //tester si c'est le premier refus du directeur ou c'est un demande deja refusé
        Etat etatvalidation = etatService.finfbyid(6);// etat :validé_par_manager
        EtatDemande etatDemande = etatDemandeService.findbyEtatAndDemande(etatvalidation, demande);
        if(etatDemande==null)
        {    //si oui : le premier refus alors retraitement par le manager
            demande.setPrime_pdg( Prim_DG);
            demande.setValide_manager(false);
            demande.setValide_dG(true);
            Etat refusDG1 = etatService.finfbyid(6);// etat : refusé par manager
            EtatDemande etatDemande1 = new EtatDemande(dateSys, refusDG1, demande);
            etatDemandeService.create(etatDemande1);

        }

        // si non : 2éme refus alors validation de prime par buget directeur

      else {
            demande.setPrime_pdg(Prim_DG);

            demande.setPrime_finale(Prim_DG);
            demande.setDate_fin(dateSys);
            demande.setDate_validation(dateSys);
            demande.setValide_dG(true);
            Etat refusDG2 = etatService.finfbyid(7);// etat : refusé par manager
            EtatDemande etatDemande2 = new EtatDemande(dateSys, refusDG2, demande);
            etatDemandeService.create(etatDemande2);
            Etat validationDG = etatService.finfbyid(5);// etat : refusé par manager
            EtatDemande etatDemande3 = new EtatDemande(dateSys, validationDG, demande);
            etatDemandeService.create(etatDemande3);

            //notification
            //consomation
        }
    }
    @Transactional(readOnly = false)
    //valider une prime
    public Boolean validerPrime(Integer  id_demande , String Prim_Managerr , String Prim_DGG , String Login )
    {
        if ( id_demande  == null) {
        throw new NullValueException("id_demande");
        }
        if ( Login  == null) {
            throw new NullValueException("Login");
        }
        Double Prim_Manager=Double.parseDouble(Prim_Managerr );
        Double Prim_DG=Double.parseDouble(Prim_DGG );
        User user = userService.findByLogin(Login);

        //user_role userole = user_roleService.finfbyroleAndUser(5,user.getId());
        //user.getUser_Role().contains("DG");

       if(user_roleService.finfbyroleAndUser(5,user.getId())!=null)
       {
          validation_DG( id_demande , Prim_Manager , Prim_DG );
          return  true;
       }
        validation_Manager( id_demande ,  Prim_Manager ,  Prim_DG );
       return  true;
    }
    @Transactional(readOnly = false)
    //refuser une prime
    public Boolean refuserPrime(Integer  id_demande , String Prim_Managerr , String Prim_DGG ,String Login )
    {
        if ( id_demande  == null) {
            throw new NullValueException("id_demande");
        }
        if ( Login  == null) {
            throw new NullValueException("Login");
        }
        Double Prim_Manager=Double.parseDouble(Prim_Managerr );
        Double Prim_DG=Double.parseDouble(Prim_DGG );
        User user = userService.findByLogin(Login);
        //user_role userole = user_roleService.finfbyroleAndUser(5,user.getId());
        if(user_roleService.finfbyroleAndUser(5,user.getId())!=null)
        {
           refus_DG( id_demande ,  Prim_Manager ,  Prim_DG);
           return  true;
        }

         refus_Manager(id_demande ,  Prim_Manager ,  Prim_DG);
        return  true;
    }



    public List<Demande> getDemandes()
    {


        return repository.findAll();
        // List<Demande> demandes=repository.findAll();
       /*
        List<Demande> historiques =null;

        for(int i=0;i<demandes.size();i++) {
            if(demandes.get(i).getDate_fin()!=null)
            {
                historiques.add(demandes.get(i));
            }

        }


       List<Demande> demandes=repository.findAll();
        List<Demande> Historiques = null;
        for(int i=0;i<demandes.size();i++)
        {
            System.out.println(demandes.get(i).getId());
            if(demandes.get(i).getDate_fin()!=null)
            {
                Historiques.add(demandes.get(i));
            }
        }
        return Historiques;
         */
    }

}


