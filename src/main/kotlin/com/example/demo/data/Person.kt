package com.example.demo.data

import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var uid: Long? = null,
        // @Generated(GenerationTime.INSERT)
        var gid: Long? = null,
        var name: String? = null
)
