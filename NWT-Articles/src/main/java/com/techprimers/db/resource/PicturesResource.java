package com.techprimers.db.resource;

import com.techprimers.db.model.Pictures;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.PicturesRepository;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.core.io.support.ResourcePatternUtils.isUrl;

@RestController
@RequestMapping(value = "/pictures")
public class PicturesResource {

    @Autowired
    PicturesRepository picturesRepository;

    @GetMapping(value = "/")
    public List<Pictures> getAll() {
        return picturesRepository.findAll();
    }

   @GetMapping(value = "/{id}")
    public ResponseEntity<?> getArtical(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<String, Object>();
        Pictures pictures = this.picturesRepository.findByBroj(id);
        if (pictures == null) {

            message.put("MESSAGE", "Ne postoji slika sa id " + id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Pictures>(pictures, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> persist(@RequestBody final Pictures pictures) {

        Map<String, Object> message = new HashMap<String, Object>();
        if (pictures.getSlika() == "" || pictures.getSlika() == null){

            message.put("MESSAGE", "Polje slika se mora popuniti");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        if (!isUrl(pictures.getSlika())){

            message.put("MESSAGE", "Polje slika se mora biti URL");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }


        picturesRepository.save(pictures);

        return new ResponseEntity<Collection<Pictures>>(this.picturesRepository.findAll(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<String, Object>();
        Pictures pictures = picturesRepository.findByBroj(id);
        if (pictures == null){

            message.put("MESSAGE","Ne postoji slika u bazi sa id " + id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        picturesRepository.delete(pictures);

        message.put("MESSAGE", "Uspjesno obrisan artikal " + id);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
