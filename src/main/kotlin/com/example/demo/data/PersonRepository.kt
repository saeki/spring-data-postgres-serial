package com.example.demo.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    @Query(value = "select nextval('person_gid_seq')", nativeQuery = true)
    fun getNextGid(): Long
}
