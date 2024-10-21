package com.example.gorodbezproblem.views.createproblem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gorodbezproblem.ui.components.Address
import com.example.gorodbezproblem.ui.components.TitledTextField
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun CreateProblemView(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIConstants.Padding)
    ) {
        // Кнопка назад
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = "Назад",
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(2.dp),
            tint = Colors.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            // Заголовок
            Text(
                text = "Сообщить о проблеме",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            TitledTextField(
                title = "Описание",
                value = "",
                onValueChange = {},
                placeholder = "Введите краткое название проблемы",
                modifier = Modifier.fillMaxWidth(),
            )

            // Поле "Расскажите подробнее"
            TitledTextField(
                title = "Расскажите подробнее",
                value = "",
                onValueChange = {},
                placeholder = "Введите подробности проблемы",
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier.height(120.dp)
            )

            // Блок "Фото"
            // TODO: Тут нужна логика похода в галеру, Гоша тут надо тащить
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(text = "Фото", fontSize = UIConstants.FontSize)
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Добавить фото",
                        tint = Colors.Black
                    )
                }
                Text(
                    text = "Добавьте фото",
                    fontSize = UIConstants.FontSize,
                    color = Colors.HalfBlack
                )
            }

            // Блок "Место" с переходом на экран места
            Address(location = "Ваш адрес", onClick = {
                navController.navigate("location_screen")
            })
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Отправить"
        Button(
            onClick = { /* Логика отправки */ },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen),
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(UIConstants.Round),
        ) {
            Text(
                text = "Отправить",
                fontSize = UIConstants.FontSize,
                fontWeight = FontWeight.Normal,
                color = Colors.Black
            )
        }
    }
}


