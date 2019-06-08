export class Osoba {
    ime: String;
    prezime: String;
    email: String;
    password: String;
    newPassword_url: String;
  constructor(ime: String, prezime: String, email: String, password: String, newPassword_url: String) {
    this.ime=ime;
    this.prezime=prezime;
    this.email=email;
    this.password=password;
    this.newPassword_url=newPassword_url;
  }

}
