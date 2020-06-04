import { Demande } from './demande';
import { Users } from './Users';

export class Salarie {
 id :  number;
 nom_salarie : string ;
 prenom_salarie : string ;
 email_salarie : string ;
 tel_salarie : string ;
 date_naissance : Date ;
 demandes : Demande [];
 users : Users  [];
}
