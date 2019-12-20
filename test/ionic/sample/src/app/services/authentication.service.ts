import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastController, Platform } from '@ionic/angular';
import { Storage } from '@ionic/storage';
import { BehaviorSubject } from 'rxjs';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { error } from 'util';

const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authenticationState = new BehaviorSubject(false);
  authenticateStateObservable$: Observable<boolean> = this.authenticationState.asObservable();
  // const LOGIN_URL = 'http://localhost:8080/api/login';
  // const readonly REGISTER_URL = 'http://localhost:8080/api/register';
  access: boolean;
  token: string;

  constructor(private router: Router, private storage: Storage, private plt: Platform, public http: Http, private toastCtrl: ToastController) {
    this.plt.ready().then(() => {
      this.checkToken();
    });
  }
  checkToken() {
    this.storage.get(TOKEN_KEY).then(res => {
      if (res) {
        this.authenticationState.next(true);
      }
    })
  }
  public login(credentials) {
    if (credentials.email === null || credentials.password === null) {
      return Observable.throw("Please insert credentials.");
    } else {
      return Observable.create(observer => {

        this.http.post('http://192.1.200.146:8080/api/login', credentials)
          .map(res => res.json())
          .subscribe(data => {
            console.log(data);
            
            if (data) {
              console.log(data);
              this.authenticationState.next(true);
              this.token = 'Bearer ' + data;
              this.access = true;
            } else {
              this.access = false;
            }
          });

        setTimeout(() => {
          observer.next(this.access);
        }, 500);

        setTimeout(() => {
          observer.complete();
        }, 1000);


      }, err => console.error(err));
    }
  }

  logout() {
    return this.storage.remove(TOKEN_KEY).then(() => {
      this.authenticationState.next(false);
    });
  }

  register(credentials) {
    console.log(credentials);

    if (credentials.name === null || credentials.email === null || credentials.password === null) {
      return Observable.throw("Please insert credentials");
    }
    else {
      return Observable.create(observer => {

        let payload: any = {};
        payload['name'] = credentials.name;
        payload['email'] = credentials.email;
        payload['password'] = credentials.password;


        this.http.post("http://192.1.200.146:8080/api/register", payload)
          .subscribe(data => {
            console.log(data);
          });

        observer.next(true);
        // observer.complete();
      });
    }
  }

  isAuthenticated() {
    return this.authenticationState.value;
  }

  check(credentials){
    console.log(credentials);
    return this.http.post("http://192.1.200.146:8080/api/check",credentials).map(response => response.json());
  }

  changepassword(credentials, token){
    var Indata={'newPassword':credentials.newPassword, 'token':token.token}
    console.log(Indata);
    console.log(token.token);
    // this.http.post("http://192.1.200.146:8080/api/checktoken",token.token).map(res=>console.log(res))
    return this.http.post("http://192.1.200.146:8080/api/changepassword",Indata).map(response => console.log(response));
    
    
  }
}


