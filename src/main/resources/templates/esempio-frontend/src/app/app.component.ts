import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public employees?: Employee[];
  public editEmployee?: Employee;
  public deleteEmployee?: Employee;

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    //funzione richiamata ogni volta che il componente viene
    //inizializzato in quanto stiamo implementando OnInit
    this.getEmployess();
  }

  public getEmployess(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //prende i dati in input e li invia verso la rest
  //Api per aggiungere un nuovo elemento al dB
  public onAddEmployee(addForm: NgForm): void {

    //value è individuato da una rappresentazione JSON 
    //dei campi di interesse all'interno della form.
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployess(); //ricarico gli impiegati
        //clear form
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
    //chiudo il modal premendo il bottone cancel da codice
    document.getElementById('add-employee-form')?.click();

  }


  //funzione che si occupa dell'eliminazione di un 
  //impiegato dal sistema
  public onDeleteEmployee(id: string): void {

    // this.employeeService.deleteEmployeeById(employee.id).subscribe(
    //   (response: string) => {

    //   }
    // )


  }

  public onUpdateEmployee(employee: Employee): void {

    //Il valore di employee è preso tramite la ngForm dall'Html
    employee.Id = '611f5b0c41c78c587fe0d302';
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        console.log(employee.Id);
        this.getEmployess(); //ricarico gli impiegati
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      } 
    );
  }

  //Funzione per l'apertura dei diversi Modal.
  //Employee | null significa che può essere di entrambi i tipi! (Necessario per l'Add)
  public onOpenModal(employee: Employee | null, mode: string): void {

    //alert("Bottone cliccato!");

    //Creazione del bottone.
    const btn = document.createElement('button');
    btn.type = 'button'; //modifico il type di default (Submit) in button
    btn.style.display = 'none'; //hide button in UI

    btn.setAttribute('data-toggle', 'modal'); //imposto data-toggle='modal'

    //ricavo il container definito nell'HTML tramite ID
    const container = document.getElementById('main-container');


    if (mode === 'add') { //addModal
      btn.setAttribute('data-target', '#addEmployeeModal'); //id del modal nell'HTML
    } else if (mode === 'edit') {
      //set dell'employee che è stato scelto dall'utente
      if(employee != null){
        this.editEmployee = employee;
      }
      btn.setAttribute('data-target', '#updateEmployeeModal');
    } else if (mode === 'delete') {
      if (employee != null) { //verifico che employee sia != da null
        this.deleteEmployee = employee;
      }
      btn.setAttribute('data-target', '#deleteEmployeeModal');
    } else {
      alert('Ciao');
    }

    //aggiungo il bottone alla Div
    container?.appendChild(btn);
    btn.click();

  }


}
