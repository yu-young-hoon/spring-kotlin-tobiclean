// package com.yyh.application
//
// import com.yyh.application.provided.MemberRegister
// import com.yyh.application.required.EmailSender
// import com.yyh.application.required.MemberRepository
// import com.yyh.domain.EmailValue
// import com.yyh.domain.Member
// import com.yyh.domain.PasswordEncoder
// import kotlin.test.Test
// import org.junit.jupiter.api.Assertions.*
//
// class MemberServiceTest {
//     @Test
//     fun testMemberRegister() {
//         MemberService(
//             MemberRepositoryStub(),
//             EmailSenderStub(),
//             PasswordEncoderStub()
//         )
//     }
//
//     class MemberRepositoryStub : MemberRepository {
//         override fun save(member: Member): Member {
//             TODO("Not yet implemented")
//         }
//     }
//
//     class EmailSenderStub : EmailSender {
//         override fun sendEmail(to: EmailValue, subject: String, body: String) {
//             TODO("Not yet implemented")
//         }
//     }
//
//     class PasswordEncoderStub : PasswordEncoder {
//         override fun encode(password: String): String {
//             TODO("Not yet implemented")
//         }
//
//         override fun matches(password: String, passwordHash: String): Boolean {
//             TODO("Not yet implemented")
//         }
//     }
//
// }
