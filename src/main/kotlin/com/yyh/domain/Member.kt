package com.yyh.domain

import org.springframework.util.Assert

data class Member(
    val email: EmailValue,
    var nickname: String,
    var passwordHash: String,
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

    data class MemberCreateRequest(
        val email: String,
        val nickname: String,
        val password: String,
    )

    companion object {
        fun create(memberCreateRequest: MemberCreateRequest, passwordEncoder: PasswordEncoder): Member {
            val passwordHash = passwordEncoder.encode(memberCreateRequest.password)

            return Member(EmailValue(memberCreateRequest.email), memberCreateRequest.nickname, passwordHash)
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