package com.yyh.application.member

import com.yyh.application.member.required.MemberRepository
import com.yyh.domain.member.Member
import com.yyh.domain.member.MemberStatus
import com.yyh.util.createMemberRegisterRequest
import com.yyh.util.createPasswordEncoder
import kotlin.test.Test
import kotlin.test.assertTrue
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.ActiveProfiles

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun createMember() {
        val memberRegisterRequest = createMemberRegisterRequest("dbtlek@gmail.com")
        val member = Member.register(
            memberRegisterRequest = memberRegisterRequest,
            passwordEncoder = createPasswordEncoder()
        )
        memberRepository.save(member)
        assertTrue(member.id != 0L)

        val found = memberRepository.findById(member.id!!)
        assertTrue(found.get().status == MemberStatus.PENDING)
        assertNotNull(found.get().detail)
    }

    @Test
    fun duplicateEmailTest() {
        val member1 = Member.register(
            createMemberRegisterRequest("dbtlek@gmail.com"),
            passwordEncoder = createPasswordEncoder()
        )
        memberRepository.save(member1)

        val member2 = Member.register(
            createMemberRegisterRequest("dbtlek@gmail.com"),
            passwordEncoder = createPasswordEncoder()
        )
        assertThrows<DataIntegrityViolationException> {
            memberRepository.save(member2)
        }
    }
}
