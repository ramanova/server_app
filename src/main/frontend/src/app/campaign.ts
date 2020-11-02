export interface Campaign {
  id: number;
  name: string;
  data: string;

  cap: {
    maxCountPerUser: number;
    maxCount: number;
  }
}

