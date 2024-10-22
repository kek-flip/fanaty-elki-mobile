package com.example.gorodbezproblem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun Address(modifier: Modifier = Modifier, location:String, onClick: () -> Unit = {}) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ) {
        Text(text = "Место", fontSize = UIConstants.FontSize)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Location",
                tint = Color.Black
            )
            Text(text = location, fontSize = UIConstants.FontSize)
        }
    }
}