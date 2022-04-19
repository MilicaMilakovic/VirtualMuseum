import { Ticket } from "./ticket.model";

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    email: string;
    active: boolean;
    loggedIn: boolean;
    role: string;
    token: string;
    approved:boolean;
    tickets: Ticket[];
}