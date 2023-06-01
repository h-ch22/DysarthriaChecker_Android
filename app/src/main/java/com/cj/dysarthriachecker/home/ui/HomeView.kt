package com.cj.dysarthriachecker.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.userManagement.helper.UserManagement

@Composable
fun HomeView(){
    DysarthriaCheckerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
            Column(modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "안녕하세요,\n${AES256Util.decrypt(UserManagement.userInfo?.name)}님😆", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}