import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Campaign} from "./campaign";


@Injectable({
  providedIn: 'root'
})
export class CampaignsService {

  private readonly URL = 'http://localhost:8080/api/campaigns/all';
  private readonly URL_SUBMIT = 'http://localhost:8080/api/campaigns';
  constructor(private http: HttpClient) { }

  private setHeaders(): HttpHeaders {
    const headersConfig = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'POST, OPTIONS, GET',
    };

    return new HttpHeaders(headersConfig);
  }

  getCampaigns() {
    return this.http.get<Campaign[]>(this.URL);
  }

  save(campaignData: Campaign) {
    let req = this.http.post<Campaign>(this.URL_SUBMIT, campaignData, {headers: this.setHeaders()});
    req.subscribe();
  }

}
