package happyprogfrog.css.dto

class LoanResultDto {
    data class ResponseDto(
        val userKey: String,
        val interest: Double,
        val limitAmount: Long
    )
}