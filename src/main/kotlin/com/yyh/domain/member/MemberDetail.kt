package com.yyh.domain.member

import com.yyh.domain.AbstractEntity
import jakarta.persistence.Entity
import org.springframework.util.Assert
import java.time.LocalDateTime

@Entity
class MemberDetail(
    val profile: ProfileValue,
    var introduction: String,
    var registeredAt: LocalDateTime? = null,
    var activatedAt: LocalDateTime? = null,
    var deactivatedAt: LocalDateTime? = null,
) : AbstractEntity() {

    fun markActivatedAt() {
        Assert.isTrue(activatedAt == null, "MemberDetail is already activated.")

        this.activatedAt = LocalDateTime.now()
    }

    fun markDeactivatedAt() {
        Assert.isTrue(deactivatedAt == null, "MemberDetail is already deactivated.")

        this.deactivatedAt = LocalDateTime.now()
    }

    companion object {
        internal fun create(): MemberDetail {
            return MemberDetail(
                profile = ProfileValue("000"),
                introduction = "안녕하세요! 반갑습니다.",
                registeredAt = LocalDateTime.now()
            )
        }
    }
}
