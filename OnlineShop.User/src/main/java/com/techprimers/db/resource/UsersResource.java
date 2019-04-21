package com.techprimers.db.resource;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;
    @LoadBalanced
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String getHelloWordObject() {
        String hello=("Pricamooo ");
        return hello;
    }

    @GetMapping(value = "/1")
    public ResponseEntity<?>get1(){
        System.out.println("tu sam");

        String str =  restTemplate.getForObject("http://articles/articles/hello",String.class);
        System.out.println("poruka"+str);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

        @GetMapping(value = "/")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Users getUser(@PathVariable Long id){

      return usersRepository.findById(id);

    }

   /* @PostMapping(value = "/load")
    public List<Users> persist(@Valid @RequestBody  final Users users, Errors errors) {
            usersRepository.save(users);
            return usersRepository.findAll();
        }*/
 /*   @PostMapping(value = "/")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
        Users registeredUser= usersRepository.findByEmail(user.getEmail());
        if(registeredUser!=null)  return  new ResponseEntity<Users>(user, HttpStatus.CONFLICT);
        usersRepository.save(user);
        return  new ResponseEntity<Users>(user, HttpStatus.OK);
    }*/

    @PostMapping(value = "/")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
        Users registeredUser= usersRepository.findByEmail(user.getEmail());
        if(registeredUser!=null)  return  new ResponseEntity<Users>(user, HttpStatus.CONFLICT);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("slika",user.getNewPassword_url());
       // MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
       // parametersMap.add("urlslike", user.getNewPassword_url());
        System.out.println("url_slikeEEEEEEEEEEEEEEEEEE"+user.getNewPassword_url());
        System.out.println("PARAMETAAAARRRRRRRR"+jsonObject);
        Boolean pictureexists =  restTemplate.postForObject("http://articles/pictures/exist",user.getNewPassword_url(),Boolean.class);
        System.out.println("porukaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+pictureexists);
        if(pictureexists==true) {
            System.out.println("TRUEEEEEEEE");
            Integer picture=restTemplate.postForObject("http://articles/pictures/picture_id",user.getNewPassword_url(), Integer.class);
            System.out.println("PICTRUREEEE"+picture);
            user.setNewPassword_url("");
            user.setProfile_image_id(picture);
            usersRepository.save(user);
            return new ResponseEntity<Users>(user, HttpStatus.CREATED);
        }
        System.out.println("FALSEEEEEEEEEEEE");
        String re=restTemplate.postForObject("http://articles/pictures/picture",jsonObject,String.class);
        System.out.println("RESPONSEEEEEEE"+re);
        Integer picture_id=restTemplate.postForObject("http://articles/pictures/picture_id",user.getNewPassword_url(), Integer.class);
        user.setNewPassword_url("");
        user.setProfile_image_id(picture_id);
        usersRepository.save(user);
        System.out.println("IMA LI MEEEEEEEEEEE");

        return  new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Users loggedInUser = usersRepository.findByEmail(user.getEmail());
        System.out.println("LOGGEDINNNNNN USEEER"+loggedInUser);
        Map<String,Object> message=new HashMap<String, Object>();
        if (loggedInUser != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println("NOVIIIIIIIII PSW hashhhhh"+user.getNewPassword_url());
            System.out.println("Compare 1 " + passwordEncoder.matches(user.getNewPassword_url(), "$2a$10$d1qRDwA0/WGT60dEzf/9D.qbsnzhOd6miM/1SrrclHKbS2zVGF2z6"));
            if (passwordEncoder.matches(user.getNewPassword_url(), loggedInUser.getPassword())) {
                message.put("MESSAGE", "Korisnik je ulogovan uspješno");
                return new ResponseEntity<>(message,HttpStatus.OK);
            }
            message.put("MESSAGE", "Email ili password su pogrešno uneseni.");
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        message.put("MESSAGE", "Email ili password su pogrešno uneseni.");
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody Users user) {
        Users existing = usersRepository.findById(id);
        Map<String,Object> message=new HashMap<String, Object>();
        if (existing == null) {
            message.put("MESSAGE", "User doesn't exist.");
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        existing.setIme(user.getIme());
        existing.setPrezime(user.getPrezime());
        System.out.println("USERR PSW"+user.getPassword());
        existing.setPassword(user.getPassword());
        //user.setRole(existing.getRole());
        usersRepository.save(existing);
        message.put("MESSAGE", "Updated user.");
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
  /*  @PostMapping("/login")
    public Users login(@Valid @RequestBody ){

        Users existing=usersRepository.findById(user.getId());
        user.setRole(existing.getRole());
        usersRepository.save(user);
        return "Updated user";
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("USEEEEER IIIIIIIIIID" + id);
        Map<String,Object> message=new HashMap<String, Object>();
        if (usersRepository.findById(id) != null) {
            usersRepository.delete(usersRepository.findById(id));

            message.put("MESSAGE", "Deleted user");
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        message.put("MESSAGE", "User doesn't exist.");
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }
}


