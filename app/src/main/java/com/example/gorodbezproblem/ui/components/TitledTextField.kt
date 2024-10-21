package com.example.gorodbezproblem.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun TitledTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    textModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = textModifier
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            shape = RoundedCornerShape(UIConstants.Round),
            modifier = textFieldModifier.fillMaxWidth()
        )
    }
}