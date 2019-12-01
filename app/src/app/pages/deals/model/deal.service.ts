import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Deal} from "./deal";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DealService {

  private API_PATH = environment.API + 'deals';

  constructor(private http: HttpClient) {
  }

  listAll(): Observable<Deal[]> {
    return this.http.get<Deal[]>(this.API_PATH).pipe(
      catchError(this.handleError),
      map(this.jsonDataToDeals)
    );
  }

  getById(id: number): Observable<Deal> {
    return this.http.get<Deal>(`${this.API_PATH}/${id}`).pipe(
      catchError(this.handleError),
      map(this.jsonDataToDeal)
    );
  }

  insert(deal: Deal): Observable<Deal> {
    return this.http.post(this.API_PATH, deal).pipe(
      catchError(this.handleError),
      map(this.jsonDataToDeal)
    );
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.API_PATH}/${id}`).pipe(
      catchError(this.handleError),
      map(() => null)
    );
  }

  update(deal: Deal): Observable<Deal> {
    return this.http.put(`${this.API_PATH}/${deal.id}`, deal).pipe(
      catchError(this.handleError),
      map(() => deal)
    );
  }

  private handleError(error: any): Observable<any> {
    console.log('ERRO NA REQUISIÇÃO =>', error);
    return throwError(error);
  }

  private jsonDataToDeals(jsonData: any): Deal[] {
    const deals: Deal[] = [];
    jsonData['content'].forEach(value => deals.push(value as Deal));

    return deals;
  }

  private jsonDataToDeal(jsonData: any): Deal {
    return jsonData as Deal;
  }
}
