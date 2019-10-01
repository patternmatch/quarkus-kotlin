package com.patternmatch.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BootJdbcApplication

fun main(args: Array<String>) {
    runApplication<BootJdbcApplication>(*args)
}

@RestController
class PersonResource(val personRepository: PersonRepository) {
    @GetMapping("/person/{id}")
    fun printName(@PathVariable("id") id: Int) = personRepository.getPersonName(id)
}

@Repository
class PersonRepository(val jdbcTemplate: JdbcTemplate) {
    fun getPersonName(id: Int): String = jdbcTemplate.queryForObject("select name from person where id = ?", arrayOf(id), String::class.java)
}