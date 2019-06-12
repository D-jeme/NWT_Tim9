package com.techprimers.db.resource;

import com.techprimers.db.model.Roles;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/roles")
public class RolesResource {

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping(value = "/")
    public List<Roles> getAll() {
        System.out.println("USEEEEER IIIIIIIIIID" + rolesRepository.findById((long)1));
        return rolesRepository.findAll();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Roles> persist(@Valid @RequestBody final Roles role) {
        Roles registeredTip= rolesRepository.findByTip(role.getTip());
        if(registeredTip!=null)  return  new ResponseEntity<Roles>(role, HttpStatus.CONFLICT);
        rolesRepository.save(role);
        return  new ResponseEntity<Roles>(role, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("USEEEEER IIIIIIIIIID" + id);
        Map<String,Object> message=new HashMap<String, Object>();
        if (rolesRepository.findById(id) != null) {
            rolesRepository.delete(rolesRepository.findById(id));
            message.put("MESSAGE", "Deleted role");
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        message.put("MESSAGE", "Role doesn't exist.");
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }

}
