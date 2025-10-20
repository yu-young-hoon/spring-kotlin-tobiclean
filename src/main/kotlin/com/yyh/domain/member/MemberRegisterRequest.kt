package com.yyh.domain.member

data class MemberRegisterRequest(
    val email: String,
    val nickname: String,
    val password: String,
)
