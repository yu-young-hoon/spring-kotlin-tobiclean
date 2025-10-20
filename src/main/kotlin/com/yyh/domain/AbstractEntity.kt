package com.yyh.domain

import jakarta.persistence.*
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache
import org.springframework.util.Assert

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0
}

