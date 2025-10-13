package com.yyh.domain

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache
import org.springframework.util.Assert

@NaturalIdCache
@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @NaturalId
    val email: EmailValue,
    var nickname: String,
    var passwordHash: String,
    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.PENDING,
) {
    fun activate() {
        Assert.state(status == MemberStatus.PENDING, "Member status must be PENDING to activate.")

        this.status = MemberStatus.ACTIVE
    }

    fun deactivate() {
        Assert.state(status == MemberStatus.ACTIVE, "Member status must be ACTIVE to deactivate.")

        this.status = MemberStatus.DEACTIVATED
    }

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, this.passwordHash)
    }

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.passwordHash = passwordEncoder.encode(password)
    }

    fun isActive(): Boolean {
        return this.status == MemberStatus.ACTIVE
    }

    data class MemberRegisterRequest(
        val email: String,
        val nickname: String,
        val password: String,
    )

    companion object {
        fun register(memberRegisterRequest: MemberRegisterRequest, passwordEncoder: PasswordEncoder): Member {
            val passwordHash = passwordEncoder.encode(memberRegisterRequest.password)

            return Member(0, EmailValue(memberRegisterRequest.email), memberRegisterRequest.nickname, passwordHash)
        }
    }
}

@JvmInline
value class EmailValue(val address: String) {
    init {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        require(address.matches(emailPattern)) { "Invalid email format." }
    }
}
