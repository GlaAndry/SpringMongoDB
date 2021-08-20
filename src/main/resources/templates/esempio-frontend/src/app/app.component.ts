import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public employees: Employee[];
  
  constructor(private employeeService: EmployeeService) {
    this.employees = [];
  }

  ngOnInit(){
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
}
