export class Flight {

  constructor(
    public id: number,
    public nbr: number,
    public airline: string,
    public origin: string,
    public destination: string,
    public departure: Date,
    public arrival: Date,
    public returning: Date,
    public price: number
  ) { }

}
