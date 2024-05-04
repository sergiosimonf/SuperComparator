package com.tfg.supercomparator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val blackGray = Color(0xFF266A4A)
val ivory = Color(0xFFF2F2C8)
val mottledGray = Color(0xFFABABBD)
val bluishGray = Color(0xFF7B7BA7)
val smokeWhite = Color(0xFFE8E8E2)
val oldLace = Color(0xFFBBBBBB)

val GrayBanished = Color(0xFFAAAAAA)

val Green = Color(0xFF1AA57B)
val DarkGreen = Color(0xFF1D2922)

val Black = Color(0xFF000113)
val LightBlueWhite = Color(0xFFF1F5F9) //Social media background
val bluishGrays = Color(0xFF334155)

val ColorScheme.focusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGreen else Black

val ColorScheme.unfocusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) Green else DarkGreen

val ColorScheme.textFieldContainer
    @Composable
    get() = if (isSystemInDarkTheme()) ivory.copy(alpha = 0.6f) else LightBlueWhite