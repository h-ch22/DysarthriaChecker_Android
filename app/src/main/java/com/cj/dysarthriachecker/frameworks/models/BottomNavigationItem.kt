package com.cj.dysarthriachecker.frameworks.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.VoiceChat
import androidx.compose.ui.graphics.vector.ImageVector

const val HOME = "HOME"
const val HOSPITAL_MAP = "HOSPITAL_MAP"
const val CHECK_DYSARTHRIA = "CHECK_DYSARTHRIA"
const val TRAIN = "TRAIN"
const val MORE = "MORE"

sealed class BottomNavigationItem(
    val title : String, val icon : ImageVector, val screenRoute : String
){
    object Home : BottomNavigationItem(
        "홈", Icons.Default.Home, HOME
    )

    object HospitalMap : BottomNavigationItem(
        "병원 지도", Icons.Default.Map, HOSPITAL_MAP
    )

    object CheckDysarthria : BottomNavigationItem(
        "음성 분석", Icons.Default.GraphicEq, CHECK_DYSARTHRIA
    )

    object Train : BottomNavigationItem(
        "구음장애 교정", Icons.Default.VoiceChat, TRAIN
    )

    object More : BottomNavigationItem(
        "더 보기", Icons.Default.MoreHoriz, MORE
    )
}
