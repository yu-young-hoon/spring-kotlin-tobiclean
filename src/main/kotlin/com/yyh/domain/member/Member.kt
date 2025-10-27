package com.yyh.domain.member

import com.yyh.domain.AbstractEntity
import com.yyh.domain.shared.EmailValue
import jakarta.persistence.*
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache
import org.springframework.util.Assert

@NaturalIdCache
@Entity
@Table(
    name = "member",
)
class Member(
    @NaturalId
    val email: EmailValue,

    var nickname: String,

    var passwordHash: String,

    var status: MemberStatus = MemberStatus.PENDING,

    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    val detail: MemberDetail,
) : AbstractEntity() {
    fun activate() {
        Assert.state(status == MemberStatus.PENDING, "Member status must be PENDING to activate.")

        this.status = MemberStatus.ACTIVE
        this.detail.markActivatedAt()
    }

    fun deactivate() {
        Assert.state(status == MemberStatus.ACTIVE, "Member status must be ACTIVE to deactivate.")

        this.status = MemberStatus.DEACTIVATED
        this.detail.markDeactivatedAt()
    }

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, this.passwordHash)
    }

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun updateInfo(memberInfoUpdateRequest: MemberInfoUpdateRequest) {
        Assert.state(status == MemberStatus.ACTIVE, "Member status must be ACTIVE to activate.")

        this.nickname = memberInfoUpdateRequest.nickname
        this.detail.introduction = memberInfoUpdateRequest.introduction
    }

    fun changePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.passwordHash = passwordEncoder.encode(password)
    }

    fun isActive(): Boolean {
        return this.status == MemberStatus.ACTIVE
    }

    companion object {
        fun register(memberRegisterRequest: MemberRegisterRequest, passwordEncoder: PasswordEncoder): Member {
            val passwordHash = passwordEncoder.encode(memberRegisterRequest.password)
            val email = EmailValue(memberRegisterRequest.email)
            val memberDetail = MemberDetail.create()
            return Member(
                email = email,
                nickname = memberRegisterRequest.nickname,
                passwordHash = passwordHash,
                detail = memberDetail
            )
        }
    }
}

