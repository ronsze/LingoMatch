package kr.sdbk.sign.onboarding

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.sdbk.common.ui.composable.BaseText
import kr.sdbk.common.ui.composable.BasicButton
import kr.sdbk.common.ui.theme.SkyBlue
import kr.sdbk.sign.R

@Composable
fun OnboardingView(
    navigateToSignUp: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        BasicButton(text = stringResource(id = R.string.sign_up)) {
            
        }
        Spacer(modifier = Modifier.height(20.dp))

        BasicButton(text = stringResource(id = R.string.sign_in)) {
            
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    OnboardingView({}, {})
}