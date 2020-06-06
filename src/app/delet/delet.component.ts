import { Component, OnInit } from '@angular/core';
import { Message } from '../message';
import { DeletService } from '../delet.service';

@Component({
  selector: 'app-delet',
  templateUrl: './delet.component.html',
  styleUrls: ['./delet.component.css']
})
export class DeletComponent implements OnInit {
/*
  message: string;

  constructor(private deletService: DeletService) { }

  ngOnInit() {

    console.log("DeletComponent");
    this.deletService.DeletService().subscribe( (result) => {
      this.message = result.content;
    });
  }
*/

constructor() {}
ngOnInit() {}
}
