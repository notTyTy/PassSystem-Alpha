package com.example.materialpass

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Padding
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        val openAlertDialog = remember { mutableStateOf(false) }

        CardHeader(title = title, expanded = expanded, onExpandToggle = { expanded = !expanded })
        CardContent(url = url, username = username, email = email, password = password)
        Spacer(Modifier.padding(bottom = 8.dp)) // Always keep this spacer
    }
}

@Composable
fun CardHeader(title: String, expanded: Boolean, onExpandToggle: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        CardOptionsMenu(expanded = expanded, onExpandToggle = onExpandToggle)
    }
}


@Composable
fun CardOptionsMenu(expanded: Boolean, onExpandToggle: () -> Unit) {
    val openAlertDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = { onExpandToggle() },
            content = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    Modifier.size(30.dp)
                )
            },
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { onExpandToggle() }) {
            DropdownMenuItem(text = { Text("Edit") }, onClick = { onExpandToggle() })
            DropdownMenuItem(text = { Text(text = "Delete") }, onClick = { /* Handle Delete */
                onExpandToggle()
                openAlertDialog.value = true
            })
        }
    }
    val context = LocalContext.current
    AlertDeleteConfirmation(openAlertDialog = openAlertDialog, onDismissRequest = {
        openAlertDialog.value = false
    }, onConfirmation = {
        openAlertDialog.value = false
    })
}


@Composable
fun AlertDeleteConfirmation(
    openAlertDialog: MutableState<Boolean>, onDismissRequest: () -> Unit, onConfirmation: () -> Unit
) {
    when {
        openAlertDialog.value -> {
            AlertDialogTemplate(
                onDismissRequest = onDismissRequest,
                onConfirmation = onConfirmation,  // TODO add something to delete value from the list and update the display cards
                dialogTitle = "Delete Confirmation",
                dialogText = "Are you sure you want to delete this? This action cannot be undone",
                icon = Icons.Default.WarningAmber
            )
        }
    }
}
//region Completed CardContent
@Composable
fun CardContent(url: String, username: String, email: String, password: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = url, style = MaterialTheme.typography.labelLarge)
        ConditionalText(label = "Username", value = username)
        ConditionalText(label = "Email", value = email)
        ConditionalText(label = "Password", value = password)
    }
}

@Composable
fun ConditionalText(label: String, value: String) {
    if (value.isNotEmpty()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier) {
                Text(text = label, style = MaterialTheme.typography.labelMedium)
                Text(text = value, style = MaterialTheme.typography.bodyLarge)
            }
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
//endregion


// Display data
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
