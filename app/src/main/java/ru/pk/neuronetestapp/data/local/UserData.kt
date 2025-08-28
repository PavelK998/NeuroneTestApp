package ru.pk.neuronetestapp.data.local

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Serializable
data class UserData(
    val sixteenDigitsCode: String = "",
    val code: String = "",
    val firstName: String = "art",
    val lastName: String = "art"
)

//Здесь в реальном приложении должна быть логика шифрования и дешифрования
// с помощью алгоритма SHA-256, либо любого другого
// в данном примере мы просто сериализуем наш data class в Json
object UserDataSerializer: Serializer<UserData> {
    override val defaultValue: UserData
        get() = UserData()

    override suspend fun readFrom(input: InputStream): UserData {
        val dataBytes = withContext(Dispatchers.IO) {
            input.use {
                it.readBytes()
            }
        }
        val dataString = dataBytes.decodeToString()
        return Json.decodeFromString(dataString)
    }

    override suspend fun writeTo(t: UserData, output: OutputStream) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        withContext(Dispatchers.IO) {
            output.use {
                it.write(bytes)
            }
        }
    }
}
