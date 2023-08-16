@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)

package com.moondroid.todolistcompose.presentation.ui.feature.note

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moondroid.todolistcompose.R
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.usecase.InsertNoteUseCase
import com.moondroid.todolistcompose.common.BoxColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Composable
fun NoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
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
                onValueChange = noteViewModel::setText
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {

            }
        }
    }
}

