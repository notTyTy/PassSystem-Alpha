package com.example.materialpass

import android.graphics.drawable.Drawable
import android.graphics.drawable.shapes.Shape
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.sharp.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview
@Composable
fun AddPage(modifier: Modifier = Modifier) // ToDo this is for adding new information to the main page
{
    Column(modifier.fillMaxWidth()) {
        TopBarAdd()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarAdd(modifier: Modifier = Modifier) {
    TopAppBar(title = { Text(text = "Add Login", modifier.padding(10.dp)) }, navigationIcon = {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier.padding(horizontal = 10.dp)
        )
    }, actions = {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = null,
            modifier.padding(horizontal = 10.dp)
        )
    })
}


@Preview
@Composable
fun FloatingAddButton(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { /*TODO*/ }, modifier.size(56.dp),
        content = {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null
            )
            FabPosition.End
        },
    )
    modifier.padding(16.dp)
}


// Data classes to store login information
//region class information
data class SiteInformation(
    val title: String = "", val url: String = "N/A", val credentials: Credentials
)

data class Credentials(
    var username: String = "", var email: String = "", var password: String = ""
)

var siteData = mutableListOf<SiteInformation>()

fun addSiteData() {
    siteData.add(
        SiteInformation(
            title = "Revolt", url = "https://revolt.chat", credentials = Credentials(
                username = "Ashy", email = "revolt@tyty.cloud", password = "thisIsAp@ssw0rd"
            )
        )
    )

}
//endregion

//region Material Card
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilledCard(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    username: String,
    email: String,
    password: String
) {


    val context = LocalContext.current
    ElevatedCard(
        modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            // TODO make sure text is modular
            Text(
                text = "$title",
                modifier.padding(start = 16.dp, top = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(
                onClick = {/*TODO open up edit fields for the user on a new page*/ },
                modifier.padding(top = 8.dp, end = 16.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier.size(24.dp)
                    )
                },
            )
        }

        val paddingTop = 8.dp
        val paddingStart = 16.dp

        //region TODO the text fields here must vary depending on the input. i.e can have password, username etc.
        // If String.isEmpty ensure that that field is not added to the design
        Text(
            text = "$url",
            modifier.padding(start = paddingStart),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Username:   " + "$username", modifier.padding(
                top = paddingTop, start = paddingStart
            ), // all padding and text align are the same
            textAlign = TextAlign.Start, style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Email:  " + "$email",
            modifier.padding(top = paddingTop, start = paddingStart),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Password:   " + "$password",
            modifier.padding(top = paddingTop, start = paddingStart),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
        )
        //endregion
        Spacer(modifier.padding(top = paddingTop)) // Always keep this spacer

        //region TODO the clipboard fields must vary depending on the input. i.e can have password username etc.
        Row(
            modifier
                .fillMaxWidth()
                .padding(), horizontalArrangement = Arrangement.Absolute.Left
        ) {
            ElevatedButton(
                onClick = {
                    Toast.makeText(
                        context,
                        "Username copied to clipboard",
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                modifier.padding(start = 16.dp),
                content = {
                    Icon(

                        painterResource(id = R.drawable.content_copy),
                        contentDescription = null,
                        modifier.size(20.dp),

                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier.padding(end = 4.dp))
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
            )
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier.padding(start = 8.dp),
                content = {
                    Icon(
                        painterResource(id = R.drawable.content_copy), contentDescription = null,
                        modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary,


                        )
                    Spacer(modifier.padding(horizontal = 4.dp))
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
            )
        }
        //endregion
        Spacer(modifier.padding(bottom = 8.dp)) // Always keep this spacer

    }
}

@Preview
@Composable
fun DisplayCard(modifier: Modifier = Modifier) {
    Column(modifier) {
        for (data in siteData) {
            FilledCard(
                title = data.title,
                url = data.url,
                username = data.credentials.username,
                email = data.credentials.email,
                password = data.credentials.password
            )
        }
    }
}
