import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.page.html',
  styleUrls: ['./changepassword.page.scss'],
})
export class ChangepasswordPage implements OnInit {

  private changePasswordForm: FormGroup;
  token: number;
  // id: string;
  constructor(private authService: AuthenticationService, private router: Router, private toastCtrl: ToastController, private formBuilder: FormBuilder, private route: ActivatedRoute) { }

  ngOnInit() {
    this.changePasswordForm = new FormGroup({
      newPassword: new FormControl('', [Validators.required]),
      confirm: new FormControl('', [this.equalto('newPassword')])
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
    let id = this.route.params.subscribe( params => { this.authService.changepassword(this.changePasswordForm.value, params).subscribe((res)=>{
      console.log(res);
      this.token=1;
    }) 
  })
    // console.log(id);
    // this.authService.changepassword(this.changePasswordForm.value, id).subscribe((res)=>{
    //   console.log(res);
    //   this.token=1;
      
    // })
  }

}
