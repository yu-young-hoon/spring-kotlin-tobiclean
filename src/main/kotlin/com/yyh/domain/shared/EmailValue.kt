package com.yyh.domain.shared

@JvmInline
value class EmailValue(val address: String) {
    init {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        require(address.matches(emailPattern)) { "Invalid email format." }
    }
}
