package com.yyh.application

import com.yyh.application.provided.MemberRegister
import com.yyh.application.required.EmailSender
import com.yyh.application.required.MemberRepository
import com.yyh.domain.Member
import com.yyh.domain.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder,
): MemberRegister {
    override fun register(request: Member.MemberRegisterRequest): Member {

        val member = Member.register(request, passwordEncoder)

        val savedMember = memberRepository.save(member)

        emailSender.sendEmail(
            to = member.email,
            subject = "Welcome to our service",
            body = "Thank you for registering, ${member.nickname}!"
        )
        return savedMember
    }
}