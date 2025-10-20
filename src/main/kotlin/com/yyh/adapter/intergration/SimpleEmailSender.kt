package com.yyh.adapter.intergration

import com.yyh.application.member.required.EmailSender
import com.yyh.domain.shared.EmailValue
import org.springframework.stereotype.Component

@Component
class SimpleEmailSender : EmailSender {
    override fun sendEmail(to: EmailValue, subject: String, body: String) {
        // 간단한 이메일 발송 로직 (예: 콘솔 출력)
        println("Sending email to: ${to.address}")
        println("Subject: $subject")
        println("Body: $body")
    }
}
