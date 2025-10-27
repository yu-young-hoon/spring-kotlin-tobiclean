package com.yyh.application.member.required

import com.yyh.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

/*
* 회원 정보를 저장하거나 조회한다
*
* */
@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun save(member: Member): Member

    fun findByEmail(email: String): Member?

    @Query("select m from Member m where m.detail.profile = :profile")
    fun findByProfile(profile: String): Member?
}
