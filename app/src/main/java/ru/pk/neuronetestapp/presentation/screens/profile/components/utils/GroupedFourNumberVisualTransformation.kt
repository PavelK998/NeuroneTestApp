package ru.pk.neuronetestapp.presentation.screens.profile.components.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupedFourNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Форматируем текст, добавляя пробелы каждые 4 символа
        val formatted = text.text.chunked(4).joinToString(" ")
        return TransformedText(
            text = AnnotatedString(formatted),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // Учитываем пробелы при преобразовании позиции курсора
                    val chunks = text.text.take(offset).chunked(4)
                    return offset + chunks.size - if (chunks.isEmpty()) 0 else 1
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // Учитываем пробелы при обратном преобразовании
                    val spaces = offset / 5
                    return offset - spaces
                }
            }
        )
    }
}