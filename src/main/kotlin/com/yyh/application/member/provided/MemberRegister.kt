package com.yyh.application.member.provided

import com.yyh.domain.member.Member
import com.yyh.domain.member.MemberRegisterRequest

interface MemberRegister {
    fun register(request: MemberRegisterRequest): Member

}
