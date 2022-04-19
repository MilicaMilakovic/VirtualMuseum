import { Museum } from "./museum.model";

export interface Tour {
    id: number;
    start: string;
    duration: number;
    price: number;
    active: boolean;
    museum: Museum;
}