package com.yyh.adapter.security

import com.yyh.domain.member.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurePasswordEncoder : PasswordEncoder {
    private final val bcryptPasswordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun encode(password: String): String {
        return bcryptPasswordEncoder.encode(password)
    }

    override fun matches(password: String, passwordHash: String): Boolean {
        return bcryptPasswordEncoder.matches(password, passwordHash)
    }
}
