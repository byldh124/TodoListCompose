package com.moondroid.todolistcompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moondroid.todolistcompose.presentation.ui.feature.home.HomeScreen
import com.moondroid.todolistcompose.presentation.ui.feature.home.HomeViewModel
import com.moondroid.todolistcompose.presentation.ui.feature.note.NoteScreen
import com.moondroid.todolistcompose.presentation.ui.theme.TodoListComposeTheme
import dagger.hilt.android.AndroidEntryPoint

fun Any.debug(msg: String) {
    Log.e("Moondroid", "[${this.javaClass.simpleName}] $msg")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            MainContainer()
        }
    }
}

@Composable
fun MainContainer() {
    val navController = rememberNavController()
    TodoListComposeTheme {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { backStackEntry ->
                //Main 에서 viewModel 생성 후 전달
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(
                    navController = navController,
                    homeViewModel = homeViewModel
                )
            }

            composable("note") {
                //Note 에서 viewModel 생성
                //val noteViewModel = hiltViewModel<NoteViewModel>()
                NoteScreen(
                    navController = navController,
                    //noteViewModel = noteViewModel
                )
            }
        }
    }
}