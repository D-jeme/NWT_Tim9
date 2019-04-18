package com.techprimers.db.resource;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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
    @PostMapping(value = "/")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
        Users registeredUser= usersRepository.findByEmail(user.getEmail());
        if(registeredUser!=null)  return  new ResponseEntity<Users>(user, HttpStatus.CONFLICT);
        usersRepository.save(user);
        return  new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Users user){
        Users loggedInUser= usersRepository.findByEmail(user.getEmail());
        if (loggedInUser!=null) {
            BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
            System.out.println("Compare 1 "+passwordEncoder.matches(user.getNewPassword(), "$2a$10$LhiY8u0pGGqqZNahFCtumOn/VfykWrpXMt0Q1ZDQVJI/tXzFapW6u"));
            if (passwordEncoder.matches(user.getNewPassword(), loggedInUser.getPassword())){
                return "Korisnik je ulogovan uspješno";
            }

            return "Email ili password su pogrešno uneseni ovdje";
        }
        return "Email ili password su pogrešno uneseni";
    }
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @Valid @RequestBody Users user){
        Users existing=usersRepository.findById(id);
        if (existing==null) {return "User doesn't exist";}
        existing.setIme(user.getIme());
        existing.setPrezime(user.getPrezime());
        existing.setPassword(user.getPassword());
        //user.setRole(existing.getRole());
        usersRepository.save(existing);
        return  "Updated user";
    }
  /*  @PostMapping("/login")
    public Users login(@Valid @RequestBody ){

        Users existing=usersRepository.findById(user.getId());
        user.setRole(existing.getRole());
        usersRepository.save(user);
        return "Updated user";
    }*/

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("USEEEEER IIIIIIIIIID"+id);
        if(usersRepository.findById(id)!=null){
        usersRepository.delete(usersRepository.findById(id));
        return "Deleted user";}
        return "User doesn't exist.";
    }
}


