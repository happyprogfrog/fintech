package happyprogfrog.css.controller

import happyprogfrog.css.dto.LoanRequestDto
import happyprogfrog.css.dto.LoanResultDto
import happyprogfrog.css.service.LoanReviewService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/css/api/v1")
class LoanRequestController(
    private val loanReviewService: LoanReviewService
) {
    @PostMapping("/request")
    fun loanReview(@RequestBody requestInputDto: LoanRequestDto.RequestInputDto
    ): LoanResultDto.ResponseDto =
        loanReviewService.loanReview(requestInputDto)
}