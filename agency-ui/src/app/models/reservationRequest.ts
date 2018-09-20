import {Passenger} from "./passenger";
import {Address} from "./address";

export class ReservationRequest {

  constructor(
    public flightId: number,
    public passengers: Passenger[],
    public address: Address,
    public confirmation: string
  ) { }

}
