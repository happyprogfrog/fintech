package happyprogfrog.api.loan.request

import happyprogfrog.api.loan.GenerateKey
import happyprogfrog.api.loan.encrypt.EncryptComponent
import happyprogfrog.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent
): LoanRequestService {

    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        // 유저키 생성
        val userKey = generateKey.generateUserKey()

        // 주민 번호 암호화
        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        // 유저 정보 저장
        saveUserInfo(loanRequestInputDto.toUserInfoDto(userKey))

        // 카프카를 통해서 유저 심사 요청
        loanRequestReview(userKey)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())

    override fun loanRequestReview(userKey: String) {
        // TODO
    }
}