package com.techprimers.db.resource;

import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll()
    {
        return usersRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users users)
    {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @GetMapping(value="/{id}")
    Users getOne(@PathVariable Integer id)
    {
        return usersRepository.findById(id);
    }

    /*@PutMapping(value="{id}")
    Users replaceUser(@RequestBody Users newUser,@PathVariable Integer id){
        Map<Users,Integer> map = new HashMap<>();
        return usersRepository.findById(id)
                .map(newUser ->{
                    newUser
                    user.setTeamName(newUser.getTeamName());
                    user.setSalary(newUser.getSalary());
                }).orElseGet(()->{
                   newUser.setId(id);
                   return usersRepository.save(newUser);
        });
    }*/

    @DeleteMapping(value="/delete/{id}")
    void deleteUser(@PathVariable Integer id){
        usersRepository.deleteById(id);
    }
}
