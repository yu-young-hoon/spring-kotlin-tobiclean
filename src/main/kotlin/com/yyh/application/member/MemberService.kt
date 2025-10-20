package com.yyh.application.member

import com.yyh.application.member.provided.MemberRegister
import com.yyh.application.member.required.EmailSender
import com.yyh.application.member.required.MemberRepository
import com.yyh.domain.member.Member
import com.yyh.domain.member.MemberRegisterRequest
import com.yyh.domain.member.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder,
): MemberRegister {
    override fun register(request: MemberRegisterRequest): Member {

        val member = Member.Companion.register(request, passwordEncoder)

        val savedMember = memberRepository.save(member)

        emailSender.sendEmail(
            to = member.email,
            subject = "Welcome to our service",
            body = "Thank you for registering, ${member.nickname}!"
        )
        return savedMember
    }
}
