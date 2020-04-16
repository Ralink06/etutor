import {Component, Injector, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnDestroy {

  private modal: any;
  private closing: boolean;

  constructor(private modalService: MatDialog,
              private router: Router,
              private activeRoute: ActivatedRoute) {
    this.openModal();
  }

  ngOnDestroy() {
    if (this.modal) {
      this.closing = true;
      this.modal.dismiss();
      this.modal = null;
    }
  }

  private openModal() {
    console.log(this.activeRoute.snapshot.data.component);
    this.modal = this.modalService.open(this.activeRoute.snapshot.data.component);
    const changeLocation = () => {
      if (!this.closing) {
        this.router.navigate([{outlets: {modal: null}}]);
      }
    };
    this.modal.result.then(changeLocation, changeLocation);
  }

}
