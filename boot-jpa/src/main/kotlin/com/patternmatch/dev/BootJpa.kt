package com.patternmatch.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
class BootJpaApplication

fun main(args: Array<String>) {
    runApplication<BootJpaApplication>(*args)
}

@RestController
@RequestMapping("/person")
class PersonResource(val personRepository: PersonRepository) {
    @PostMapping
    fun createPerson() = personRepository.save(Person(name = "Finn"))

    @GetMapping("/{id}")
    fun getName(@PathVariable("id") id: Int) = personRepository.findById(id)
}

@Entity
class Person(@Id @GeneratedValue var id: Int? = null, var name: String? = null)

interface PersonRepository : CrudRepository<Person, Int>
