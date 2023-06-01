package com.cj.dysarthriachecker.check.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.check.models.CheckTypeModel
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckTypeSelectionView(){
    val navController = rememberNavController()
    var selectedType by remember {
        mutableStateOf<CheckTypeModel?>(null)
    }

    DysarthriaCheckerTheme {
        NavHost(navController = navController, startDestination = "CheckTypeSelectionView") {
            composable(route = "EmptyView") {
            }

            composable(route = "CheckTypeSelectionView") {
                Scaffold(topBar = {
                    TopAppBar (
                        title = {
                            Text(text = "검사 타입 선택", color = DysarthriaCheckerColorPalette.current.txtColor)
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
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top) {
                            Text(text = "아래 옵션 중 하나를 선택해 검사를 진행하세요.", color = DysarthriaCheckerColorPalette.current.txtColor)

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                selectedType = CheckTypeModel.WORD
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(selectedType == CheckTypeModel.WORD) accent else DysarthriaCheckerColorPalette.current.btnColor
                                ), elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                    Image(imageVector = Icons.Default.TextFormat,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                        if(selectedType == CheckTypeModel.WORD) white else DysarthriaCheckerColorPalette.current.txtColor
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(text = "단어", fontSize = 18.sp, color = if(selectedType == CheckTypeModel.WORD) white else DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                                        Text(text = "짧은 단어로 검사를 진행합니다.\n장애의 정도가 매우 심하거나 소통이 매우 힘든 경우 이 옵션을 선택하십시오.", fontSize = 12.sp, color = if(selectedType == CheckTypeModel.WORD) white else DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                selectedType = CheckTypeModel.SENTENCE
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(selectedType == CheckTypeModel.SENTENCE) accent else DysarthriaCheckerColorPalette.current.btnColor
                                ), elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                    Image(imageVector = Icons.Default.TextSnippet,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            if(selectedType == CheckTypeModel.SENTENCE) white else DysarthriaCheckerColorPalette.current.txtColor
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(text = "문장", fontSize = 18.sp, color = if(selectedType == CheckTypeModel.SENTENCE) white else DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                                        Text(text = "문장을 읽어 검사를 진행합니다.\n일반적인 대화가 가능한 경우 이 옵션을 선택하십시오.", fontSize = 12.sp, color = if(selectedType == CheckTypeModel.SENTENCE) white else DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                selectedType = CheckTypeModel.PARAGRAPH
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(selectedType == CheckTypeModel.PARAGRAPH) accent else DysarthriaCheckerColorPalette.current.btnColor
                                ), elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                    Image(imageVector = Icons.Default.Book,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            if(selectedType == CheckTypeModel.PARAGRAPH) white else DysarthriaCheckerColorPalette.current.txtColor
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(text = "문단", fontSize = 18.sp, color = if(selectedType == CheckTypeModel.PARAGRAPH) white else DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                                        Text(text = "문단 단위로 검사를 진행합니다.\n일상생활에서 소통에 큰 불편함이 없는 경우 이 옵션을 선택하십시오.", fontSize = 12.sp, color = if(selectedType == CheckTypeModel.PARAGRAPH) white else DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                selectedType = CheckTypeModel.SEMI_FREE_SPEECH
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(selectedType == CheckTypeModel.SEMI_FREE_SPEECH) accent else DysarthriaCheckerColorPalette.current.btnColor
                                ), elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                    Image(imageVector = Icons.Default.ChatBubble,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            if(selectedType == CheckTypeModel.SEMI_FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(text = "준자유발화", fontSize = 18.sp, color = if(selectedType == CheckTypeModel.SEMI_FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                                        Text(text = "자유발화에 준하는 문장으로 검사를 진행합니다.\n의사소통이 매우 원활한 경우 이 옵션을 선택하십시오.", fontSize = 12.sp, color = if(selectedType == CheckTypeModel.SEMI_FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(onClick = {
                                selectedType = CheckTypeModel.FREE_SPEECH
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(selectedType == CheckTypeModel.FREE_SPEECH) accent else DysarthriaCheckerColorPalette.current.btnColor
                                ), elevation = ButtonDefaults.buttonElevation(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                    Image(imageVector = Icons.Default.Message,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            if(selectedType == CheckTypeModel.FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(text = "자유발화", fontSize = 18.sp, color = if(selectedType == CheckTypeModel.FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor, fontWeight = FontWeight.SemiBold)
                                        Text(text = "정해진 문장 없이 자유로운 대화를 통해 검사를 진행합니다.\n의사소통이 매우 원활하거나, 화면에 표시되는 텍스트를 읽기 어려운 경우 이 옵션을 선택하십시오.", fontSize = 12.sp, color = if(selectedType == CheckTypeModel.FREE_SPEECH) white else DysarthriaCheckerColorPalette.current.txtColor)
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            if(selectedType != null){
                                Spacer(modifier = Modifier.height(20.dp))

                                Button(onClick = {

                                },
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    enabled = selectedType != null,
                                    contentPadding = PaddingValues(15.dp),
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
                        }
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun CheckTypeSelectionView_previews(){
    CheckTypeSelectionView()
}