package com.cj.dysarthriachecker.check.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.frameworks.ui.MainActivity
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun DysarthriaCheckView(){
    val navController = rememberNavController()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "DysarthriaCheckView"){
            composable(route="CheckTypeSelectionView"){
                CheckTypeSelectionView()
            }

            composable("DysarthriaCheckView"){
                Surface(modifier = Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "음성 분석 시작하기", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.GraphicEq, contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(accent))

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "구음장애 음성 분석", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                    Text(text = "구음장애 음성인식 모델을 통해 구음장애를 가진 사람의 음성을 정확히 인식할 수 있습니다.", fontSize = 12.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.PersonSearch, contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(accent))

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "대략적인 구음장애 심각도 확인", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                    Text(text = "음성 분석 모델을 통해 구음장애의 대략적인 심각도를 확인할 수 있습니다.", fontSize = 12.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.AutoGraph, contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(accent))

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "검사 추이", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                    Text(text = "검사 기록을 기반으로 구음자애의 심각도 변화 추이를 분석할 수 있습니다.", fontSize = 12.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Image(imageVector = Icons.Rounded.Warning, contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(accent))

                        Text(text = "Dysarthria Checker는 구음장애의 완전한 진단 및 치료를 보장하지 않으며, 환자는 Dysarthria Checker를 통해 치료상의 이익을 얻을 수 없습니다.\n구음장애가 의심되는 경우 의사와 상담을 통해 의학적 조치를 받으십시오.", fontSize = 10.sp, color = gray, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            navController.navigate("CheckTypeSelectionView"){
                                popUpTo("DysarthriaCheckView"){
                                    inclusive = false
                                }
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent, disabledContainerColor = gray
                            ),
                            elevation = ButtonDefaults.buttonElevation(5.dp, disabledElevation = 5.dp)
                        ) {
                            Row{
                                androidx.compose.material3.Text("시작하기", color = white)
                                androidx.compose.material3.Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = white)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DysarthriaCheckView_previews(){
    DysarthriaCheckView()
}