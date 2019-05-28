import { SteleksFrontPage } from './app.po';

describe('steleks-front App', function() {
  let page: SteleksFrontPage;

  beforeEach(() => {
    page = new SteleksFrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
