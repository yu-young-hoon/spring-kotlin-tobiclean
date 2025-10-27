package com.yyh.adapter.webapi.dto

import com.yyh.domain.member.Member

data class MemberRegisterResponse(
    val memberId: Long,
    val emailAddress: String,
) {
    companion object {
        fun of(member: Member): MemberRegisterResponse {
            return MemberRegisterResponse(
                memberId = member.id,
                emailAddress = member.email.address,
            )
        }
    }
}