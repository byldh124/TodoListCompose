package com.moondroid.todolistcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.moondroid.todolistcompose.presentation.ui.screen.home.HomeScreen
import com.moondroid.todolistcompose.presentation.ui.screen.home.HomeViewModel
import com.moondroid.todolistcompose.presentation.ui.screen.note.NoteScreen

object ArgumentKey {
    const val NOTE_ID = "noteId"
}

@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationAction: MyNavigationAction,
    startDestination: String = MyDestination.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MyDestination.HOME_ROUTE) { _ ->
            //Main 에서 viewModel 생성 후 전달
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                navigationAction = navigationAction,
                homeViewModel = homeViewModel
            )
        }

        composable(route = MyDestination.NOTE_ROUTE) { backStackEntry ->
            //Note 에서 viewModel 생성
            //val noteViewModel = hiltViewModel<NoteViewModel>()

            NoteScreen(
                navController = navController,
                //noteViewModel = noteViewModel
                noteId = backStackEntry.arguments?.getString(ArgumentKey.NOTE_ID) ?: ""
            )
        }
    }
}