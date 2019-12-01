import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {environment} from "../../../../environments/environment";
import {BuyOption} from "./buy-option";

@Injectable({
  providedIn: 'root'
})
export class BuyOptionService {

  private API_PATH = environment.API + 'buyOptions';

  constructor(private http: HttpClient) {
  }

  getById(id: number): Observable<BuyOption> {
    return this.http.get<BuyOption>(`${this.API_PATH}/${id}`).pipe(
      catchError(this.handleError),
      map(this.jsonDataToBuyOption)
    );
  }

  insert(deal: BuyOption): Observable<BuyOption> {
    return this.http.post(this.API_PATH, deal).pipe(
      catchError(this.handleError),
      map(this.jsonDataToBuyOption)
    );
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.API_PATH}/${id}`).pipe(
      catchError(this.handleError),
      map(() => null)
    );
  }

  update(deal: BuyOption): Observable<BuyOption> {
    return this.http.put(`${this.API_PATH}/${deal.id}`, deal).pipe(
      catchError(this.handleError),
      map(() => deal)
    );
  }

  private handleError(error: any): Observable<any> {
    console.log('ERRO NA REQUISIÇÃO =>', error);
    return throwError(error);
  }

  private jsonDataToBuyOption(jsonData: any): BuyOption {
    return new BuyOption(jsonData);
  }
}
