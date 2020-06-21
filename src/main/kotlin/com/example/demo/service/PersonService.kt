package com.example.demo.service

import com.example.demo.data.Person
import com.example.demo.data.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Service
class PersonService(
        private val repository: PersonRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<Person> {
        return repository.findAll()
    }

    @Transactional(readOnly = false)
    fun save(person: Person): Person {
        if (person.gid == null) {
            person.gid = repository.getNextGid()
        }
        return repository.save(person)
    }

    @PostConstruct
    fun populate() {
        if (repository.count() != 0L) {
            return
        }

        val alice = this.save(Person(name = "Alice"))
        val betty = this.save(Person(name = "Betty", gid = alice.gid))
        val cyndi = this.save(Person(name = "Cyndi"))
        val diana = this.save(Person(name = "Diana", gid = cyndi.gid))

        println(alice)
        println(betty)
        println(cyndi)
        println(diana)
    }
}
