import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.page.html',
  styleUrls: ['./changepassword.page.scss'],
})
export class ChangepasswordPage implements OnInit {

  private changePassword: FormGroup;
  token: number;
  constructor(private authService: AuthenticationService, private router: Router, private toastCtrl: ToastController, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.changePassword = new FormGroup({
      email: new FormControl('',[Validators.email]),
      password: new FormControl('', [Validators.required]),
      confirm: new FormControl('', [this.equalto('password')])
    })
  }
  equalto(field_name): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {

      let input = control.value;

      let isValid = control.root.value[field_name] == input
      if (!isValid)
        return { 'equalTo': { isValid } }
      else
        return null;
    };
  }

  changepassword(){
    this.authService.changepassword(this.changePassword.value).subscribe((res)=>{
      console.log(res);
      this.token=1;
      
    })
  }

}
