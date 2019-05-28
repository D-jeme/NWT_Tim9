/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MainpictureComponent } from './mainpicture.component';

describe('MainpictureComponent', () => {
  let component: MainpictureComponent;
  let fixture: ComponentFixture<MainpictureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainpictureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainpictureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
