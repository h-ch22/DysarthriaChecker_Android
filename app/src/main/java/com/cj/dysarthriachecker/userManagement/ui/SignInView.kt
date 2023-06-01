package com.cj.dysarthriachecker.userManagement.ui

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.R
import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.frameworks.helper.DataStoreUtil
import com.cj.dysarthriachecker.frameworks.ui.MainActivity
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.red
import com.cj.dysarthriachecker.ui.theme.white
import com.cj.dysarthriachecker.userManagement.helper.UserManagement
import com.cj.dysarthriachecker.userManagement.models.AuthInfoModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun SignInView(){
    val navController = rememberNavController()
    val helper = UserManagement()

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var showProgress by remember{
        mutableStateOf(false)
    }

    var showFailDialog by remember{
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val dataStoreUtil = DataStoreUtil(context)
    val scope = rememberCoroutineScope()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "SignInView"){
            composable(route="SignUpView"){
                SignUpView()
            }

            composable(route="ResetPasswordView"){

            }

            composable(route="SignInView"){
                Surface(modifier= Modifier
                    .fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.ic_appstore),
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    clip = true
                                )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Dysarthria",
                                modifier = Modifier,
                                color = DysarthriaCheckerColorPalette.current.txtColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = " Checker",
                                modifier = Modifier,
                                color = DysarthriaCheckerColorPalette.current.txtColor,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))


                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = email,
                            onValueChange = { textVal : String -> email = textVal },
                            label = { Text("E-Mail") },
                            placeholder = { Text("E-Mail") } ,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.AlternateEmail,
                                    contentDescription = null
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = accent,
                                focusedBorderColor = accent,
                                errorCursorColor = red,
                                errorLeadingIconColor = red,
                                disabledPlaceholderColor = gray,
                                focusedTextColor = accent,
                                focusedLabelColor = accent,
                                focusedLeadingIconColor = accent,
                                disabledTextColor = gray,
                                unfocusedLabelColor = DysarthriaCheckerColorPalette.current.txtColor,
                                unfocusedLeadingIconColor = DysarthriaCheckerColorPalette.current.txtColor,
                                unfocusedSupportingTextColor = DysarthriaCheckerColorPalette.current.txtColor,
                                selectionColors = TextSelectionColors(handleColor = accent, backgroundColor = accent.copy(alpha = 0.5f))
                            ),
                            maxLines = 1,
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = { textVal : String -> password = textVal },
                            label = { Text("비밀번호") },
                            placeholder = { Text("비밀번호") } ,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Key,
                                    contentDescription = null
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = accent,
                                focusedBorderColor = accent,
                                errorCursorColor = red,
                                errorLeadingIconColor = red,
                                disabledPlaceholderColor = gray,
                                focusedTextColor = accent,
                                focusedLabelColor = accent,
                                focusedLeadingIconColor = accent,
                                disabledTextColor = gray,
                                unfocusedLabelColor = DysarthriaCheckerColorPalette.current.txtColor,
                                unfocusedLeadingIconColor = DysarthriaCheckerColorPalette.current.txtColor,
                                unfocusedSupportingTextColor = DysarthriaCheckerColorPalette.current.txtColor,
                                selectionColors = TextSelectionColors(handleColor = accent, backgroundColor = accent.copy(alpha = 0.5f))

                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            maxLines = 1,
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = {
                            showProgress = true

                            helper.signIn(
                                email, password
                            ){
                                if(it){
                                    scope.launch {
                                        dataStoreUtil.saveToDataStore(AES256Util.encrypt(email), AES256Util.encrypt(password))
                                        context.startActivity(Intent(context, MainActivity :: class.java))
                                    }

                                } else{
                                    showProgress = false
                                    showFailDialog = true
                                }
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth(),
                            enabled = !email.isEmpty() && !password.isEmpty() && email.contains("@") && password.length >= 6,
                            contentPadding = PaddingValues(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent, disabledContainerColor = gray
                            ),
                            elevation = ButtonDefaults.buttonElevation(5.dp, disabledElevation = 5.dp)
                        ) {
                            Row{
                                Text("로그인", color = white)
                                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = white)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row {
                            TextButton(onClick = {  }) {
                                Text("비밀번호 재설정", color = accent)
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            TextButton(onClick = {
                                navController.navigate("SignUpView"){
                                    popUpTo("SignInView"){
                                        inclusive = false
                                    }
                                }
                            }) {
                                Text("회원가입", color = accent)
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = "© 2023 Ha Changjin\nAll Rights Reserved",
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(value=15f, TextUnitType.Sp),
                            color = gray,
                            fontSize = 12.sp)

                        if(showProgress){
                            Dialog(
                                onDismissRequest = { showProgress = false },
                                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                            ) {
                                Box(
                                    contentAlignment= Alignment.Center,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(
                                            DysarthriaCheckerColorPalette.current.background.copy(
                                                alpha = 0.7f
                                            ),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                ) {
                                    CircularProgressIndicator(color = accent)
                                }
                            }
                        }

                        if(showFailDialog){
                            AlertDialog(
                                onDismissRequest = { showFailDialog = false },

                                confirmButton = {
                                    TextButton(onClick = {
                                        showFailDialog = false
                                    }){
                                        Text("확인", color = accent, fontWeight = FontWeight.Bold)
                                    }
                                },
                                title = {
                                    Text("오류")
                                },
                                text = {
                                    Text("요청하신 작업을 처리하는 중 문제가 발생했습니다.\n정상 로그인 여부, 네트워크 상태를 확인하거나 나중에 다시 시도해주세요.")
                                },
                                icon = {
                                    Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignInView_previews(){
    SignInView()
}