export class Currency {
  id: string;
  name: string;
  rates: Rate[]
}

export class Rate {
    id: string;
    name: string;
    value: number;
}