package com.example.materialpass

import android.annotation.SuppressLint
import android.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

//region Search bar TODO work on this to allow text input and update | fix padding

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Search(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    var activeSearch by remember { mutableStateOf(false) }
    var dropDownSort by remember { mutableStateOf(false) }

    SearchBar(modifier = Modifier
        .padding(horizontal = 5.dp, vertical = 10.dp)
        .fillMaxWidth(),

        query = searchText, onQueryChange = { searchText = it }, onSearch = {
            activeSearch = false
        }, active = activeSearch, content = { /* TODO */ }, onActiveChange = {
            activeSearch = it
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)

        }, trailingIcon = {
            if (activeSearch) {
                Icon(modifier = Modifier.clickable {
                    if (searchText.isNotEmpty()) {
                        searchText = ""
                    } else {
                        activeSearch = false
                    }
                }, imageVector = Icons.Default.Close, contentDescription = null)
            } else {
                IconButton(onClick = { dropDownSort = true }, content = {
                    Icon(Icons.Default.Sort, contentDescription = null)
                })
            }
            // TODO val dropDownList = listOf<String>("Ascending", "Descending", "Recent")


            DropdownMenu(expanded = dropDownSort, onDismissRequest = { dropDownSort = false }) {
                DropdownMenuItem(text = { Text("Ascending (A-Z)") },
                    onClick = { dropDownSort = false })
                DropdownMenuItem(text = { Text(text = "Descending (Z-A)") },
                    onClick = { /* Handle sorting */
                        dropDownSort = false
                    })
                DropdownMenuItem(text = { Text(text = "Recent") }, onClick = { /* Handle sorting */
                    dropDownSort = false
                })
            }
        }, placeholder = { Text(text = "Search accounts") })
}

//endregion

@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        listOf(NavigationBarItem(selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = null) },
            label = { Text("Favourites") }), NavigationBarItem(selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") }), NavigationBarItem(selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
            label = { Text("Settings") }))
    }
}




