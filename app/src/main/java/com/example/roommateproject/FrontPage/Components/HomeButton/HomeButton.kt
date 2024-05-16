import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.Components.HomeButton.HomeButtonViewModel
import com.example.roommateproject.FrontPage.Components.HomeButton.HomeButtonViewModelFactory
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.white

@Composable
fun HomeToggleButton(viewModel: HomeButtonViewModel = viewModel(factory = HomeButtonViewModelFactory(AccountService()))) {
    val context = LocalContext.current
    val isHome by viewModel.isHome

    Box(
        modifier = Modifier
            .background(Color.White)
            .wrapContentWidth()
    ) {
        Button(
            onClick = { viewModel.toggleHomeStatus(context) },
            colors = ButtonDefaults.buttonColors(lightYellow)
        ) {
            Text(
                text = if (isHome) "I'm leaving" else "I'm home",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white
            )
        }
    }
}


