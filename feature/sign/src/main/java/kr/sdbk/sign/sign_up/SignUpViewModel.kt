package kr.sdbk.sign.sign_up

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.common.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : BaseViewModel() {
    fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ) {

    }
}