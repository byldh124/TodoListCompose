package com.moondroid.todolistcompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.moondroid.todolistcompose.presentation.navigation.MyDestination
import com.moondroid.todolistcompose.presentation.navigation.MyNavGraph
import com.moondroid.todolistcompose.presentation.navigation.MyNavigationAction
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
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    TodoListComposeTheme {
        val navController = rememberNavController()
        val navigationAction = remember(navController) {
            MyNavigationAction(navController = navController)
        }
        MyNavGraph(
            navController = navController,
            navigationAction = navigationAction,
            startDestination = MyDestination.HOME_ROUTE
        )
    }
}