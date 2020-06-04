import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class DemandeService {

   private baseUrl = 'http://localhost:8080/springboot-crud-rest/api/v1/demande';
   constructor(private http: HttpClient) {}


   createDemande(demande: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, demande);
      }

    getDemandeList(): Observable<any> {
       return this.http.get(`${this.baseUrl}`);
     }

}



