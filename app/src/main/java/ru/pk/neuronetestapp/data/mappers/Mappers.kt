package ru.pk.neuronetestapp.data.mappers

import ru.pk.neuronetestapp.data.dto.NetworkResultDto
import ru.pk.neuronetestapp.data.local.UserData
import ru.pk.neuronetestapp.domain.model.NetworkDataModel
import ru.pk.neuronetestapp.domain.model.UserDataModel


fun UserData.toUserDataModel(): UserDataModel = UserDataModel(
    sixteenDigitsCode = sixteenDigitsCode,
    code = code,
    firstName = firstName,
    lastName = lastName
)

fun UserDataModel.toUserData(): UserData = UserData(
    sixteenDigitsCode = sixteenDigitsCode,
    code = code,
    firstName = firstName,
    lastName = lastName
)

fun NetworkResultDto.toNetworkDataModelList():List<NetworkDataModel> = this.data.map { data ->
    NetworkDataModel(
        date = data.date,
        purchasesListNames = data.namesList,
    )
}