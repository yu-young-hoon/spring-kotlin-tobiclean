package com.yyh.application.required

import com.yyh.domain.EmailValue

/*
* 이메일을 발송한다
*
* */
interface EmailSender {
    fun sendEmail(to: EmailValue, subject: String, body: String)
}
