package ru.pk.neuronetestapp.data.repository

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.data.dto.NetworkResultDto
import ru.pk.neuronetestapp.data.mappers.toNetworkDataModelList
import ru.pk.neuronetestapp.domain.repository.NetworkRepository
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : NetworkRepository {
    override suspend fun getDataFromServer() = withContext(Dispatchers.IO) {

        //Имитация длительной операции
        delay(2000)
        val inputStream = context.resources.openRawResource(R.raw.request)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString =  reader.use { it.readText() }
        val gson = Gson()
        val networkResult = gson.fromJson(jsonString, NetworkResultDto::class.java)
        networkResult.toNetworkDataModelList()
    }
}