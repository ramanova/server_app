import {Component, OnInit} from '@angular/core';
import {Campaign} from "../campaign";
import {FormControl, FormGroup} from "@angular/forms";
import {CampaignsService} from "../campaigns.service";

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.scss']
})

export class CampaignComponent implements OnInit {
  campaign: Campaign = {
    id: 0,
    name: "my_campaign_1",
    data: "",
    cap: {
      maxCountPerUser: 3,
      maxCount: 10,
    }
  };
  campaignForm: FormGroup;

  constructor(private campaignsService: CampaignsService) {
  }

  ngOnInit(): void {
    this.campaignForm = new FormGroup({
      campaignName: new FormControl(),
      maxCountPerUser: new FormControl(),
      maxCount: new FormControl()
    });
  }

  onSubmit(campaignData: Campaign) {
    this.campaignsService.save(campaignData);
  }

}
