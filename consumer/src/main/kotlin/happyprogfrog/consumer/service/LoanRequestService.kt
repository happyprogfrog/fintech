package happyprogfrog.consumer.service

import happyprogfrog.consumer.dto.ReviewResponseDto
import happyprogfrog.domain.domain.LoanReview
import happyprogfrog.domain.repository.LoanReviewRepository
import happyprogfrog.kafka.dto.LoanRequestDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {
    companion object {
        const val NGINX_URL = "http://localhost:8085/css/api/v1/request"
    }

    fun loanRequest(loanRequestDto: LoanRequestDto) {
        // CB 사에 요청 보내기
        val reviewResult = loanRequestToCb(loanRequestDto)

        // 응답값을 DB에 저장하기
        saveLoanReviewData(reviewResult.toLoanReviewEntity())
    }

    private fun loanRequestToCb(loanRequestDto: LoanRequestDto): ReviewResponseDto {
        // WebClient 를 사용하는 방법도 있음
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(1000))
            .setReadTimeout(Duration.ofMillis(1000))
            .build()

        return restTemplate.postForEntity(NGINX_URL, loanRequestDto, ReviewResponseDto::class.java).body!!
    }

    private fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)
}