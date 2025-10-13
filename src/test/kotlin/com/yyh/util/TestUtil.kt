package com.yyh.util

import com.yyh.domain.Member.MemberRegisterRequest
import com.yyh.domain.PasswordEncoder

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

