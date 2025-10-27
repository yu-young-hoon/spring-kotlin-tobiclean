package com.yyh.application.member.provided

import com.yyh.domain.member.Member
import com.yyh.domain.member.MemberInfoUpdateRequest
import com.yyh.domain.member.MemberRegisterRequest
import jakarta.validation.Valid

interface MemberRegister {
    fun register(@Valid request: MemberRegisterRequest): Member

    fun activate(memberId: Long): Member

    fun deactivate(memberId: Long): Member

    fun updateInfo(memberId: Long, @Valid memberInfoUpdateRequest: MemberInfoUpdateRequest): Member
}
