@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)

package com.moondroid.todolistcompose.presentation.ui.screen.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moondroid.todolistcompose.R
import com.moondroid.todolistcompose.common.BoxColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel = hiltViewModel(),
    noteId: String,
) {
    val saveDone = remember {
        noteViewModel.saveDone
    }
    if (saveDone.value) {
        navController.navigateUp()
    }

    val id = noteId.toInt()

    LaunchedEffect(key1 = id) {
        noteViewModel.getNote(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                model = R.drawable.ic_back,
                contentDescription = "back",
                modifier = Modifier.clickable {
                    navController.navigateUp()
                }
            )
            Text(text = "메모")
            Text(text = "저장", modifier = Modifier.clickable {
                noteViewModel.save()
            })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = noteViewModel.data.value,
                onValueChange = noteViewModel::setText,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = noteViewModel.boxColor.value.color
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BoxColor.values().forEach { boxColor ->
                    BoxColorSelector(
                        boxColor = boxColor,
                        isChecked = noteViewModel.boxColor.value == boxColor,
                        onClick = { color ->
                            noteViewModel.setBoxColor(color)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BoxColorSelector(
    boxColor: BoxColor,
    isChecked: Boolean,
    onClick: (BoxColor) -> Unit,
) {
    Card(
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .clickable { onClick(boxColor) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = boxColor.color)
    ) {
        if (isChecked) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

