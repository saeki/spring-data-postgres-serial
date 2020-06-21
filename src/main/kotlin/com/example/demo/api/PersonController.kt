package com.example.demo.api

import com.example.demo.data.Person
import com.example.demo.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(
        private val service: PersonService
) {

    @GetMapping("/")
    fun index(): List<Person> {
        return service.findAll()
    }
}
