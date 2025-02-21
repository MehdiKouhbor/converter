package com.example.converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarkThemeConverter()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DarkThemeConverter() {
    var dpValue by remember { mutableStateOf("") }
    var sdpPhoneValue by remember { mutableStateOf("") }
    var sdpTabletValue by remember { mutableStateOf("") }
    val conversionRatioPhone = 0.833
    val conversionRatioTablet = 1.2

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.TopCenter
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "DP to SDP Converter",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                OutlinedTextField(
                    value = dpValue,
                    onValueChange = {
                        dpValue = it
                        val dpInput = it.toDoubleOrNull()
                        if (dpInput != null) {
                            sdpPhoneValue = (dpInput * conversionRatioPhone).toInt().toString()
                            sdpTabletValue = (dpInput * conversionRatioTablet).toInt().toString()
                        } else {
                            sdpPhoneValue = ""
                            sdpTabletValue = ""
                        }
                    },
                    label = { Text("Enter DP Value", color = Color.Gray) },
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFBB86FC),
                        unfocusedBorderColor = Color.Gray
                    )
                )

                Text(
                    text = "Equivalent value for Phone: $sdpPhoneValue",
                    fontSize = 16.sp,
                    color = Color.White
                )

                Text(
                    text = "Equivalent value for Tablet: $sdpTabletValue",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDarkThemeConverter() {
    DarkThemeConverter()
}
