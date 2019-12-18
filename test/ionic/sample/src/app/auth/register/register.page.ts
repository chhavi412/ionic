import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthenticationService } from '../../services/authentication.service';
import { Observable } from 'rxjs';
import { ToastController } from '@ionic/angular';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  private myForm: FormGroup;
  constructor(private authService: AuthenticationService, private router: Router, private toastCtrl: ToastController, private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.myForm = new FormGroup({
      name: new FormControl('', [Validators.minLength(3)]),
      email: new FormControl('', [Validators.email]),
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
  async register() {

    if (this.myForm.value.password == this.myForm.value.confirm) {
      this.authService.register(this.myForm.value).subscribe((res) => {
        console.log(res);
        this.router.navigateByUrl('login');
      },
        (err) => {
          console.log("couldn't register");
        });
    }
    else {
      console.log("toaster");
      let toast = await this.toastCtrl.create({
        message: 'password is incorrect',
        duration: 3000,
        position: 'top'
      });
      toast.present();
    }

  }

}
