package happyprogfrog.css.service

import happyprogfrog.css.dto.LoanRequestDto
import happyprogfrog.css.dto.LoanResultDto
import org.springframework.stereotype.Service

@Service
class LoanReviewService {
    fun loanReview(loanRequestDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto {
        // 리턴값을 주기 위한 예시이며, 실제로 CB 사의 로직은 이렇게 단순하지 않음
        if (loanRequestDto.userIncomeAmount < 0) throw RuntimeException("Invalid userIncomeAmount Param")
        if (loanRequestDto.userIncomeAmount < 10000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,0.0, 100000000)
        if (loanRequestDto.userIncomeAmount < 20000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,10.0, 200000000)
        if (loanRequestDto.userIncomeAmount < 30000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,9.0, 300000000)
        if (loanRequestDto.userIncomeAmount < 40000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,8.0, 400000000)
        if (loanRequestDto.userIncomeAmount < 50000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,7.0, 500000000)
        if (loanRequestDto.userIncomeAmount >= 50000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey,6.0, 600000000)
        throw RuntimeException("Invalid userIncomeAmount Param")
    }
}