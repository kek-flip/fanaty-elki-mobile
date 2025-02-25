package com.example.gorodbezproblem.views.problems.createproblem.problem

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gorodbezproblem.R
import com.example.gorodbezproblem.ui.components.TitledTextField
import com.example.gorodbezproblem.ui.theme.Colors

@Composable
fun CreateProblemView(
    navController: NavHostController,
    title: String,
    description: String,
    location: String,
    viewModel: CreateProblemViewModel = viewModel()
) {
    // Логика выбора изображений
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            viewModel.onImagesSelected(uris)
        }
    )

    LaunchedEffect(title) {
        viewModel.onProblemTitleChange(title)
    }

    LaunchedEffect(description) {
        viewModel.onProblemDescriptionChange(description)
    }

    LaunchedEffect(location) {
        viewModel.onSpecificLocationChange(location)
    }

    LaunchedEffect(viewModel.isCreated) {
        if (viewModel.isCreated) {
            navController.popBackStack()
        }
    }

    val context = LocalContext.current // Получаем контекст

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Кнопка назад
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
        }

        // Заголовок
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            Text(
                text = "Сообщить о проблеме",
                fontSize = 24.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.ic_ai_icon),
                contentDescription = "Заполнить проблему с помощью ИИ",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate("ai_task")
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода названия проблемы
        TitledTextField(
            title = "Название",
            value = viewModel.problem.title, // Связываем с ViewModel
            onValueChange = { viewModel.onProblemTitleChange(it) },
            placeholder = "Введите название проблемы"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода описания проблемы
        TitledTextField(
            title = "Описание",
            value = viewModel.problem.description, // Связываем с ViewModel
            onValueChange = { viewModel.onProblemDescriptionChange(it) },
            placeholder = "Введите описание проблемы"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Блок выбора фотографий
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Фото", fontSize = 16.sp)
                IconButton(onClick = { imagePickerLauncher.launch("image/*") }) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Добавить фото")
                }
            }

            if (viewModel.selectedImages.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.selectedImages.forEach { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Uploaded Image",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            } else {
                Text(text = "Добавьте фото", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода адреса
        TitledTextField(
            title = "Место",
            value = viewModel.problem.specificLocation, // Связываем с ViewModel
            onValueChange = { viewModel.onSpecificLocationChange(it) },
            placeholder = "Введите адрес места"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка "Отправить"
        Button(
            onClick = { viewModel.onSubmitClick(context) },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Colors.YellowGreen)
        ) {
            Text(text = "Отправить")
        }
    }
}




