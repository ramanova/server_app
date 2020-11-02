import { Component, OnInit } from '@angular/core';
import { Campaign } from "../campaign";

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.scss']
})
export class CampaignComponent implements OnInit {
  campaigns: Campaign = {
    id: 0,
    name: "my_campaign_1",
    data: "",
    cap: {
      maxCountPerUser: 3,
      maxCount: 10,
    }
  };

  constructor() { }

  ngOnInit(): void {
  }

}
