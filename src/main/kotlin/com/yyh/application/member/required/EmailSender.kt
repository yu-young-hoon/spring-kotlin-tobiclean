package com.yyh.application.member.required

import com.yyh.domain.shared.EmailValue

/*
* 이메일을 발송한다
*
* */
interface EmailSender {
    fun sendEmail(to: EmailValue, subject: String, body: String)
}
