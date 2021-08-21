import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { environment } from 'src/environments/environment';

//creato tramite angular cli con comando: ng generate service employee

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

    private apiServerUrl = environment.apiBaseUrl; //preso da env
    //${this.apiServerUrl} non prende il valore??? --> serve `` non '' o ""


    constructor(private http: HttpClient) {}


    public getEmployees(): Observable<Employee[]> {
        return this.http.get<Employee[]>(`${this.apiServerUrl}/api/v1/employee/findAll`)
    }

    public addEmployee(employee: Employee): Observable<Employee> {
        return this.http.post<Employee>('http://localhost:8080/api/v1/employee/addEmployee', employee)
    }

    public updateEmployee(employee: Employee): Observable<Employee> {
        return this.http.put<Employee>('http://localhost:8080/api/v1/employee/updateEmployee', employee)
    }

    //Variabile: Tipo ----- Observable(ritorno) --> void per delete
    public deleteEmployeeById(employeeId: string): Observable<void> {
        return this.http.delete<void>(`http://localhost:8080/api/v1/employee/deleteEmployee/${employeeId}`)
    }
}
