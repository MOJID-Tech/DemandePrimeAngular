import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demande } from '../demande';
import { DemandeService } from '../demande.service';
import { Salarie } from '../salarie';
import { Users } from '../Users';

@Component({
  selector: 'app-demande-prime',
  templateUrl: './demande-prime.component.html',
  styleUrls: ['./demande-prime.component.css']
})

export class DemandePrimeComponent implements OnInit {
    demande: Demande = new Demande();
    submitted = false;
    users : Users = new Users();
   ngOnInit() {
     }
   constructor(private demandeService: DemandeService,
      private router: Router) { }

  newDemande(): void {
      this.submitted = false;
      this.demande = new Demande();
      this.demande.salarie = this.users.salarie;
    }

    save() {

      this.demande.date_debut=new Date();
      this.demandeService.createDemande(this.demande)
        .subscribe(data => console.log(data), error => console.log(error));
        this.demande = new Demande();
          this.gotoList();
    }

    onSubmit() {
      this.submitted = true;
      this.save();
    }

    gotoList() {
      this.router.navigate(['/demande-prime']);
    }

}
