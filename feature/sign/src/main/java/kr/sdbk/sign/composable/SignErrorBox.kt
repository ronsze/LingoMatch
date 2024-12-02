package kr.sdbk.sign.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.sdbk.common.exceptions.auth.InvalidEmailOrPasswordException
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.common.exceptions.auth.EmailExistsException
import kr.sdbk.common.exceptions.auth.InvalidEmailFormatException
import kr.sdbk.common.exceptions.auth.WeakPasswordException
import kr.sdbk.common.ui.theme.Pink
import kr.sdbk.sign.R

@Composable
fun SignErrorBox(
    error: Exception
) {
    val msg = when (error) {
        is InvalidEmailOrPasswordException -> R.string.invalid_email_or_password
        is TooManyRequestsException -> R.string.too_many_requests
        is WeakPasswordException -> R.string.weak_password
        is InvalidEmailFormatException -> R.string.invalid_email_format
        is EmailExistsException -> R.string.email_exists
        else -> kr.sdbk.common.R.string.unknown_error
    }
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(Pink.copy(0.7f), RoundedCornerShape(5.dp))
    ) {
        Text(
            text = stringResource(id = msg),
            color = Color.Red.copy(0.8f),
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SignErrorBox(TooManyRequestsException())
}