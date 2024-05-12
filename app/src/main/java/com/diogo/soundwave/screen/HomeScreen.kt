package com.diogo.soundwave.screen

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.diogo.soundwave.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController
){

    TextButton(
        onClick = {
            navController.navigate(Screen.Track.route("Drake", "One Dance"))
        }
    ){
        Text(
            text = "One Dance - Drake"
        )
    }

}