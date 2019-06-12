package com.techprimers.db.resource;

import com.techprimers.db.model.Articles;
import com.techprimers.db.repository.ArticlesRepository;
import com.techprimers.db.services.ArticleEventHandler;
import com.techprimers.db.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/articles")
public class ArticlesResource {

    @Autowired

    ArticlesRepository articlesRepository;
    ArticlesService articlesService;
    @LoadBalanced
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ArticleEventHandler articleEventHandler;


    @GetMapping(value = "/1")
    public ResponseEntity<?>get1(){
        System.out.println("tu sam");
    String string1 = null;
        Articles ar =  restTemplate.getForObject("http://articles/articles/9",Articles.class);
        System.out.println("poruka"+ar);
        return new ResponseEntity<Articles>(ar, HttpStatus.OK);
    }
    @GetMapping(value = "/2")
    public ResponseEntity<?>get2(){
        System.out.println("tu sam");

        String str =  restTemplate.getForObject("http://USERS/rest/users/hello",String.class);
        System.out.println("poruka"+str);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }
    @GetMapping("/hello")
    public String getHelloWordObject() {
      String hello=("Hi there! you are number ");
        return hello;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        responseHeaders.set("Access-Control-Allow-Headers", "Content-Type");
        System.out.println("imal me"+responseHeaders);

        Collection<Articles> articles = this.articlesRepository.findAll();
        if(articles.isEmpty())
        {Map<String, Object> message = new HashMap<String, Object>();

            message.put("MESSAGE", "Nema artikala u bazi");
            return new ResponseEntity<>(message,responseHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<Collection<Articles>>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getArtical(@PathVariable Integer id) {
       Articles article = this.articlesRepository.findByBroj(id);
       System.out.println("rezultat"+article);
        if(article==null){
            Map<String, Object> message = new HashMap<String, Object>();

            message.put("MESSAGE", "Ne postoji artikal sa id "+id);


            return new ResponseEntity<>(message , HttpStatus.OK);
            //return new ResponseEntity<>("Ne postoji artikal sa id "+id, HttpStatus.OK);
        }

        return new ResponseEntity<Articles>(article, HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public ResponseEntity<?> persist(@RequestBody final Articles articles) {

        Map<String, Object> message = new HashMap<String, Object>();
        Articles artikal=articlesRepository.findByNaziv(articles.getNaziv());
        if(artikal!=null){

            message.put("MESSAGE", "Vec postoji artikal sa nazivom "+articles.getNaziv());
            return new ResponseEntity<>(message, HttpStatus.OK);

        }
        if(!restTemplate.getForObject("http://USERS/rest/users/exist/"+articles.getObjavio(),Boolean.class)){
            message.put("MESSAGE", "Ne postoji user sa id:"+articles.getObjavio());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }



        if(articles.getCijena()==0) {

            message.put("MESSAGE", "Polje cijena mora biti vece do nule ");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(articles.getAktivan()==null){

            message.put("MESSAGE", "Polje aktivan mora biti popunjeno sa true ili false");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        if(articles.getDugi_tekst()==""){

            message.put("MESSAGE", "Polje dugi_tekst mora biti popunjeno");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(articles.getKratki_tekst()=="") {

            message.put("MESSAGE", "Polje kratki_tekst mora biti popunjeno");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(articles.getNaziv()=="") {

            message.put("MESSAGE", "Polje naziv mora biti popunjeno");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        Integer picture = restTemplate.postForObject("http://articles/pictures/picture_id1", articles.getPictures().getSlika(), Integer.class);

        articles.getPictures().setBroj(picture);


        articlesRepository.save(articles);
        return new ResponseEntity<Collection<Articles>>(this.articlesRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id){
        Articles article=articlesRepository.findByBroj(id);
        Map<String, Object> message = new HashMap<String, Object>();
        if(article==null){

            message.put("MESSAGE", "Ne postoji artikal u bazi sa id "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        articleEventHandler.handleAfterCreated(String.valueOf(id));
        articlesRepository.delete(article);

        message.put("MESSAGE", "Uspjesno obrisan artikal "+id);
         return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping("all/{id}")
    ResponseEntity<?> deleteUsersArticle(@PathVariable Long id){
        Collection<Articles> articles=articlesRepository.findAll();
        System.out.println("duzina"+articles.size());
        for (Articles article:articles
        ) {
            if(article.getObjavio()==id) {
                articlesRepository.delete(article);
            }
        }
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("MESSAGE", "Uspjesno obrisani artikli korisnika sa idom:"+id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PutMapping("/updateAktivan/{id}")
    public ResponseEntity<?>  izmijeniAktivan(@PathVariable Integer id,@RequestBody final Articles articles){
        Articles article=articlesRepository.findByBroj(id);

        Map<String, Object> message = new HashMap<String, Object>();
        if(article==null){

            message.put("MESSAGE","Ne postoji artikal u bazi sa id "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        article.setAktivan(articles.getAktivan());
        articleEventHandler.handleAfterUpdated(String.valueOf(id));
        return new ResponseEntity<Articles>(articlesRepository.save(article), HttpStatus.OK);

    }

    @PutMapping("/updateCijena/{id}")
    public ResponseEntity<?>  izmijeniCijena(@PathVariable Integer id,@RequestBody final Articles articles){

        Map<String, Object> message = new HashMap<String, Object>();
        if(articles.getCijena()==null) {

            message.put("MESSAGE","Mora postojati polje cijena");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(articles.getCijena()==0) {
            message.put("MESSAGE","Cijena mora biti veci od nule");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        Articles article=articlesRepository.findByBroj(id);
        if(article==null){

            message.put("MESSAGE","Ne postoji artikal u bazi sa id "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        article.setCijena(articles.getCijena());
        return new ResponseEntity<Articles>(articlesRepository.save(article), HttpStatus.OK);
    }

    @PutMapping("/updatePopust/{id}")
    public ResponseEntity<?> izmijeniPopust(@PathVariable Integer id,@RequestBody final Articles articles){

        Map<String, Object> message = new HashMap<String, Object>();
        Articles article=articlesRepository.findByBroj(id);
        if(article==null){
            message.put("MESSAGE","Ne postoji artikal u bazi sa id "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        article.setPopust(articles.getPopust());
        return new ResponseEntity<Articles>(articlesRepository.save(article), HttpStatus.OK);
    }

    @PutMapping("/updateKolicina/{id}")
    public ResponseEntity<?> izmijeniKolicina (@PathVariable Integer id,@RequestBody final Articles articles){

        Map<String, Object> message = new HashMap<String, Object>();
        Articles article=articlesRepository.findByBroj(id);
        if(article==null){
            message.put("MESSAGE","Ne postoji artikal u bazi sa id "+id);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        article.setKolicina(articles.getKolicina());

        return new ResponseEntity<Articles>(articlesRepository.save(article), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> izmijeniProizvod (@PathVariable Integer id,@RequestBody final Articles articles){
        System.out.println("TU SAAAAM"+articles);

        Map<String, Object> message = new HashMap<String, Object>();
        Articles article=articlesRepository.findByBroj(id);
        if(article==null){
            message.put("MESSAGE","Ne postoji artikal u bazi sa id "+id);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        article.setKratki_tekst(articles.getKratki_tekst());
        article.setDugi_tekst(articles.getDugi_tekst());
        article.setCijena(articles.getCijena());
        article.setPopust(articles.getPopust());

        return new ResponseEntity<Articles>(articlesRepository.save(article), HttpStatus.OK);
    }

}
