package sopt.uni.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.BuildConfig
import sopt.uni.R
import sopt.uni.data.service.KakaoLoginService
import sopt.uni.databinding.ActivityLoginBinding
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var kakaoLoginService: KakaoLoginService
    private val loginViewModel by viewModels<LoginViewModel>()

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val keyHash = Utility.getKeyHash(this)
        Timber.d(keyHash)

        auth = FirebaseAuth.getInstance()
        googleInit()

        moveToKakaoLogin()
        moveToGoogleLogin()
        collectIsTokenAvailability()
    }

    private fun collectIsTokenAvailability() {
        lifecycleScope.launch {
            kakaoLoginService.loginState
                .flowWithLifecycle(lifecycle)
                .collect {
                    when (it) {
                        is KakaoLoginService.LoginState.Success -> {
                            startActivity<NickNameActivity>()
                            Timber.e("Kakao Login Success ${it.token} ${it.id}")
                        }

                        is KakaoLoginService.LoginState.Failure -> Timber.d("Kakao Login Failed ${it.error}")
                        else -> Timber.d("Kakao INIT")
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
    private fun googleInit() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.let { data ->

                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

                        try {
                            val account = task.getResult(ApiException::class.java)!!
                            Timber.e("firebaseAuthWithGoogle:" + account.id)
                            firebaseAuthWithGoogle(account.idToken!!)
                        } catch (e: ApiException) {
                            Timber.e("Google sign in failed", e)
                        }
                    }
                } else {
                    Timber.e("Google Result Error $result")
                }
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.e("signInWithCredential:success")
                    startActivity<NickNameActivity>()
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.e("signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun moveToGoogleLogin() {
        binding.llGoogleLogin.setOnSingleClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startGoogleLoginForResult.launch(signInIntent)
        }
    }
}
