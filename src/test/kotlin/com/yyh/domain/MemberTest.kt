package com.yyh.domain

import com.yyh.domain.Member.MemberRegisterRequest
import com.yyh.util.createMemberRegisterRequest
import com.yyh.util.createPasswordEncoder
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MemberTest {
    var member: Member? = null
    var passwordEncoder: PasswordEncoder? = null

    @BeforeEach
    fun setUp() {
        passwordEncoder = createPasswordEncoder()
        member = Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder!!)
    }

    @Test
    fun registerMember() {
        assertEquals(member?.status, MemberStatus.PENDING)
    }

    @Test
    fun constructorNullCheck() {
        assertThrows<NullPointerException> {
            Member.register(MemberRegisterRequest(null!!, "nickname", "secret"), passwordEncoder!!)
        }
        assertThrows<Exception> {
            Member.register(MemberRegisterRequest(null!!, "nickname", "secret"), passwordEncoder!!)
        }
    }

    @Test
    fun activate() {
        member?.activate()

        assertEquals(member?.status, MemberStatus.ACTIVE)
    }

    @Test
    fun activateFail() {
        member?.activate()

        assertThrows<IllegalStateException> {
            member?.activate()
        }
    }

    @Test
    fun deactivate() {
        member?.activate()

        member?.deactivate()

        assertEquals(member?.status, MemberStatus.DEACTIVATED)
    }

    @Test
    fun deactivateFail() {
        assertThrows<IllegalStateException> {
            member?.deactivate()
        }
    }

    @Test
    fun verifyPassword() {
        assertTrue(member?.verifyPassword("secret", passwordEncoder!!)!!)
        assertFalse(member?.verifyPassword("hello", passwordEncoder!!)!!)
    }

    @Test
    fun changeNickname() {
        assertEquals(member?.nickname, "nickname")

        member?.changeNickname("newNickname")

        assertEquals(member?.nickname, "newNickname")
    }

    @Test
    fun changePassword() {
        member?.changePassword("verysecret", passwordEncoder!!)

        assertTrue(member?.verifyPassword("verysecret", passwordEncoder!!)!!)
    }

    @Test
    fun isActive() {
        member?.activate()

        assertTrue(member?.isActive()!!)

        member?.deactivate()

        assertFalse(member?.isActive()!!)
    }

    @Test
    fun invalidEmail() {
        assertThrows<IllegalArgumentException> {
            Member.register(createMemberRegisterRequest("invalid-email"), passwordEncoder!!)
        }
        Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder!!)
    }
}
