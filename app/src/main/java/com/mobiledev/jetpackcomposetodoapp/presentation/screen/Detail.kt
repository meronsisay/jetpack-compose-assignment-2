package com.mobiledev.jetpackcomposetodoapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobiledev.jetpackcomposetodoapp.data.model.Todo

@Composable
fun DetailScreen(
    todo: Todo?,
    onBack: () -> Unit
) {
    // Define your fixed purple colors
    val purplePrimary = Color(0xFF7C4DFF)          // Deep purple
    val purpleOnPrimary = Color.White
    val purpleSecondaryContainer = Color(0xFFD1C4E9) // Light purple container
    val purpleOnSecondaryContainer = Color(0xFF311B92) // Dark purple text on container
    val purpleError = Color(0xFFD55C5C)             // Red error color
    val background = Color(0xFFF3E5F5)              // Very light purple background
    val outline = Color(0xFFB39DDB)                  // Purple outline
    val surface = Color.White
    val onSurface = Color(0xFF4A148C)                // Dark purple for text

    Scaffold(
        topBar = {
            DetailTopBar(onBackClick = onBack, primaryColor = purplePrimary, onPrimaryColor = purpleOnPrimary)
        },
        containerColor = background
    ) { padding ->
        todo?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = purplePrimary
                    )
                    Surface(
                        color = if (it.completed) purpleSecondaryContainer else purpleError.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = if (it.completed) "Completed" else "Incomplete",
                            color = if (it.completed) purpleOnSecondaryContainer else purpleError,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = outline
                )

                Text(
                    "Title",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    color = purplePrimary
                )
                Text(
                    text = "\"${it.title}\"",
                    style = MaterialTheme.typography.bodyLarge,
                    color = onSurface
                )

                Text(
                    "Details",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    color = purplePrimary
                )
                Text("ID: ${it.id}", style = MaterialTheme.typography.bodyLarge, color = onSurface)
                Text("User ID: ${it.userId}", style = MaterialTheme.typography.bodyLarge, color = onSurface)

                Row {
                    Text("Status:", style = MaterialTheme.typography.bodyLarge, color = onSurface)
                    Text(
                        text = " ${if (it.completed) "Completed" else "Incomplete"}",
                        color = if (it.completed) purplePrimary else purpleError,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = purplePrimary)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBackClick: () -> Unit,
    primaryColor: Color,
    onPrimaryColor: Color
) {
    TopAppBar(
        title = { Text("Todo Details") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = onPrimaryColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
            titleContentColor = onPrimaryColor,
            navigationIconContentColor = onPrimaryColor
        )
    )
}
