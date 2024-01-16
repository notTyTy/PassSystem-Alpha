package com.example.materialpass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//region TODO Implement this on add page
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarAdd(modifier: Modifier = Modifier) {
    TopAppBar(title = { Text(text = "Add Login", Modifier.padding(10.dp)) }, navigationIcon = {
        IconButton(onClick = {/* TODO */ }, content = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
            )
        })
    }, actions = {
        IconButton(onClick = { /*TODO*/ }, content = {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
            )
        })
    })
}
//endregion

//region FAB TODO open add page
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
//endregion

//region class Site Data and credentials
// List of login data and domain/app name
var siteData = mutableListOf<SiteInformation>()

data class SiteInformation(
    val title: String = "", val url: String = "N/A", val credentials: Credentials
)

data class Credentials(
    val username: String?, val email: String?, val password: String?
)

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

//region Search bar TODO work on this to allow text input and update
@Preview
@Composable
fun SearchTopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.large),
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(value = "", onValueChange = {}, placeholder = { Text(text = "Search Logins") })
    }
}
//endregion