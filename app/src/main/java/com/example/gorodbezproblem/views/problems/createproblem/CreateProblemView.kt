package com.example.gorodbezproblem.views.problems.createproblem

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.gorodbezproblem.ui.components.Address
import com.example.gorodbezproblem.ui.components.TitledTextField
import com.example.gorodbezproblem.ui.theme.Colors
import com.example.gorodbezproblem.ui.theme.UIConstants

@Composable
fun CreateProblemView(
    navController: NavHostController,
    viewModel: CreateProblemViewModel = viewModel()
) {
    // Добавляем переменные состояния для хранения текста
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.isCreated) {
        if (viewModel.isCreated) {
            navController.popBackStack()
            // Нужно добавить показ сообщения об успехе
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIConstants.SidesPadding)
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

            // Поле "Описание"
            TitledTextField(
                title = "Описание",
                value = title,
                onValueChange = { newTitle ->
                    title = newTitle
                    viewModel.onProblemTitleChange(newTitle)
                },
                placeholder = "Введите краткое название проблемы",
                modifier = Modifier.fillMaxWidth(),
            )

            // Поле "Расскажите подробнее"
            TitledTextField(
                title = "Расскажите подробнее",
                value = description,
                onValueChange = { newDescription ->
                    description = newDescription
                    viewModel.onProblemDescriptionChange(newDescription)
                },
                placeholder = "Введите подробности проблемы",
                modifier = Modifier.fillMaxWidth(),
                textFieldModifier = Modifier.height(120.dp)
            )

            // Блок "Фото"
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let { viewModel.addPhotoUri(it) }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { launcher.launch("image/*") }
                ) {
                    Text(text = "Фото", fontSize = UIConstants.FontSize)
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Добавить фото",
                        tint = Colors.Black
                    )
                }
                // Показать текст "Добавьте фото" только если список фото пуст
                if (viewModel.photoUris.isEmpty()) {
                    Text(
                        text = "Добавьте фото",
                        fontSize = UIConstants.FontSize,
                        color = Colors.HalfBlack
                    )
                }
            }

            // Показать изображения, если они есть
            if (viewModel.photoUris.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.photoUris.forEach { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(model = uri),
                            contentDescription = "Фото",
                            modifier = Modifier.size(80.dp).padding(2.dp)
                        )
                    }
                }
            }

            // Блок "Место" с переходом на экран места
            Address(location = "Ваш адрес", onClick = {
                navController.navigate("location_screen")
            })
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Отправить"
        Button(
            onClick = {
                viewModel.onSubmitClick()
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
