package com.yyh.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class EmailValueTest {
    @Test
    fun validate() {
        assertThrows<IllegalArgumentException> {
            EmailValue("invalid-email")
        }
        assertDoesNotThrow {
            EmailValue("dbtlek@gamil.com")
        }
    }

    @Test
    fun equality() {
        val emailValue1 = EmailValue("dbtlek@gamil.com")
        val emailValue2 = EmailValue("dbtlek@gamil.com")
        assertEquals(emailValue1, emailValue2)
    }
}