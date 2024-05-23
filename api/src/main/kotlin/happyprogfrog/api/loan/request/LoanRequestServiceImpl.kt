package happyprogfrog.api.loan.request

import happyprogfrog.api.loan.GenerateKey
import happyprogfrog.api.loan.encrypt.EncryptComponent
import happyprogfrog.domain.repository.UserInfoRepository
import happyprogfrog.kafka.enum.KafkaTopic
import happyprogfrog.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent,
    private val loanRequestSender: LoanRequestSender
): LoanRequestService {

    /**
     * 대출 심사 요청
     */
    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        // 유저 키 생성
        val userKey = generateKey.generateUserKey()

        // 주민 번호 암호화
        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey);

        // 유저 정보 저장
        saveUserInfo(userInfoDto)

        // 카프카를 통해서 유저 심사 요청
        loanRequestReview(userInfoDto)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    /**
     * 유저 정보 저장
     */
    override fun saveUserInfo(userInfoDto: UserInfoDto) = userInfoRepository.save(userInfoDto.toEntity())

    /**
     * 카프카를 통해서 유저 심사 요청
     */
    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(
            KafkaTopic.LOAN_REQUEST,
            userInfoDto.toLoanRequestKafkaDto()
        )
    }
}