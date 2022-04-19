import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../models/user.model";

@Injectable({
    providedIn: 'root',
  })
export class UserService{

    public user!: User;
    public admin!:User;

    url = "http://localhost:9000/api/v1/users/";
    constructor(private http: HttpClient){

    }
    public refresh(){
      this.http.get<User>(this.url+this.user.id, {withCredentials: true})
               .subscribe(res => 
                this.user = res);
    }
}