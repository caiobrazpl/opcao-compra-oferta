import {Component, OnInit} from "@angular/core";
import {BuyOption} from "../../model/buy-option";
import {BuyOptionService} from "../../model/buy-option.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Deal} from "../../../deals/model/deal";
import {ActivatedRoute, Router} from "@angular/router";
import {DealService} from "../../../deals/model/deal.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-option-form',
  templateUrl: './option-form.component.html'
})
export class OptionFormComponent implements OnInit {

  formGroup: FormGroup;
  errorMessages: string[] = [];

  deal: Deal;

  constructor(private optionService: BuyOptionService,
              private dealService: DealService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.loadDeal();
    this.buildForm()
  }

  private buildForm() {
    this.formGroup = this.formBuilder.group({
      title: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(255)]],
      text: [null, [Validators.required, Validators.maxLength(255)]],
      normalPrice: [null, [Validators.required, Validators.min(0)]],
      percentageDiscount: [null, [Validators.required, Validators.min(0), Validators.max(99)]],
      quantityCoupon: [null, [Validators.required, Validators.min(0)]],
      startDate: [null, [Validators.required]],
      endDate: [null, [Validators.required]]
    });
  }

  submitForm() {
    const buyOption: BuyOption = new BuyOption(this.formGroup.value);
    buyOption.deal = this.deal;

    this.optionService.insert(buyOption).subscribe(
      value => this.success(),
      error => console.log(error)
    );
  }

  private success() {
    this.router.navigateByUrl('deals/view/' + this.deal.id).then(
      () => this.toastr.success('Registro cadastrado.', 'Sucesso')
    );
  }

  private loadDeal() {
    this.route.queryParams.subscribe(value => {

      this.dealService.getById(value['idDeal']).subscribe(deal => {
        this.deal = deal;
      });

    });
  }

}
