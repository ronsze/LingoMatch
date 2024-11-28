package kr.sdbk.common.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.sdbk.common.ui.theme.SkyBlue

@Composable
fun BasicButton(
    text: String,
    fontSize: TextUnit = 18.sp,
    fontColor: Color = Color.White,
    backgroundColor: Color = SkyBlue,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        BaseText(
            text = text,
            fontSize = fontSize,
            color = fontColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    BasicButton(text = "Preview") {

    }
}
