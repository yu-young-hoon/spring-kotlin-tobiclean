package com.yyh.application.provided

import com.yyh.domain.Member

interface MemberRegister {
    fun register(request: Member.MemberRegisterRequest): Member

}
