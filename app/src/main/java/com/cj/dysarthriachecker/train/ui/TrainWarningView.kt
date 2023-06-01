package com.cj.dysarthriachecker.train.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraFront
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.orange
import com.cj.dysarthriachecker.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainWarningView(){
    val navController = rememberNavController()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "TrainWarningView") {
            composable(route = "EmptyView") {
            }

            composable(route = "TrainWarningView"){
                Scaffold(topBar = {
                    TopAppBar (
                        title = {
                            Text(text = "", color = DysarthriaCheckerColorPalette.current.txtColor)
                        },
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = accent)
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = DysarthriaCheckerColorPalette.current.background,
                            titleContentColor = DysarthriaCheckerColorPalette.current.txtColor
                        )
                    )
                }, content = {
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(it), color = DysarthriaCheckerColorPalette.current.background) {
                        Column(modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(imageVector = Icons.Rounded.Warning,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(orange),
                                modifier = Modifier.size(100.dp)
                            )

                            Text(text = "구음장애 교정 주의사항",
                                color = DysarthriaCheckerColorPalette.current.txtColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp)

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start) {
                                Icon(imageVector = Icons.Default.Face, contentDescription = null)

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "기본 표정 알아보기",
                                        fontWeight = FontWeight.SemiBold,
                                        color = DysarthriaCheckerColorPalette.current.txtColor)

                                    Text(text = "이 교정 프로세스에서 기본 표정은 입술이 평행한 상태입니다.\n입술이 평행한 상태를 유지하십시오.", color = gray, fontSize = 10.sp, textAlign = TextAlign.Start)

                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start) {
                                Icon(imageVector = Icons.Default.Checklist, contentDescription = null)

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "본인의 의사에 따라 프로세스 진행 여부 결정하기",
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.SemiBold,
                                        color = DysarthriaCheckerColorPalette.current.txtColor)

                                    Text(text = "타인에 의해 강제로 교정 프로세스에 참여 중인 경우 본인의 의사에 따라 교정 프로세스의 진행 여부를 결정하십시오.", color = gray, fontSize = 10.sp, textAlign = TextAlign.Start)

                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null)

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "프로세스와 관련된 중요한 안내사항",
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.SemiBold,
                                        color = DysarthriaCheckerColorPalette.current.txtColor)

                                    Text(text = "이 교정 프로세스를 통해 사용자는 구음장애 및 기타 치료 상의 이익을 얻을 수 없으며, 그 어떠한 치료 및 교정 효과도 Dysarthria Checker에서는 보장하지 않습니다.", color = gray, fontSize = 10.sp, textAlign = TextAlign.Start)

                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start) {
                                Icon(imageVector = Icons.Default.CameraFront, contentDescription = null)

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(text = "AR 세션 이용과 관련된 중요한 주의사항",
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.SemiBold,
                                        color = DysarthriaCheckerColorPalette.current.txtColor)

                                    Text(text = "부상의 위험이 있으니 AR 세션을 진행하는 동안 뒤로 걷기를 포함한 신체적 움직임이 없는 장소에서 편안한 자세로 앉아 세션을 진행하십시오.\n정신 착란을 포함한 정신적인 문제가 발생할 수 있으니 너무 오랜시간 동안 세션을 진행하지 말고 적당한 시간을 정해 진행하고, 되도록 보호자의 지도 하에 세션을 진행하십시오.", color = gray, fontSize = 10.sp, textAlign = TextAlign.Start)

                                }

                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {

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

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(text = "교정 프로세스를 진행하는 동안 촬영된 데이터는 서버 및 기기에 저장되지 않습니다.", color = gray, fontSize = 10.sp, textAlign = TextAlign.Start)

                        }
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrainWarningView_previews(){
    TrainWarningView()
}