package com.yap.yapcommon.controller;

import com.yap.yapcommon.pojo.Person;
import com.yap.yapcommon.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/get/{id}")
    public Mono<Person> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    /**
     *
     * @return
     */
    //@GetMapping(value = "/getall",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @GetMapping(value = "/getall",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Person> getAllPerson() {
        return personService.getAllPerson()
                .delayElements(Duration.ofMillis(500))
                .doOnNext(person -> System.out.println("Server Produce:" + person));
    }

    @PostMapping("/update")
    public Mono<Person> updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);

    }
}
