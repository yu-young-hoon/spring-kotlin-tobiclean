package com.yyh.adapter.webapi

import com.yyh.adapter.webapi.dto.MemberRegisterResponse
import com.yyh.application.member.provided.MemberRegister
import com.yyh.domain.member.MemberRegisterRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApi(
    val memberRegister: MemberRegister
) {
    @PostMapping
    fun register(@RequestBody @Valid request: MemberRegisterRequest): MemberRegisterResponse {
        val member = memberRegister.register(request)
        return MemberRegisterResponse.of(member)
    }
}