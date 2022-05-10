package com.choice.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.transform.RoundedCornersTransformation
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyImage
import com.choice.core.remote.Url

@Composable
fun InfoUI(navController: NavHostController) {
    
 
    MonkeyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        MonkeyImage(
            urlImage = Url.IMAGE_GITHUB,
            corners = RoundedCornersTransformation(50f)
        )
    }
    
}