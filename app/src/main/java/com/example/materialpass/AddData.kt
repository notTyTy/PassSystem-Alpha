package com.example.materialpass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    val username: String?, val email: String?, val password: String?
)


var siteData = mutableListOf<SiteInformation>()
fun addSiteData() {
    siteData.add(
        SiteInformation(
            title = "Google Account", url = "https://google.com", credentials = Credentials(
                username = "pikaprisma",
                email = "google@tyty.cloud",
                password = "googleStopTrackingMe"
            )
        )
    )
    siteData.add(
        SiteInformation(
            title = "Revolt", url = "https://revolt.chat", credentials = Credentials(
                username = "", email = "revolt@tyty.cloud", password = "thisIsAp@ssw0rd"
            )
        )
    )
}
//endregion


//region Material Card
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
            .padding(horizontal = 10.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = title,
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
                        modifier.size(30.dp)
                    )
                },
            )
        }
        val paddingTop = 8.dp
        val paddingStart = 16.dp

        Text(
            text = url,
            modifier.padding(start = paddingStart),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier.padding(vertical = 2.dp))
        @Composable
        fun ConditionalText(label: String, value: String) {
            if (value.isNotEmpty()) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier) {
                        Text(
                            text = label,
                            modifier = Modifier.padding(start = paddingStart),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.labelMedium
                        )

                        Text(
                            text = value,
                            modifier = Modifier.padding(start = paddingStart),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    IconButton(onClick = {/*TODO*/ }, content = {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = null,
                            modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    })
                }
            }
        }
        ConditionalText(label = "Username", value = username)
        ConditionalText(label = "Email", value = email)
        ConditionalText(label = "Password", value = password)

        Spacer(modifier.padding(top = paddingTop)) // Always keep this spacer

    }
    Spacer(modifier.padding(bottom = 8.dp)) // Always keep this spacer

}

@Preview
@Composable

fun DisplayCard(modifier: Modifier = Modifier) {
    addSiteData()
    LazyColumn(modifier.padding(), contentPadding = PaddingValues()) {
        addSiteData()
        items(siteData) { data ->
            FilledCard(
                title = data.title,
                url = data.url,
                username = data.credentials.username.toString(),
                email = data.credentials.email.toString(),
                password = data.credentials.password.toString()
            )
        }
    }
}

