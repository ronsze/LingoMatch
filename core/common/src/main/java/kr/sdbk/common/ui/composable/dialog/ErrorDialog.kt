package kr.sdbk.common.ui.composable.dialog

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import kr.sdbk.common.R
import kr.sdbk.common.ui.composable.BaseText

@Composable
fun ErrorDialog(
    text: String,
    onClickRetry: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = {
            BaseText(text = text)
        },
        confirmButton = {
            BaseText(
                text = stringResource(id = R.string.retry),
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable {
                        onDismissRequest()
                        onClickRetry()
                    }
            )
        },
        dismissButton = {
            BaseText(
                text = stringResource(id = R.string.confirm),
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable { onDismissRequest() }
            )
        }
    )
}