export class Artikal {
    id: number;
    naziv: String;
    kratki_tekst: String;
    dugi_tekst: String;
    cijena: number;
    popust: number;
    kolicina
  constructor(id: number, naziv: String, kratki_tekst: String, dugi_tekst: String, cijena: number, popust: number, kolicina: number) {
    this.id = id;
    this.naziv=naziv;
    this.kratki_tekst=kratki_tekst;
    this.dugi_tekst=dugi_tekst;
    this.cijena=cijena;
    this.popust=popust;
    this.kolicina=kolicina;
  }

}
