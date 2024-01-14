package com.example.materialpass

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Drawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.materialpass.ui.theme.PassSystemAlphaTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PassSystemAlphaTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen()
            }
        }
    }
}

//region Search Bar
@Preview
@Composable
fun SearchTopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.large),
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(value = "", onValueChange = {}, placeholder = { Text(text = "Search Logins") })
    }
}

//endregion
//region Display Card
@Composable
fun DisplayCard(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    urlFavicon: Int,
    siteName: String
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(10.dp, 5.dp)
            .clip(shape = MaterialTheme.shapes.large)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(25.dp, 20.dp)
        ) {
            DisplayCardHeader(painterResource = urlFavicon, text = siteName)
            DisplayCardInformation(loginType = "Username: ", userInformation = username)
            DisplayCardInformation(loginType = "Password: ", userInformation = password)
        }
    }
}

@Composable
fun DisplayCardInformation(
    modifier: Modifier = Modifier, loginType: String, userInformation: String
) {
    Row(modifier = modifier.padding(10.dp)) {
        Text(text = loginType) // Make this functional
        Text(text = userInformation)
    }
}

@Composable
fun DisplayCardHeader(modifier: Modifier = Modifier, painterResource: Int, text: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = painterResource),
            contentDescription = null,
            Modifier.size(50.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(10.dp)
        ) // Name of the platform
    }
}

//endregion

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { SearchTopBar(modifier.padding(start = 10.dp, end = 10.dp)) },
        bottomBar = { /* BottomBarMain() */},
        floatingActionButton = { FloatingAddButton() },
        floatingActionButtonPosition = FabPosition.End,

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
        }
    }
}