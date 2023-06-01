package com.cj.dysarthriachecker.frameworks.ui

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.dysarthriachecker.R
import com.cj.dysarthriachecker.frameworks.helper.AES256Util
import com.cj.dysarthriachecker.frameworks.helper.DataStoreUtil
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerColorPalette
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme
import com.cj.dysarthriachecker.ui.theme.accent
import com.cj.dysarthriachecker.userManagement.helper.UserManagement
import com.cj.dysarthriachecker.userManagement.models.AuthInfoModel
import com.cj.dysarthriachecker.userManagement.ui.SignInView
import java.util.concurrent.Executor

@Composable
fun SplashView(){
    val helper = UserManagement()
    val context = LocalContext.current as FragmentActivity
    val dataStoreUtil = DataStoreUtil(context)
    val authInfo = dataStoreUtil.getFromDataStore().collectAsState(initial = AuthInfoModel(email = "", password = ""))
    val navController = rememberNavController()
    var biometricPrompt : BiometricPrompt? = null
    var executor: Executor? = null
    var promptInfo: BiometricPrompt.PromptInfo? = null

    var isSignedIn : Boolean? = null

    DysarthriaCheckerTheme {
        LaunchedEffect(key1 = null){
            if(authInfo.value.email != "" && authInfo.value.password != ""){
                val biometricManager = BiometricManager.from(context)

                executor = ContextCompat.getMainExecutor(context)

                biometricPrompt = BiometricPrompt(context, executor!!, object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Log.d("SplashView", errString.toString())
                        isSignedIn = false
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)

                        helper.signIn(
                            AES256Util.decrypt(authInfo.value.email ?: ""),
                            AES256Util.decrypt(authInfo.value.password ?: "")
                        ){
                            isSignedIn = it
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.d("SplashView", "AuthenticationFailed")
                        isSignedIn = false
                    }

                } )

                when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {

                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        val promptBuilder: PromptInfo.Builder = PromptInfo.Builder()
                        promptBuilder.setTitle("생체 인식")
                        promptBuilder.setSubtitle("로그인을 위해 생체인식을 진행해주세요")
                        promptBuilder.setNegativeButtonText("비밀번호 사용")

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            promptBuilder.setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                        }

                        promptInfo = promptBuilder.build()

                        promptInfo?.let{
                            biometricPrompt?.authenticate(it)
                        }
                    }

                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                        Log.d("SplashView", "NO_HARDWARE")
                        isSignedIn = false
                    }

                    BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                        Log.d("SplashView", "HW UNAVAILABLE")

                        isSignedIn = false
                    }

                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        Log.d("SplashView", "NONE ENROLLED")
                        isSignedIn = false
                    }

                    else -> isSignedIn = false
                }
            } else{
                Log.d("SplashView", "Auto Sign-In Disabled.")
                isSignedIn = false
            }
        }

        NavHost(navController = navController, startDestination = "SplashView"){
            composable(route = "SignInView"){
                SignInView()
            }

            composable(route = "SplashView"){
                Surface(modifier=Modifier.fillMaxSize(), color = DysarthriaCheckerColorPalette.current.background) {
                    Column(modifier=Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.ic_appstore),
                            contentDescription = null,
                            modifier = Modifier
                                .width(180.dp)
                                .height(180.dp)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    clip = true
                                )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row {
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

                        Spacer(modifier = Modifier.weight(1f))

                        CircularProgressIndicator(modifier = Modifier,
                            color = accent)

                        if(isSignedIn == true){
                            context.startActivity(Intent(context, MainActivity :: class.java))
                        } else if(isSignedIn == false){
                            navController.navigate("SignInView"){
                                popUpTo("SplashView"){
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}