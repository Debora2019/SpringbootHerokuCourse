package pt.com.curso.curso.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.com.curso.curso.model.Person;
import pt.com.curso.curso.repository.PersonRepository;

@RestController
public class Controller {

@Autowired
private PersonRepository personRepository;

@GetMapping
@RequestMapping("api/inicial")
public String greetings(){
    return "Welcome!";
    }

@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public String returnHello(@PathVariable String name){
    Person person = new Person();
    person.setName(name);

    personRepository.save(person);

    return "I am " + name;
}

@GetMapping(value="listAll")
@ResponseBody
public ResponseEntity<List<Person>> listPerson(){
    List<Person> person = personRepository.findAll();

    return new ResponseEntity<List<Person>>(person, HttpStatus.OK);
}



@PostMapping(value = "save")
@ResponseBody
public ResponseEntity<Person> save(@RequestBody Person person){
    Person p = personRepository.save(person);

    return new ResponseEntity<Person>(p, HttpStatus.CREATED);
}



@DeleteMapping(value = "delete")
@ResponseBody
public ResponseEntity<String> delete(@RequestParam Long id){
    personRepository.deleteById(id);

    return new ResponseEntity<String>("User deleted succesfully", HttpStatus.OK);
}


@GetMapping(value = "search")
@ResponseBody
public ResponseEntity<Person> findById(@RequestParam Long id){
   Person p =  personRepository.findById(id).get();

    return new ResponseEntity<Person>(p, HttpStatus.OK);
}

@PutMapping(value = "update")
@ResponseBody
public ResponseEntity<?> update(@RequestBody Person person){
    
    if (person.getId() == 0){
        return new ResponseEntity<String>("Id not found", HttpStatus.OK);

    }

    Person p = personRepository.saveAndFlush(person);
    return new ResponseEntity<Person>(p, HttpStatus.OK);
}



@GetMapping(value = "findByName")
@ResponseBody
public ResponseEntity<List<Person>> findByName(@RequestParam (name = "name") String name){
  List<Person> p =  personRepository.findByName(name.trim().toUpperCase());

    return new ResponseEntity<List<Person>>(p, HttpStatus.OK);
}
    
}
