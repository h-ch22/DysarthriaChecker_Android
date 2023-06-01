package com.cj.dysarthriachecker.userManagement.ui

import android.content.Intent
import android.os.CountDownTimer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.dysarthriachecker.R
import com.cj.dysarthriachecker.frameworks.ui.MainActivity
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.ui.theme.gray
import com.cj.dysarthriachecker.ui.theme.green
import com.cj.dysarthriachecker.ui.theme.white

@Composable
fun SignUpSuccessView(){
    val context = LocalContext.current
    val finish : Long = 5 * 1000
    val timeData = remember {
        mutableStateOf(finish)
    }

    val countDownTimer = object : CountDownTimer(finish, 1000){
        override fun onTick(millisUntilFinished: Long) {
            timeData.value = millisUntilFinished
        }

        override fun onFinish() {
            context.startActivity(Intent(context, MainActivity :: class.java))

        }

    }

    DysarthriaCheckerTheme {
        LaunchedEffect(key1 = true){
            countDownTimer.start()
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DysarthriaCheckerColorPalette.current.background
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.weight(1f))

                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = green, modifier = Modifier.size(150.dp))

                Text(text = "가입이 완료되었어요!", fontWeight = FontWeight.Bold, color = DysarthriaCheckerColorPalette.current.txtColor, fontSize = 24.sp)

                Spacer(modifier = Modifier.height(10.dp))

                Text("${timeData.value / 1000}초 후에 메인 페이지로 이동해요!", color = gray, fontSize = 12.sp)

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    context.startActivity(Intent(context, MainActivity :: class.java))
                },
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accent, disabledContainerColor = gray
                    ),
                    elevation = ButtonDefaults.buttonElevation(5.dp, disabledElevation = 5.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                        Text(text = "시작하기", color = white)
                        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = white)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpSuccessView_previews(){
    SignUpSuccessView()
}