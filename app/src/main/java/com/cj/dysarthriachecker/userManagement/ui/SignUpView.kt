package com.cj.dysarthriachecker.userManagement.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.red
import com.cj.dysarthriachecker.ui.theme.white
import com.cj.dysarthriachecker.userManagement.helper.UserManagement
import com.cj.dysarthriachecker.userManagement.models.SignUpFailType
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

fun getAlertTitle(model : SignUpFailType) : String{
    when(model){
        SignUpFailType.ACCEPT_LICENSE -> return "약관 동의 필요"
        SignUpFailType.EMPTY_FIELD -> return "공백 필드"
        SignUpFailType.WEAK_PASSWORD -> return "안전하지 않은 비밀번호"
        SignUpFailType.PASSWORD_MISMATCH -> return "비밀번호 불일치"
        SignUpFailType.UNSUPPORTED_EMAIL_TYPE -> return "잘못된 E-Mail 형식"
        SignUpFailType.AUTH_ERROR -> return "회원가입 오류"
    }
}

fun getAlertContents(model : SignUpFailType) : String{
    when(model){
        SignUpFailType.ACCEPT_LICENSE -> return "필수 동의 약관을 읽고 동의해주세요."
        SignUpFailType.EMPTY_FIELD -> return "모든 필드를 채워주세요."
        SignUpFailType.WEAK_PASSWORD -> return "보안을 위해 6자리 이상의 비밀번호를 설정해주세요."
        SignUpFailType.PASSWORD_MISMATCH -> return "비밀번호와 비밀번호 확인이 일치하지 않습니다."
        SignUpFailType.UNSUPPORTED_EMAIL_TYPE -> return "잘못된 E-Mail 형식입니다."
        SignUpFailType.AUTH_ERROR -> return "회원가입 중 문제가 발생했습니다.\n기가입 여부, 네트워크 상태를 확인하거나 나중에 다시 시도하십시오."
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(){
    val navController = rememberNavController()

    var showView by remember{
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf("반가워요!")
    }

    var name by remember{
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var checkPassword by remember{
        mutableStateOf("")
    }

    var phone by remember{
        mutableStateOf("")
    }

    var acceptEULA by remember{
        mutableStateOf(false)
    }

    var acceptPrivacy by remember{
        mutableStateOf(false)
    }

    var acceptSensitive by remember{
        mutableStateOf(false)
    }

    var acceptHumanResearch by remember{
        mutableStateOf(false)
    }

    var alertModel by remember{
        mutableStateOf<SignUpFailType?>(null)
    }

    var showAlert by remember{
        mutableStateOf(false)
    }

    var showProgress by remember{
        mutableStateOf(false)
    }

    val helper = UserManagement()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "SignUpView"){
            composable(route="DiseaseCheckView"){
                DiseaseCheckView(entryPoint = "SignUpView")
            }

            composable(route="SignUpView"){
                Scaffold(topBar = {
                    TopAppBar (
                        title = {
                            Text(text = "회원가입", color = DysarthriaCheckerColorPalette.current.txtColor)
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
                        .padding(it)
                        .fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                        Column(modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top) {
                            AnimatedVisibility(visible = true){
                                androidx.compose.material3.Text(
                                    text = title,
                                    color = DysarthriaCheckerColorPalette.current.txtColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }

                            LaunchedEffect(Unit){
                                delay(1.seconds)
                                title = "E-Mail을 입력해주세요."
                                showView = true
                            }

                            Spacer(modifier = Modifier.height(40.dp))

                            AnimatedVisibility(visible = showView){
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = email,
                                    onValueChange = { textVal : String -> email = textVal },
                                    label = { androidx.compose.material3.Text("E-Mail") },
                                    placeholder = { androidx.compose.material3.Text("E-Mail") } ,
                                    leadingIcon = {
                                        androidx.compose.material3.Icon(
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
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            AnimatedVisibility(visible = email != ""){
                                title = "비밀번호를 입력해주세요."

                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = password,
                                    onValueChange = { textVal : String -> password = textVal },
                                    label = { androidx.compose.material3.Text("비밀번호") },
                                    placeholder = { androidx.compose.material3.Text("비밀번호") } ,
                                    leadingIcon = {
                                        androidx.compose.material3.Icon(
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
                                    maxLines = 1,
                                    singleLine = true,
                                    visualTransformation = PasswordVisualTransformation())
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            AnimatedVisibility(visible = password != ""){
                                title = "비밀번호를 한번 더 입력해주세요."

                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = checkPassword,
                                    onValueChange = { textVal : String -> checkPassword = textVal },
                                    label = { androidx.compose.material3.Text("한번 더") },
                                    placeholder = { androidx.compose.material3.Text("한번 더") } ,
                                    leadingIcon = {
                                        androidx.compose.material3.Icon(
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
                                    maxLines = 1,
                                    singleLine = true,
                                    visualTransformation = PasswordVisualTransformation())
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            AnimatedVisibility(visible = checkPassword != ""){
                                title = "이름을 입력해주세요."

                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = name,
                                    onValueChange = { textVal : String -> name = textVal },
                                    label = { androidx.compose.material3.Text("이름") },
                                    placeholder = { androidx.compose.material3.Text("이름") } ,
                                    leadingIcon = {
                                        androidx.compose.material3.Icon(
                                            imageVector = Icons.Default.Person,
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
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            AnimatedVisibility(visible = name != ""){
                                title = "연락처를 입력해주세요."

                                Column {
                                    OutlinedTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = phone,
                                        onValueChange = { textVal : String -> phone = textVal },
                                        label = { androidx.compose.material3.Text("연락처") },
                                        placeholder = { androidx.compose.material3.Text("연락처") } ,
                                        leadingIcon = {
                                            androidx.compose.material3.Icon(
                                                imageVector = Icons.Default.Phone,
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
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                                    )

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = acceptEULA,
                                            onCheckedChange = { acceptEULA = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = accent,
                                                checkmarkColor = white,
                                                uncheckedColor = accent
                                            ))

                                        Spacer(modifier=Modifier.width(5.dp))

                                        Text(text="최종 사용권 계약서 (필수)", color = DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = acceptPrivacy,
                                            onCheckedChange = { acceptPrivacy = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = accent,
                                                checkmarkColor = white,
                                                uncheckedColor = accent
                                            ))

                                        Spacer(modifier=Modifier.width(5.dp))

                                        Text(text="개인정보 수집 및 처리방침 (필수)", color = DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = acceptSensitive,
                                            onCheckedChange = { acceptSensitive = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = accent,
                                                checkmarkColor = white,
                                                uncheckedColor = accent
                                            ))

                                        Spacer(modifier=Modifier.width(5.dp))

                                        Text(text="민감정보 수집 및 처리방침 (필수)", color = DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = acceptHumanResearch,
                                            onCheckedChange = { acceptHumanResearch = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = accent,
                                                checkmarkColor = white,
                                                uncheckedColor = accent
                                            ))

                                        Spacer(modifier=Modifier.width(5.dp))

                                        Text(text="인간대상연구에 대한 동의서 (필수)", color = DysarthriaCheckerColorPalette.current.txtColor)
                                    }
                                }

                            }

                            AnimatedVisibility(visible = name != "" && phone != "" && password != "" && checkPassword != "" && acceptEULA && acceptPrivacy && acceptHumanResearch && acceptSensitive) {
                                Spacer(modifier = Modifier.height(20.dp))

                                Button(onClick = {
                                    if(!email.contains("@")){
                                        alertModel = SignUpFailType.UNSUPPORTED_EMAIL_TYPE
                                        showAlert = true
                                    } else if(password != checkPassword){
                                        alertModel = SignUpFailType.PASSWORD_MISMATCH
                                        showAlert = true
                                    } else if(password.length < 6){
                                        alertModel = SignUpFailType.WEAK_PASSWORD
                                        showAlert = true
                                    } else if(email.isEmpty() || password.isEmpty() || checkPassword.isEmpty() || name.isEmpty() || phone.isEmpty()){
                                        alertModel = SignUpFailType.EMPTY_FIELD
                                        showAlert = true
                                    } else if(!acceptEULA || !acceptHumanResearch || !acceptPrivacy || !acceptSensitive){
                                        alertModel = SignUpFailType.ACCEPT_LICENSE
                                        showAlert = true
                                    } else{
                                        showProgress = true
                                        helper.signUp(email, password, phone, name){
                                            if(it){
                                                showProgress = false
                                                navController.navigate("DiseaseCheckView"){
                                                    popUpTo("SignUpView"){
                                                        inclusive = true
                                                    }
                                                }
                                            } else{
                                                showProgress = false
                                                alertModel = SignUpFailType.AUTH_ERROR
                                                showAlert = true
                                            }
                                        }
                                    }
                                },
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    enabled = name != "" && phone != "" && password != "" && checkPassword != "" && acceptEULA && acceptPrivacy && acceptHumanResearch && acceptSensitive,
                                    contentPadding = PaddingValues(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = accent, disabledContainerColor = gray
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(5.dp, disabledElevation = 5.dp)
                                ) {
                                    Row{
                                        androidx.compose.material3.Text("다음 단계로", color = white)
                                        androidx.compose.material3.Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = white)
                                    }
                                }
                            }

                            if(showAlert){
                                AlertDialog(
                                    onDismissRequest = { showAlert = false },

                                    confirmButton = {
                                        TextButton(onClick = {
                                            showAlert = false
                                        }){
                                            androidx.compose.material3.Text("확인", color = accent, fontWeight = FontWeight.Bold)
                                        }
                                    },
                                    title = {
                                        androidx.compose.material3.Text(getAlertTitle(model=alertModel!!))
                                    },
                                    text = {
                                        androidx.compose.material3.Text(getAlertContents(model=alertModel!!))
                                    },
                                    icon = {
                                        androidx.compose.material3.Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                                    }
                                )
                            }

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
                        }
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpView_previews(){
    SignUpView()
}