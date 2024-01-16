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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilledCard(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    username: String,
    email: String,
    password: String
) {
    LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = title, style = MaterialTheme.typography.headlineLarge
            )
            IconButton(
                onClick = {/*TODO open up edit fields for the user on a new page*/ },
                content = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        Modifier.size(30.dp)
                    )
                },
            )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = url, style = MaterialTheme.typography.labelLarge
            )
            @Composable
            fun ConditionalText(label: String, value: String) {
                if (value.isNotEmpty()) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(Modifier) {
                            Text(
                                text = label, style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = value, style = MaterialTheme.typography.bodyLarge
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
        }
        Spacer(Modifier.padding(bottom = 8.dp)) // Always keep this spacer
    }
}


@Preview
@Composable

fun DisplayCard(modifier: Modifier = Modifier) {
    addSiteData()
    LazyColumn(Modifier.padding(), contentPadding = PaddingValues()) {
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