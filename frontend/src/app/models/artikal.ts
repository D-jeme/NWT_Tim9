export class Artikal {
    naziv: String;
    kratki_tekst: String;
    dugi_tekst: String;
    cijena: Number;
    popust: Number;
  constructor(naziv: String, kratki_tekst: String, dugi_tekst: String, cijena: Number, popust: Number) {
    this.naziv=naziv;
    this.kratki_tekst=kratki_tekst;
    this.dugi_tekst=dugi_tekst;
    this.cijena=cijena;
    this.popust=popust;
  }

}
