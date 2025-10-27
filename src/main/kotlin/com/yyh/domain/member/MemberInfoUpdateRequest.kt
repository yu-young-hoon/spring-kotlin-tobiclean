package com.yyh.domain.member

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class MemberInfoUpdateRequest(
    @Size(min = 5, max = 20) val nickname: String,
    @Size(min = 8, max = 100) val profileAddress: String,
    @NotNull val introduction: String,
)