package com.yap.yapcommon.service;

import com.yap.yapcommon.pojo.Person;
import lombok.val;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonService {

    static Map<String, Person> personData;

    static {
        personData = new HashMap<>();
        personData.put("1", new Person("1", "ding1", "1"));
        personData.put("2", new Person("2", "xiao2", "2"));
        personData.put("3", new Person("3", "zhang3", "3"));
        personData.put("4", new Person("4", "li4", "4"));
        personData.put("5", new Person("5", "wang5", "5"));
        personData.put("6", new Person("6", "chen6", "6"));
    }

    public Mono<Person> getPersonById(String id) {
        return Mono.just(personData.get(id));
    }

    public Flux<Person> getAllPerson() {
        return Flux.fromIterable(personData.values());
    }

    public Mono<Person> updatePerson(Person person) {
        Person existPerson = personData.get(person.getId());
        if (existPerson != null) {
            existPerson.setName(person.getName());
            existPerson.setAge(person.getAge());
        }
        return Mono.just(existPerson);
    }
}
