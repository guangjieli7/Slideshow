package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshow.ui.theme.SlideshowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowTheme {
                val captions = stringArrayResource(R.array.captions_array)
                Slideshow(captions = captions,modifier = Modifier)
            }
        }
    }
}


@Composable
fun Slideshow(captions: Array<String>, modifier: Modifier = Modifier) {
    var page by remember{mutableIntStateOf(0)}
    var pageInput by remember{mutableStateOf("")}
    val images = listOf(R.drawable.honey_bee,R.drawable.bumblebee,R.drawable.stingless_bee,R.drawable.carpenter_bee,R.drawable.leafcutter_bee)
    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFFffd447)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(images[page]),
            contentDescription = captions[page],
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth(0.8f).fillMaxHeight(0.4f)
        )
        Text(
            text = captions[page],
            fontSize = 20.sp,
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "Image ${page+1} of 5",
            fontSize = 16.sp,
            modifier = modifier
        )
        Row(modifier = modifier) {
            Button(
                onClick = { page = if (page != 0) page - 1 else 4 },
                modifier = modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.previous))
            }
            Spacer(modifier = modifier.width(15.dp))
            Button(
                onClick = { page = if (page != 4) page + 1 else 0 },
                modifier = modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
        Column(modifier = modifier) {
            Text(
                text = "Jump to image: ",
                fontSize = 16.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = pageInput,
                onValueChange = { input ->
                    pageInput = input
                    val inputInt = input.toIntOrNull()
                    if (inputInt != null && inputInt in 1..5) {
                        page = inputInt - 1
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                singleLine = true,
                modifier = modifier.height(50.dp).width(50.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(widthDp = 400, heightDp = 400, showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowTheme {
        val captions = stringArrayResource(R.array.captions_array)
        Slideshow(captions = captions,modifier = Modifier)
    }
}