package com.yyh.domain.member

@JvmInline
value class ProfileValue(val address: String) {
    init {
        val profilePattern = "^[a-z0-9]+$".toRegex()
        require(address.matches(profilePattern)) { "Invalid profile format." }
    }

    fun url(): String = "@${address}"
}
