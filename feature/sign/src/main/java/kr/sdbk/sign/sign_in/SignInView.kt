package kr.sdbk.sign.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.ui.composable.BaseText
import kr.sdbk.common.ui.composable.BasicButton
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.sign.R
import kr.sdbk.sign.composable.SignErrorBox

@Composable
fun SignInView(
    navigateToMain: (User) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        var email: String by remember { mutableStateOf("") }
        var password: String by remember { mutableStateOf("") }

        if (uiState is SignInViewModel.SignInUiState.Failed) {
            SignErrorBox(error = uiState.error)
        }

        BaseText(
            text = stringResource(id = R.string.sign_in),
            fontSize = 21.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            label = { BaseText(text = stringResource(id = R.string.email))},
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            label = { BaseText(text = stringResource(id = R.string.password)) },
            value = password,
            onValueChange = { password = it },
            visualTransformation = { TransformedText(AnnotatedString("●".repeat(password.length)), OffsetMapping.Identity) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))

        BasicButton(text = stringResource(id = R.string.sign_up)) {
            viewModel.signIn(email, password)
        }
        Spacer(modifier = Modifier.height(20.dp))

        LaunchedEffect(key1 = uiState) {
            if (uiState is SignInViewModel.SignInUiState.Signed) {
                navigateToMain(uiState.user)
            }
        }
    }
}