package com.patternmatch.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BootPlainApplication

fun main(args: Array<String>) {
	runApplication<BootPlainApplication>(*args)
}

@RestController
class GreetingResource {
	@GetMapping("/greeting")
	fun hello() = "hello"
}