import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demande } from '../demande';
import { DemandeService } from '../demande.service';
import { Observable } from "rxjs";
@Component({
  selector: 'app-list-demande',
  templateUrl: './list-demande.component.html',
  styleUrls: ['./list-demande.component.css']
})
export class ListDemandeComponent implements OnInit {
   demandes: Observable<Demande[]>;

   ngOnInit() { this.reloadData(); }
   constructor(private demandeService: DemandeService,
   private router: Router) { }

    reloadData() {
       this.demandes = this.demandeService.getDemandeList();
     }


}
