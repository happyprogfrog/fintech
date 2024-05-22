package happyprogfrog.api.loan.encrypt

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

// 참고) 애노테이션으로 만들면 편하다!
@Component
class EncryptComponent {
    companion object {
        private const val SECRET_KEY = "12345678901234561234567890123456"
    }

    private val encoder = Base64.getEncoder();
    private val decoder = Base64.getDecoder();

    fun encryptString(encryptString: String): String {
        // 암호화할 String 을 받아서, ByteArray 로 변환을 하고, 암호화를 한 다음에
        val encryptedString = cipherPkcs5(Cipher.ENCRYPT_MODE, SECRET_KEY).doFinal(encryptString.toByteArray(Charsets.UTF_8))

        // 인코딩을 해서 리턴
        return String(encoder.encode(encryptedString))
    }

    fun decryptString(decryptString: String): String {
        // 복호화는 위와 반대
        // 받은 String 을 디코딩을 먼저해준 다음에
        val byteString = decoder.decode(decryptString.toByteArray(Charsets.UTF_8))

        // 복호화를 한 후에 리턴
        return String(cipherPkcs5(Cipher.DECRYPT_MODE, SECRET_KEY).doFinal(byteString))
    }

    fun cipherPkcs5(opMode: Int, secretKey: String): Cipher {
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opMode, sk, iv)
        return c
    }
}