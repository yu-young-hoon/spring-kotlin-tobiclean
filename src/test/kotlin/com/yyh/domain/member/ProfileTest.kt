package com.yyh.domain.member

import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class ProfileTest {
    @Test
    fun validProfileTest() {
        val validAddresses = listOf("a", "b", "c", "0", "1", "2")
        for (address in validAddresses) {
            val profileValue = ProfileValue(address)
            assertEquals(address, profileValue.address)
        }
    }

    @Test
    fun invalidAddressTest() {
        val invalidAddresses = listOf("A!", "!", "@", "#", "abc한", "a%1", "", " ")
        for (address in invalidAddresses) {
            val exception = assertThrows<IllegalArgumentException> {
                ProfileValue(address)
            }
            assertEquals("Invalid profile format.", exception.message)
        }
    }

    @Test
    fun url() {
        val profileValue = ProfileValue("username")
        assertEquals("@username", profileValue.url())
    }
}
