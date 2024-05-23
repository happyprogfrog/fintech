package happyprogfrog.api.loan.request

import happyprogfrog.domain.domain.UserInfo

interface LoanRequestService {
    fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(userInfoDto: UserInfoDto): UserInfo

    fun loanRequestReview(userInfoDto: UserInfoDto)
}