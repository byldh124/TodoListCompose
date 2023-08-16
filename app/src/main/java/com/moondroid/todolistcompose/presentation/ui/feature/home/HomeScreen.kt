package com.moondroid.todolistcompose.presentation.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.moondroid.todolistcompose.R
import com.moondroid.todolistcompose.common.BoxColor
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.presentation.ui.theme.Purple40
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val list = remember {
        homeViewModel.data
    }

    LaunchedEffect(key1 = list) {
        homeViewModel.getNote()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "메모장",
                color = Color.Red,
                fontFamily = FontFamily(Font(R.font.jalnan))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    contentColor = Color.White,
                    shape = CircleShape,
                    containerColor = Purple40,
                    onClick = {
                        navController.navigate("note")
                    }) {
                    Icon(Icons.Filled.Add, "")
                }
            },
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                NoteList(list = list.value)
            }
        }
    }
}

@Composable
fun NoteList(
    list: List<Note>,
    onItemClicked: (Note) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list.size) { index ->
            NoteItem(
                note = list[index], onClick = onItemClicked
            )
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(note) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = note.boxColor.value
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = SimpleDateFormat("yyyy MM dd HH:mm:ss").format(Date(note.date))
                )
            }
            Box {
                Text(text = note.description)
            }
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    NoteItem(note = Note(description = "Hello", date = 0L, boxColor = BoxColor.BLUE), onClick = {})
}

