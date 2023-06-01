package com.cj.dysarthriachecker.more.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.R
import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.userManagement.helper.UserManagement

@Composable
fun MoreView(){
    val navController = rememberNavController()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "MoreView") {
            composable(route = "EmptyView"){

            }

            composable(route = "MoreView"){
                Surface(modifier = Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Dysarthria",
                                modifier = Modifier,
                                color = DysarthriaCheckerColorPalette.current.txtColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = " Checker",
                                modifier = Modifier,
                                color = DysarthriaCheckerColorPalette.current.txtColor,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            navController.navigate("ProfileView"){
                                popUpTo("MoreView"){
                                    inclusive = false
                                }
                            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DysarthriaCheckerColorPalette.current.btnColor
                            ), elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            shape = RoundedCornerShape(15.dp),
                            contentPadding = PaddingValues(start = 15.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_appstore),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(45.dp)
                                        .height(45.dp)
                                        .shadow(
                                            elevation = 8.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            clip = true
                                        )
                                )

                                Column(horizontalAlignment = Alignment.Start) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(AES256Util.decrypt(UserManagement.userInfo?.name), fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = DysarthriaCheckerColorPalette.current.txtColor)
                                        Spacer(modifier = Modifier.weight(1f))
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text("프로필 보기", fontSize = 10.sp, color = gray)
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Divider(modifier = Modifier.fillMaxWidth(), color = gray.copy(alpha = 0.5f))

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DysarthriaCheckerColorPalette.current.btnColor
                            ), elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.AutoGraph, contentDescription = null, colorFilter = ColorFilter.tint(
                                    accent))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("검사 추세 및 기록", fontSize = 18.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DysarthriaCheckerColorPalette.current.btnColor
                            ), elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.CircleNotifications, contentDescription = null, colorFilter = ColorFilter.tint(
                                    accent))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("공지사항", fontSize = 18.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DysarthriaCheckerColorPalette.current.btnColor
                            ), elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.Favorite, contentDescription = null, colorFilter = ColorFilter.tint(
                                    accent))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("피드백 허브", fontSize = 18.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DysarthriaCheckerColorPalette.current.btnColor
                            ), elevation = ButtonDefaults.buttonElevation(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                Image(imageVector = Icons.Default.Info, contentDescription = null, colorFilter = ColorFilter.tint(
                                    accent))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("정보", fontSize = 18.sp, color = DysarthriaCheckerColorPalette.current.txtColor)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}