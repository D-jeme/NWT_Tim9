package com.techprimers.db.resource;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import com.techprimers.db.services.UserEventHandler;
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

    @Autowired
    UserEventHandler userEventHandler;

    @GetMapping("/exist/{id}")
    public boolean userExist(@PathVariable Long id) {
        Users user=usersRepository.findById(id);
        System.out.println("user"+user);
        if(user==null)return false;
        return true;
    }

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
      userEventHandler.handleAfterCreated( usersRepository.findById(id));
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
        System.out.println("IMAAAAAA"+user.getEmail()+user.getRole());
        System.out.println("OVDJEJEEE"+usersRepository.findByEmail(user.getEmail()));
        Users registeredUser= usersRepository.findByEmail(user.getEmail());
        System.out.println("REGISTEREEED USEEER"+registeredUser);
        if(registeredUser!=null)  return  new ResponseEntity<Users>(user, HttpStatus.CONFLICT);
        if(user.getNewPassword_url()!=null) {
            JSONObject jsonObject=new JSONObject();
            System.out.println("1111111111111111111");
            jsonObject.put("slika", user.getNewPassword_url());
            Boolean pictureexists = restTemplate.postForObject("http://articles/pictures/exist", user.getNewPassword_url(), Boolean.class);

            System.out.println("2222222222");
            if (pictureexists == true) {
                Integer picture = restTemplate.postForObject("http://articles/pictures/picture_id", user.getNewPassword_url(), Integer.class);
                user.setNewPassword_url("");
                user.setProfile_image_id(picture);
                usersRepository.save(user);
                return new ResponseEntity<Users>(user, HttpStatus.CREATED);
            }
            String re = restTemplate.postForObject("http://articles/pictures/picture", jsonObject, String.class);
            Integer picture_id = restTemplate.postForObject("http://articles/pictures/picture_id", user.getNewPassword_url(), Integer.class);
            user.setNewPassword_url("");
            user.setProfile_image_id(picture_id);
        }
        else user.setNewPassword_url("");
        usersRepository.save(user);
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
            System.out.println("Compare 1 " + passwordEncoder.matches(user.getNewPassword_url(), "$2a$10$.pWex2THB4/o4EuO1HW84eYQI63cLZW0.ppt3TWgsjt1Mroh3PX1S"));
            System.out.println("PSWWWWWWWWWWW"+loggedInUser.getPassword());
            if (passwordEncoder.matches(user.getNewPassword_url(), loggedInUser.getPassword())) {
                message.put("MESSAGE", "Korisnik je ulogovan uspješno");
                return new ResponseEntity<>(message,HttpStatus.OK);
            }
            message.put("MESSAGE", "Email ili password su pogrešno uneseni.");
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        message.put("MESSAGE", "Email ili password su pogrešno unesenii.");
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
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("slika",user.getNewPassword_url());
        existing.setIme(user.getIme());
        existing.setPrezime(user.getPrezime());
        System.out.println("USERR PSW"+user.getPassword());
        String psw=user.getNewPassword();
        existing.setPassword(user.getNewPassword());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("EXISTIIING"+existing.getPassword());
        System.out.println("Compare 1 " + passwordEncoder.matches(user.getNewPassword(), existing.getPassword()));
        System.out.println("PSWWW"+existing.getPassword());
        Boolean pictureexists =  restTemplate.postForObject("http://articles/pictures/exist",user.getNewPassword_url(),Boolean.class);
        //user.setRole(existing.getRole());
        System.out.println("porukaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+pictureexists);
        if(pictureexists==true) {
            System.out.println("TRUEEEEEEEE");
            Integer picture=restTemplate.postForObject("http://articles/pictures/picture_id",user.getNewPassword_url(), Integer.class);
            System.out.println("PICTRUREEEE"+picture);
            existing.setNewPassword_url("");
            existing.setProfile_image_id(picture);
            usersRepository.save(existing);
            message.put("MESSAGE", "Updated user.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        System.out.println("FALSEEEEEEEEEEEE");
        String re=restTemplate.postForObject("http://articles/pictures/picture",jsonObject,String.class);
        System.out.println("RESPONSEEEEEEE"+re);
        Integer picture_id=restTemplate.postForObject("http://articles/pictures/picture_id",user.getNewPassword_url(), Integer.class);
        existing.setNewPassword_url("");
        existing.setProfile_image_id(picture_id);
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
        Integer profile_picture_id;
        userEventHandler.handleAfterCreated(usersRepository.findById(id));
        if (usersRepository.findById(id) != null) {
            profile_picture_id=usersRepository.findById(id).getProfile_image_id();
            usersRepository.delete(usersRepository.findById(id));
            restTemplate.delete("http://articles/articles/all/"+id,String.class);
            restTemplate.delete("http://articles/pictures/"+profile_picture_id,String.class);
            message.put("MESSAGE", "Deleted user");

            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        message.put("MESSAGE", "User doesn't exist.");
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }

}


