package com.moondroid.todolistcompose.presentation.navigation

import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.moondroid.todolistcompose.domain.model.Note


object MyDestination {
    const val HOME_ROUTE = "home"
    const val NOTE_ROUTE = "note/{noteId}"
}

class MyNavigationAction(navController: NavHostController) {
    val toNote: (Int) -> Unit = { id ->
        navController.navigate("note/$id")
    }
}