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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
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
import com.cj.dysarthriachecker.ui.theme.white
import com.cj.dysarthriachecker.userManagement.helper.UserManagement
import com.cj.dysarthriachecker.userManagement.models.DisabledSeverityOption
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseCheckView(entryPoint: String){
    var strokeDisabledSelection by remember {
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var degenerativeBrainDiseaseSelection by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var peripheralNeuropathySelection by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var otherBrainDisease by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var functionalLanguageSelection by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var larynxSelection by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var oralSelection by remember{
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var otherLanguageDisease by remember {
        mutableStateOf<DisabledSeverityOption?>(null)
    }

    var showDatePicker by remember{
        mutableStateOf(false)
    }

    var birthDay by remember{
        mutableStateOf("")
    }

    var showErrorAlert by remember{
        mutableStateOf(false)
    }

    var showProgress by remember{
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    val helper = UserManagement()
    val navController = rememberNavController()

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "DiseaseCheckView"){
            composable(route="SignUpSuccessView"){
                SignUpSuccessView()
            }

            composable(route="DiseaseCheckView"){
                LaunchedEffect(key1 = null){
                    val formatter = SimpleDateFormat("yyyy. MM. dd")
                    birthDay = formatter.format(Date())
                }

                Surface(modifier = Modifier.fillMaxSize(),
                    color = DysarthriaCheckerColorPalette.current.background) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "질병 정보를 입력해주세요.", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(20.dp))

                        Surface(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                            .shadow(5.dp),
                            color= DysarthriaCheckerColorPalette.current.btnColor,
                            shape = RoundedCornerShape(size = 30f),
                            content = {
                                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, tint = DysarthriaCheckerColorPalette.current.txtColor)
                                    Spacer(modifier = Modifier.width(5.dp))
                                    androidx.compose.material3.Text(text = "생년월일", color = DysarthriaCheckerColorPalette.current.txtColor)

                                    Spacer(modifier = Modifier.weight(1f))

                                    TextButton(onClick = { showDatePicker = true }) {
                                        androidx.compose.material3.Text(text = birthDay, color = accent)
                                    }
                                }
                            })

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "귀하께서는 아래 뇌질환 중 하나 이상을 진단받은 적이 있습니까?", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "뇌졸중", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = strokeDisabledSelection == DisabledSeverityOption.NONE, onClick = {
                                strokeDisabledSelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = strokeDisabledSelection == DisabledSeverityOption.WEAK, onClick = {
                                strokeDisabledSelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = strokeDisabledSelection == DisabledSeverityOption.MEDIUM, onClick = {
                                strokeDisabledSelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = strokeDisabledSelection == DisabledSeverityOption.HARD, onClick = {
                                strokeDisabledSelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "퇴행성 뇌질환", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = degenerativeBrainDiseaseSelection == DisabledSeverityOption.NONE, onClick = {
                                degenerativeBrainDiseaseSelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = degenerativeBrainDiseaseSelection == DisabledSeverityOption.WEAK, onClick = {
                                degenerativeBrainDiseaseSelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = degenerativeBrainDiseaseSelection == DisabledSeverityOption.MEDIUM, onClick = {
                                degenerativeBrainDiseaseSelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = degenerativeBrainDiseaseSelection == DisabledSeverityOption.HARD, onClick = {
                                degenerativeBrainDiseaseSelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "말초성 뇌신경장애", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = peripheralNeuropathySelection == DisabledSeverityOption.NONE, onClick = {
                                peripheralNeuropathySelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = peripheralNeuropathySelection == DisabledSeverityOption.WEAK, onClick = {
                                peripheralNeuropathySelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = peripheralNeuropathySelection == DisabledSeverityOption.MEDIUM, onClick = {
                                peripheralNeuropathySelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = peripheralNeuropathySelection == DisabledSeverityOption.HARD, onClick = {
                                peripheralNeuropathySelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "기타 뇌질환", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherBrainDisease == DisabledSeverityOption.NONE, onClick = {
                                otherBrainDisease = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherBrainDisease == DisabledSeverityOption.WEAK, onClick = {
                                otherBrainDisease = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherBrainDisease == DisabledSeverityOption.MEDIUM, onClick = {
                                otherBrainDisease = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherBrainDisease == DisabledSeverityOption.HARD, onClick = {
                                otherBrainDisease = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "귀하께서는 아래 언어.청각 장애 중 하나 이상을 진단받은 적이 있습니까?", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "기능성 언어.청각 장애", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = functionalLanguageSelection == DisabledSeverityOption.NONE, onClick = {
                                functionalLanguageSelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = functionalLanguageSelection == DisabledSeverityOption.WEAK, onClick = {
                                functionalLanguageSelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = functionalLanguageSelection == DisabledSeverityOption.MEDIUM, onClick = {
                                functionalLanguageSelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = functionalLanguageSelection == DisabledSeverityOption.HARD, onClick = {
                                functionalLanguageSelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "후두 장애", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = larynxSelection == DisabledSeverityOption.NONE, onClick = {
                                larynxSelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = larynxSelection == DisabledSeverityOption.WEAK, onClick = {
                                larynxSelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = larynxSelection == DisabledSeverityOption.MEDIUM, onClick = {
                                larynxSelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = larynxSelection == DisabledSeverityOption.HARD, onClick = {
                                larynxSelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "구강 장애", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = oralSelection == DisabledSeverityOption.NONE, onClick = {
                                oralSelection = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = oralSelection == DisabledSeverityOption.WEAK, onClick = {
                                oralSelection = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = oralSelection == DisabledSeverityOption.MEDIUM, onClick = {
                                oralSelection = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = oralSelection == DisabledSeverityOption.HARD, onClick = {
                                oralSelection = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "기타 언어.청각 장애", color = DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherLanguageDisease == DisabledSeverityOption.NONE, onClick = {
                                otherLanguageDisease = DisabledSeverityOption.NONE
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "없음", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherLanguageDisease == DisabledSeverityOption.WEAK, onClick = {
                                otherLanguageDisease = DisabledSeverityOption.WEAK
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "경도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherLanguageDisease == DisabledSeverityOption.MEDIUM, onClick = {
                                otherLanguageDisease = DisabledSeverityOption.MEDIUM
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "중등", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))

                            RadioButton(selected = otherLanguageDisease == DisabledSeverityOption.HARD, onClick = {
                                otherLanguageDisease = DisabledSeverityOption.HARD
                            }, colors = RadioButtonDefaults.colors(
                                selectedColor = accent, unselectedColor = accent
                            ))

                            Text(text = "고도", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        AnimatedVisibility(visible = strokeDisabledSelection != null && degenerativeBrainDiseaseSelection != null && peripheralNeuropathySelection != null && otherBrainDisease != null && functionalLanguageSelection != null && larynxSelection != null && oralSelection != null && otherLanguageDisease != null) {
                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                showProgress = true

                                helper.updateDiseaseData(
                                    strokeDisabledSelection?.getCode() ?: 0,
                                    degenerativeBrainDiseaseSelection?.getCode() ?: 0,
                                    peripheralNeuropathySelection?.getCode() ?: 0,
                                    otherBrainDisease?.getCode() ?: 0,
                                    functionalLanguageSelection?.getCode() ?: 0,
                                    larynxSelection?.getCode() ?: 0,
                                    oralSelection?.getCode() ?: 0,
                                    otherLanguageDisease?.getCode() ?: 0,
                                    birthDay
                                ){
                                    if(it){
                                        showProgress = false

                                        if(entryPoint == "SignUpView"){
                                            navController.navigate("SignUpSuccessView"){
                                                popUpTo("DiseaseCheckView"){
                                                    inclusive = false
                                                }
                                            }
                                        }
                                    } else{
                                        showProgress = false
                                        showErrorAlert = true
                                    }
                                }
                            },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                enabled = strokeDisabledSelection != null && degenerativeBrainDiseaseSelection != null && peripheralNeuropathySelection != null && otherBrainDisease != null && functionalLanguageSelection != null && larynxSelection != null && oralSelection != null && otherLanguageDisease != null,
                                contentPadding = PaddingValues(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = accent, disabledContainerColor = gray
                                ),
                                elevation = ButtonDefaults.buttonElevation(5.dp, disabledElevation = 5.dp)
                            ) {
                                Row{
                                    androidx.compose.material3.Text("다음 단계로", color = white)
                                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = white)
                                }
                            }
                        }
                    }

                    if(showDatePicker){
                        DatePickerDialog(
                            onDismissRequest = {
                                showDatePicker = false
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        val formatter = SimpleDateFormat("yyyy. MM. dd")
                                        val calendar = Calendar.getInstance()
                                        calendar.timeInMillis = datePickerState.selectedDateMillis!!
                                        birthDay = formatter.format(calendar.time)
                                        showDatePicker = false
                                    }
                                ){
                                    androidx.compose.material3.Text("확인", color = accent)
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        showDatePicker = false
                                    }
                                ){
                                    androidx.compose.material3.Text("취소", color = accent)
                                }
                            },

                            tonalElevation = 5.dp) {
                            DatePicker(state = datePickerState, colors = DatePickerDefaults.colors(
                                containerColor = DysarthriaCheckerColorPalette.current.background,
                                titleContentColor = DysarthriaCheckerColorPalette.current.txtColor,
                                weekdayContentColor = DysarthriaCheckerColorPalette.current.txtColor,
                                todayContentColor = accent,
                                selectedDayContentColor = white,
                                selectedYearContentColor = white,
                                todayDateBorderColor = accent,
                                selectedYearContainerColor = accent,
                                selectedDayContainerColor = accent
                            ))
                        }
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

                    if(showErrorAlert){
                        AlertDialog(
                            onDismissRequest = { showErrorAlert = false },

                            confirmButton = {
                                TextButton(onClick = {
                                    showErrorAlert = false
                                }){
                                    androidx.compose.material3.Text("확인", color = accent, fontWeight = FontWeight.Bold)
                                }
                            },
                            title = {
                                androidx.compose.material3.Text("오류")
                            },
                            text = {
                                androidx.compose.material3.Text("요청하신 작업을 처리하는 중 문제가 발생했습니다.\n정상 로그인 여부, 네트워크 상태를 확인하거나 나중에 다시 시도해주세요.")
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

@Preview(showBackground = true)
@Composable
fun DiseaseCheckView_previews(){
    DiseaseCheckView(entryPoint = "SignUpView")
}