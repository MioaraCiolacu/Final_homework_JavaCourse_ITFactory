package com.itfactory.sbdemo.controller;

import com.itfactory.sbdemo.dao.PersonDao;
import com.itfactory.sbdemo.models.Person;
import com.itfactory.sbdemo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestController.class);
    private final PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/insertperson")
    public void insertPerson(@RequestBody Person person) {
        System.out.println(personService.insertPerson(person));

    }

    //Metoda de mai jos, realizeaza modificarea adresei de e-mail, insa in postgres lasa si valoarea structurii JSON, adica
    // afiseaza in dreptul id-ului:   {"email:"bla@example.com"},     ceea ce nu e de dorit, pentru ca vrem sa se afiseze doar          bla@example.com
//    @PutMapping("/updatepersonemail")
//    public void updatePersonEmail(@RequestParam int id, @RequestBody String email) {
//        System.out.println(personService.updatePersonEmail(id, email));
//
//    }

    //Pentru a evita sa imi afiseze ca mai sus, modific metoda ca mai jos, pentru a se trata corpul cererii JSON ca un
    // obiect Map si astfel se va prelua valoarea de e-mail din campul "email".
    // Astfel, se elimina posibilitatea adaugarii structurii JSON in valoarea de e-mail.

    @PutMapping("/updatepersonemail")
    public void updatePersonEmail(@RequestParam int id, @RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println(personService.updatePersonEmail(id, email));
    }

    @PutMapping("/updatepersontelephone")
    public void updatePersonTelephone(@RequestParam int id, @RequestBody Map<String, String> request) {
        String newTelephone = request.get("telefon");
        System.out.println(personService.updatePersonTelephone(id, newTelephone));
    }


    @DeleteMapping("/deleteperson/{id}")
    public void deletePerson(@PathVariable int id) {
        System.out.println(personService.deletePerson(id));

    }
    //in postman pot pune orice nr la id, dar in consola voi cere numarul (id-ul) persoanei pe care vreau punctual sa o sterg,
    // daca in postman pun numarul corect, dar in consola nr. gresit (inexistent), nu se va sterge din baza de date

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.retrievePersons();

    }

}

//just for me:
//    @GetMapping("/all")
//    public String getPersons(){
//        return "Persoanele din sistem";
//    }
//}

