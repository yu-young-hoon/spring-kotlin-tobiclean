package com.yyh.adapter.security

import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*

class SecurePasswordEncoderTest {

    @Test
    fun securePasswordEncoder() {
        val passwordEncoder = SecurePasswordEncoder()
        val rawPassword = "my_secure_password"
        val encodedPassword = passwordEncoder.encode(rawPassword)

        assertNotEquals(rawPassword, encodedPassword, "Encoded password should not match raw password")
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "Password should match after encoding")
    }
}
