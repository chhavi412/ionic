import { Component, OnInit } from '@angular/core';
import { Router } from  "@angular/router";
import { AuthenticationService } from './../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(private authService: AuthenticationService, private router : Router) { }

  ngOnInit() {
  }
  
  login(form){
    console.log(form.value);
    
    this.authService.login(form.value).subscribe((res)=>{
      if(res==true)
      this.router.navigateByUrl('home');
    });
  }
  resetpassword(){
    this.router.navigateByUrl('resetpassword');
  }
}
