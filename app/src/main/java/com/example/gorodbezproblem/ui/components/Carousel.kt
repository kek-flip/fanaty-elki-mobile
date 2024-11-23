package com.example.gorodbezproblem.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gorodbezproblem.ui.theme.Colors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComposableCarousal(
    carouselItems: List<Int>,
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp), // Remove top and right padding, keep bottom padding
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCarousel(
            carouselItems = carouselItems,
            selectedItemIndex = selectedItemIndex,
            onSelectedItemChange = { index ->
                selectedItemIndex = index
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        DotsIndicator(
            numDots = carouselItems.size,
            currentIndex = selectedItemIndex,
            onDotClick = { index ->
                selectedItemIndex = index
            }
        )

    }
}

@Composable
fun ImageCarousel(
    carouselItems: List<Int>,
    selectedItemIndex: Int,
    onSelectedItemChange: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { carouselItems.size }) // Состояние пагера

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onSelectedItemChange(page)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth() // Заполняет родительский контейнер
    ) { page ->
        AnimatedVisibility(
            visible = selectedItemIndex >= 0, // To prevent animation on first item when recomposing
            enter = slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(durationMillis = 1000)
            )
        ) {
            Crossfade(targetState = carouselItems[page], label = "") { imageRes ->
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth() // Take full width available
                        .height(320.dp), // Take full height available
                    contentScale = ContentScale.Fit, // Image takes full width and height of the Box
                )
            }
        }
    }
}

@Composable
fun DotsIndicator(
    numDots: Int,
    currentIndex: Int,
    onDotClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until numDots) {
            Dot(index = i, isSelected = i == currentIndex, onDotClick)
            Spacer(modifier = Modifier.width(5.dp)) // Add space between dots
        }
    }
}

@Composable
fun Dot(
    index: Int,
    isSelected: Boolean,
    onDotClick: (Int) -> Unit
) {
    val color = if (isSelected) Colors.YellowGreen else Colors.Background
    Box(
        modifier = Modifier
            .size(12.dp) // Increase the size of dots for better visibility
            .background(color = color, shape = CircleShape)
            .clickable {
                onDotClick(index)
            }
    )
}