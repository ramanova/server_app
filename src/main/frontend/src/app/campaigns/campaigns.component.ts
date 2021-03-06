import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {CampaignsService} from "../campaigns.service";
import {Campaign} from "../campaign";


@Component({
  selector: 'app-campaigns',
  templateUrl: './campaigns.component.html',
  styleUrls: ['./campaigns.component.scss']
})
export class CampaignsComponent implements OnInit {
  @Input()
  allCampaigns$: Observable<Campaign[]>;

  constructor(private campaignsService: CampaignsService) {
    this.allCampaigns$ = campaignsService.getCampaigns();
  }

  ngOnInit(): void {
  }
}
