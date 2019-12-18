import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Observable } from 'rxjs';
import { error } from 'util';


@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.page.html',
  styleUrls: ['./resetpassword.page.scss'],
})
export class ResetpasswordPage implements OnInit {

  private resetForm: FormGroup;
  token: number;

  constructor(private authService: AuthenticationService, private router: Router, private toastCtrl: ToastController, private formBuilder: FormBuilder) { 
    let token= 0;
  }

  ngOnInit() {
    
    this.resetForm = new FormGroup({
      email: new FormControl('',[ Validators.email]),
    })
  }

  resetpassword(){
    this.token=1;
    this.authService.check(this.resetForm.value).subscribe((res)=>{
      console.log(res);
      if(res==true)
      this.token= 2;
      // this.router.navigateByUrl('login');
      else if(res==false)
      this.token=3;

    },
    (error)=>{this.token=3 ;console.log(error);})
  }

}
