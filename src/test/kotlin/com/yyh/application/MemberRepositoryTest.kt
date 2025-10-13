package com.yyh.application

import com.yyh.application.required.MemberRepository
import com.yyh.domain.Member
import com.yyh.util.createMemberRegisterRequest
import com.yyh.util.createPasswordEncoder
import kotlin.test.Test
import kotlin.test.assertTrue
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
open class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun createMember() {
        val member =
            Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder = createPasswordEncoder())
        memberRepository.save(member)
        assertTrue(member.id != 0L)
    }

    @Test
    fun duplicateEmailTest() {
        val member1 =
            Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder = createPasswordEncoder())
        memberRepository.save(member1)
        val member2 =
            Member.register(createMemberRegisterRequest("dbtlek@gmail.com"), passwordEncoder = createPasswordEncoder())
        assertThrows<org.springframework.dao.DataIntegrityViolationException> {
            memberRepository.save(member2)
        }
    }
}
