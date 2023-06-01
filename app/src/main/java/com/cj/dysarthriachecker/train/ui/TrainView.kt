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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.R
import com.cj.dysarthriachecker.check.ui.CheckTypeSelectionView
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.orange
import com.cj.dysarthriachecker.ui.theme.white

@Composable
fun TrainView(){
    val navController = rememberNavController()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "TrainView") {
            composable(route = "TrainWarningView") {
                TrainWarningView()
            }

            composable(route = "TrainView"){
                Surface(modifier = Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                    Column(modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "교정 시작하기",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = DysarthriaCheckerColorPalette.current.txtColor
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "입술 운동",
                                fontWeight = FontWeight.Bold,
                                color = DysarthriaCheckerColorPalette.current.txtColor
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "하루에 3~5번 시행해주세요.",
                                fontSize = 10.sp,
                                color = gray
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            navController.navigate("TrainWarningView"){
                                popUpTo("TrainView"){
                                    inclusive = false
                                }
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange.copy(alpha = 0.3f), disabledContainerColor = gray
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_a),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_b),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_c),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_d),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_e),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_f),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_g),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_h),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.lip_i),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Text(text = "입술 운동 시작하기", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = DysarthriaCheckerColorPalette.current.txtColor)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "혀 운동",
                                fontWeight = FontWeight.Bold,
                                color = DysarthriaCheckerColorPalette.current.txtColor
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "하루에 3~5번 시행해주세요.",
                                fontSize = 10.sp,
                                color = gray
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            navController.navigate("TrainWarningView"){
                                popUpTo("TrainView"){
                                    inclusive = false
                                }
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent.copy(alpha = 0.3f), disabledContainerColor = gray
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_a),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_b),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_c),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_d),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_e),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Image(
                                        painter = painterResource(id = R.drawable.tongue_f),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Text(text = "혀 운동 시작하기", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = DysarthriaCheckerColorPalette.current.txtColor)
                                }
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
fun TrainView_previews(){
    TrainView()
}