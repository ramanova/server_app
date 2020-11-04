import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CampaignsComponent} from "./campaigns/campaigns.component";
import {CampaignComponent} from "./campaign/campaign.component";

const routes: Routes = [
  {
    path: 'campaigns',
    component: CampaignsComponent
  },
  {
    path: 'campaign/:campaignId',
    component: CampaignComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
