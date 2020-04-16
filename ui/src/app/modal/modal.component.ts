import {Component, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";

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
    if (!this.modal.closed) {
      this.closing = true;
      this.modal.close();
      this.modal = null;
    }
  }

  private openModal() {
    this.modal = this.modalService.open(this.activeRoute.snapshot.data.component, {
      height: 'auto',
      width: '600px',
    }).afterClosed().subscribe(value => {
      if (!this.closing) {
        this.router.navigate([{outlets: {modal: null}}]);
      }
    });
  }
}
