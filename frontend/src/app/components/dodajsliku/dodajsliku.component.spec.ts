import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { DodajslikuComponent } from './dodajsliku.component';

describe('RegistracijaComponent', () => {
  let component: DodajslikuComponent;
  let fixture: ComponentFixture<DodajslikuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodajslikuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajslikuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});*/
