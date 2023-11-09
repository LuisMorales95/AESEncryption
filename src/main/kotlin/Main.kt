import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

fun main(args: Array<String>) {
    val originalText = "This is the text to be encrypted using AES128."

    val aesEncryption = AESEncryption(128)

    // Encrypt the original text
    val encryptedText = aesEncryption.encrypt(originalText)
    println("Encrypted text: $encryptedText")

    // Decrypt the encrypted text
    val decryptedText = aesEncryption.decrypt(encryptedText)
    println("Decrypted text: $decryptedText")
}

class AESEncryption(val size: Int) {

    val key: SecretKey = KeyGenerator.getInstance("AES").apply {
        init(128) // 128-bit key length
    }.generateKey()

    fun encrypt(text: String): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        val encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes)
        return encryptedBase64
    }

    fun decrypt(encryptedText: String): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, key)
        val encryptedBytes = Base64.getDecoder().decode(encryptedText)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }
}
