package com.moondroid.todolistcompose.presentation.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moondroid.todolistcompose.common.BoxColor
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.presentation.navigation.MyNavigationAction
import com.moondroid.todolistcompose.presentation.ui.theme.CAFE_24
import com.moondroid.todolistcompose.presentation.ui.theme.JALNAN
import com.moondroid.todolistcompose.presentation.ui.theme.NANUM_EB
import com.moondroid.todolistcompose.presentation.ui.theme.Purple40
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigationAction: MyNavigationAction, homeViewModel: HomeViewModel) {
    val list = remember {
        homeViewModel.data
    }

    val showDialog = remember {
        mutableStateOf(false)
    }

    val deleteNote = remember {
        mutableStateOf<Note?>(null)
    }

    if (showDialog.value) {
        Alert(
            showDialog = showDialog.value,
            onDismiss = {
                showDialog.value = false
            },
            onConfirm = {
                homeViewModel.delete(deleteNote.value!!)
                showDialog.value = false
            }
        )
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
                fontFamily = JALNAN
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
                        navigationAction.toNote(0)
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
                NoteList(
                    list = list.value,
                    onItemClicked = { note ->
                        navigationAction.toNote(note.id)
                    },
                    onItemLongClicked = { note ->
                        deleteNote.value = note
                        showDialog.value = true
                    }
                )
            }
        }
    }
}

@Composable
fun NoteList(
    list: List<Note>,
    onItemClicked: (Note) -> Unit = {},
    onItemLongClicked: (Note) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { note ->
            NoteItem(note, onItemClicked, onItemLongClicked)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(note: Note, onClick: (Note) -> Unit, onLongClicked: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .combinedClickable(
                onClick = {
                    onClick(note)
                },
                onLongClick = {
                    onLongClicked(note)
                }
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = note.boxColor.color
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
                    text = getDateFormat(note.date),
                    fontFamily = CAFE_24
                )
            }
            Box {
                Text(
                    text = note.description,
                    fontFamily = NANUM_EB
                )
            }
        }
    }
}

@Composable
fun Alert(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "취소")
            }
        },
        title = {
            Text(text = "삭제하시겠습니까?")
        },
        text = {
            Text(text = "저장된 메모가 삭제됩니다.")
        }
    )
}

@SuppressLint("SimpleDateFormat")
private fun getDateFormat(date: Long): String {
    return SimpleDateFormat("yyyy MM dd HH:mm:ss").format(Date(date))
}

