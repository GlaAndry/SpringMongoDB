
//Identifichiamo gli elementi che compongono
//un employee
export interface Employee {
  //E' necessario che venga rispettata 
  //la scrittura degli attributi nella
  //classe entity, per questo Id è maiuscolo
  id: string;
  name: string;
  email: string;
  phone: string;
  code: string;
}
