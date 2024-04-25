package happyprogfrog.api.loan.request

import happyprogfrog.domain.domain.UserInfo

data class UserInfoDto (
    val userKey: String,
    val userName: String,
    val userRegistrationNumber: String,
    val userIncomeAmount: Long
){
    fun toEntity(): UserInfo = UserInfo(userKey, userRegistrationNumber, userName, userIncomeAmount)
}