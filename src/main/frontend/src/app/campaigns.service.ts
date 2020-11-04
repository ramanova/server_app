import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, filter, switchMap } from 'rxjs/operators';
import {Campaign} from "./campaign";


@Injectable({
  providedIn: 'root'
})
export class CampaignsService {

  private readonly URL = 'http://localhost:8080/api/campaigns/all';
  constructor(private http: HttpClient) { }

  getCampaigns() {
    return this.http.get<Campaign[]>(this.URL);
  }
}
