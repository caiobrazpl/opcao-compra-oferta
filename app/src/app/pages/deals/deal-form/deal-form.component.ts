import {Component, OnInit} from "@angular/core";
import {Deal} from "../model/deal";
import {DealService} from "../model/deal.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-deal-form',
  templateUrl: './deal-form.component.html',
  styleUrls: ['./deal-form.component.scss']
})
export class DealFormComponent implements OnInit {

  entryForm: FormGroup;
  errorMessages: string[] = [];

  constructor(private dealService: DealService,
              private formBuilder: FormBuilder,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.buildForm()
  }

  private buildForm() {
    this.entryForm = this.formBuilder.group({
      title: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      text: [null, [Validators.required, Validators.maxLength(255)]],
      publishDate: [null, [Validators.required]],
      validity: [null, [Validators.required, Validators.min(1), Validators.max(30)]],
      type: [null, [Validators.required]]
    });
  }

  submitForm() {
    const deal: Deal = new Deal(this.entryForm.value);

    this.dealService.insert(deal).subscribe(
      value => this.success(value),
      error => this.error(error)
    );
  }

  private success(deal: Deal) {
    this.router.navigateByUrl('deals', {skipLocationChange: true}).then(
      () => this.toastr.success('Registro cadastrado.', 'Sucesso')
    );
  }

  private error(error) {
    if (error.status === 422 && error.error && error.error.errors) {
      error.error.errors.forEach(
        error => this.errorMessages.push(JSON.stringify(error))
      );
      return;
    }

    this.errorMessages = ['Falha na comunicação'];
  }

}
