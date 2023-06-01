package com.cj.dysarthriachecker.hospitalMap.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme

@Composable
fun HospitalMapView(){
    DysarthriaCheckerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
            Column(modifier = Modifier.padding(20.dp)) {

            }
        }
    }
}