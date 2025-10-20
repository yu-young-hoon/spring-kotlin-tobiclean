package com.yyh.util

import com.yyh.domain.member.MemberRegisterRequest
import com.yyh.domain.member.PasswordEncoder

fun createMemberRegisterRequest(email: String): MemberRegisterRequest =
    MemberRegisterRequest(email, "nickname", "secret")

fun createPasswordEncoder() =
    object : PasswordEncoder {
        override fun encode(password: String): String {
            return "encoded_$password"
        }

        override fun matches(password: String, passwordHash: String): Boolean {
            return passwordHash == encode(password)
        }
    }

