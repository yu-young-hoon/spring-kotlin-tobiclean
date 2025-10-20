package com.yyh.domain.member

import com.yyh.util.createMemberRegisterRequest
import com.yyh.util.createPasswordEncoder
import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows

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
        assertNotNull(member?.detail?.registeredAt)
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
        assertNotNull(member?.detail?.activatedAt)
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
        assertNotNull(member?.detail?.deactivatedAt)
    }

    @Test
    fun deactivateFail() {
        assertThrows<IllegalStateException> {
            member?.deactivate()
        }
    }

    @Test
    fun verifyPassword() {
        Assertions.assertTrue(member?.verifyPassword("secret", passwordEncoder!!)!!)
        Assertions.assertFalse(member?.verifyPassword("hello", passwordEncoder!!)!!)
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

        Assertions.assertTrue(member?.verifyPassword("verysecret", passwordEncoder!!)!!)
    }

    @Test
    fun isActive() {
        member?.activate()

        Assertions.assertTrue(member?.isActive()!!)

        member?.deactivate()

        Assertions.assertFalse(member?.isActive()!!)
    }

    @Test
    fun invalidEmail() {
        assertThrows<IllegalArgumentException> {
            Member.register(createMemberRegisterRequest("invalid-email"), passwordEncoder!!)
        }
        Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder!!)
    }

    @Test
    fun updateInfo() {
        val updateRequest = MemberInfoUpdateRequest(
            nickname = "updatednickname2",
            introduction = "Hello, I'm new here!"
        )

        member?.updateInfo(updateRequest)

        assertEquals("updatednickname2", member?.nickname)
        assertEquals("Hello, I'm new here!", member?.detail?.introduction)
    }
}
