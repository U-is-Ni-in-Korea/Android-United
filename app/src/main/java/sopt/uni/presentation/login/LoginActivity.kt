package sopt.uni.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.data.service.KakaoLoginService
import sopt.uni.databinding.ActivityLoginBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showToast
import sopt.uni.util.extension.startActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var kakaoLoginService: KakaoLoginService
    private val loginViewModel by viewModels<LoginViewModel>()

    //    private lateinit var auth: FirebaseAuth
//    private lateinit var mGoogleSignInClient: GoogleSignInClient
//    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        auth = FirebaseAuth.getInstance()
//        googleInit()

        moveToKakaoLogin()
//        moveToGoogleLogin()
        collectIsTokenAvailability()
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.exit_text),
                Toast.LENGTH_SHORT,
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun collectIsTokenAvailability() {
        lifecycleScope.launch {
            kakaoLoginService.loginState
                .collect {
                    Log.e("kakaologinservice.loginstate", "collectIsTokenAvailability: $it")
                    when (it) {
                        is KakaoLoginService.LoginState.Success -> {
                            loginViewModel.getAccessToken("kakao", it.token).join()

                            when (loginViewModel.loginResult.value) {
                                is LoginViewModel.SparkleLoginState.Success -> {
                                    this@LoginActivity.showToast(getString(R.string.success_kakao_login))
                                    startActivity<NickNameActivity>()
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    return@collect
                                }

                                is LoginViewModel.SparkleLoginState.AlreadyCoupled -> {
                                    this@LoginActivity.showToast(getString(R.string.success_kakao_login))
                                    startActivity<HomeActivity>()
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    return@collect
                                }

                                is LoginViewModel.SparkleLoginState.Failure -> {
                                    Timber.e("Login Failed")
                                    return@collect
                                }

                                is LoginViewModel.SparkleLoginState.Init -> {
                                    Timber.d("Login Init")
                                    return@collect
                                }
                            }
                        }

                        is KakaoLoginService.LoginState.Failure -> {
                            Timber.d("Kakao Login Failed ${it.error}")
                        }

                        else -> {
                            Timber.d("Kakao INIT")
                        }
                    }
                }
        }
    }

    private fun moveToKakaoLogin() {
        binding.llKakaoLogin.setOnSingleClickListener {
            if (kakaoLoginService.isKakaoTalkLoginAvailable) {
                kakaoLoginService.loginByKakaoTalk()
            } else {
                kakaoLoginService.loginByKakaoAccount()
            }
        }
    }

    // TODO 함수 분리
//    private fun googleInit() {
//        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(GOOGLE_CLIENT_ID)
//            .requestEmail()
//            .requestId()
//            .requestProfile()
//            .build()
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
//
//        startGoogleLoginForResult =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//                if (result.resultCode == RESULT_OK) {
//                    result.data?.let { data ->
//
//                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//
//                        try {
//                            val account = task.getResult(ApiException::class.java)!!
//                            Timber.e("firebaseAuthWithGoogle:" + account.idToken)
//                            loginViewModel.getAccessToken("google", account.idToken!!)
//                            firebaseAuthWithGoogle(account.idToken!!)
//                        } catch (e: ApiException) {
//                            Timber.e("Google sign in failed", e)
//                        }
//                    }
//                } else {
//                    Timber.e("Google Result Error $result")
//                }
//            }
//    }

//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Timber.e("signInWithCredential:success")
//                    when (loginViewModel.loginResult.value) {
//                        is LoginViewModel.SparkleLoginState.Success -> {
//                            this@LoginActivity.showToast(getString(R.string.success_google_login))
//                            startActivity<NickNameActivity>()
//                        }
//
//                        is LoginViewModel.SparkleLoginState.AlreadyCoupled -> {
//                            this@LoginActivity.showToast(getString(R.string.success_google_login))
//                            startActivity<HomeActivity>()
//                        }
//
//                        is LoginViewModel.SparkleLoginState.Failure -> {
//                            Timber.e("Login Failed")
//                        }
//
//                        is LoginViewModel.SparkleLoginState.Init -> {
//                            Timber.d("Login Init")
//                        }
//                    }
//                } else {
//                    Timber.e("signInWithCredential:failure", task.exception)
//                }
//            }
//    }
//
//    private fun moveToGoogleLogin() {
//        binding.llGoogleLogin.setOnSingleClickListener {
//            val signInIntent = mGoogleSignInClient.signInIntent
//            startGoogleLoginForResult.launch(signInIntent)
//        }
//    }
}
