package com.tfg.supercomparator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val blackGray = Color(0xFF4F4F54)
val ivory = Color(0xFFF2F2C8)
val mottledGray = Color(0xFFABABBD)
val bluishGray = Color(0xFF7B7BA7)
val smokeWhite = Color(0xFFE8E8E2)

val GrayBanished = Color(0xFFAAAAAA)

val Green = Color(0xFF1AA57B)
val DarkGreen = Color(0xFF004346)

val Black = Color(0xFF000113)
val LightBlueWhite = Color(0xFFF1F5F9) //Social media background
val bluishGrays = Color(0xFF334155)

val ColorScheme.focusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Black

val ColorScheme.unfocusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF475569)

val ColorScheme.textFieldContainer
    @Composable
    get() = if (isSystemInDarkTheme()) ivory.copy(alpha = 0.6f) else LightBlueWhite