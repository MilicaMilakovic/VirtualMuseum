import { Tour } from "./tour.model";

export interface Ticket {
    id: number;
    issuedDate: string;
    tour: Tour;
}