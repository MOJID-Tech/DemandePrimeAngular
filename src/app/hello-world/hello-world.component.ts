import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demande } from '../demande';
import { DemandeService } from '../demande.service';
import { Salarie } from '../salarie';
import { Users } from '../Users';

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.css']
})
export class HelloWorldComponent implements OnInit {

  message: string;
/*
  constructor(private helloWorldService: HelloWordService) { }

  ngOnInit() {

    console.log("HelloWorldComponent");
    this.helloWorldService.helloWorldService().subscribe( (result) => {
      this.message = result.content;
    });
  }

constructor() {}
ngOnInit() {}

*/
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
      
      var test:number = 40 ;
      this.demandeService.createDemandee(test,'Mojid')
        .subscribe(data => console.log(data), error => console.log(error));
        this.demande = new Demande();
          this.gotoList();
    }

    onSubmit() {
      this.submitted = true;
      this.save();
    }

    gotoList() {
      this.router.navigate(['/liste-prime']);
    }
}



