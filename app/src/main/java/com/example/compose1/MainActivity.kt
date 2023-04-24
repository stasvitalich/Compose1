// Importing required packages and modules
package com.example.compose1

// Importing Android and Jetpack Compose components
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.ui.theme.Compose1Theme
import kotlin.math.round

// Main Activity class extending ComponentActivity
class MainActivity : ComponentActivity() {
    // Overriding onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        // Setting up the content of the MainActivity
        super.onCreate(savedInstanceState)
        setContent {
            // Applying the custom theme
            Compose1Theme {
                // Creating a Surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    // Calling the 'createBizCard' composable function
                    createBizCard()
                }
            }
        }
    }
}

// Composable function to create the business card layout
@Composable
fun createBizCard() {
    // Defining a mutable state for the button click
    val clickedButtonState = remember {
        mutableStateOf(false)
    }
    // Creating a Surface with a Card, arranged in a Column
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Card(
            modifier = Modifier
                .height(390.dp)
                .width(290.dp)
                .padding(12.dp),
            RoundedCornerShape(corner = CornerSize(25.dp)),
            elevation = 5.dp,
            backgroundColor = Color.LightGray
        ) {
            Column(
                modifier = Modifier.height(250.dp), verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Composable functions for creating the profile image, divider, info, button, and content
                CreateImageProfile()
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    color = Color.Gray,
                    thickness = 1.dp,
                )

                CreateInfo()

                Button(
                    onClick = {
                        clickedButtonState.value = !clickedButtonState.value
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Portfolio")
                }
                if (clickedButtonState.value) {
                    Content()
                } else {
                    Box() {

                    }
                }

            }


        }
    }
}

// Composable function for the portfolio content
@Preview
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            border = BorderStroke(width = 2.dp, color = Color.Blue)
        ) {
            Portfolio(data = listOf("Project1", "Project2", "Project3", "Project 4"))
        }
    }
}

// Composable function for the portfolio project list
@Composable
fun Portfolio(data: List<String>) {
    // Creating a LazyColumn to display the projects in a list
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 5.dp

            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = Color.LightGray)
                        .padding(10.dp)
                ) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "Item of the portfolio")
                    }
                    
                }
            }
        }
    }
}

// Composable function to create the info section
@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp)
                .padding(bottom = 5.dp),
            text = "Stanislav Kniazev",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 25.sp
        )
        Text(
            text = "Android Compose Programmer \n @Stasvitalich",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 5.dp)
        )

    }
}

// Composable function to create the profile image
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(10.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Yellow),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "The photo of the user",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}


// Preview function for the DefaultPreview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose1Theme {
        createBizCard()
    }
}