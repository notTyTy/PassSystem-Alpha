package com.example.materialpass

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AddPage(modifier: Modifier = Modifier) // ToDo this is for adding new information to the main page
{
    Column(modifier) {

    }
}

@Preview
@Composable
fun BottomBarAdd(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), Arrangement.SpaceBetween, Alignment.CenterVertically
        ) {
            Text(text = "Cancel", modifier.padding(horizontal = 30.dp))
            Text(text = "Save", modifier.padding(horizontal = 30.dp))
        }
    }
}
@Composable
fun AddPanel(modifier: Modifier = Modifier)
{

}