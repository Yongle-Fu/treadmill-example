package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.AndroidTheme
import tech.brainco.treadmilljna.TreadmillSDK

class MainActivity : ComponentActivity() {
    private fun testEncryptionAndDecryption() {
//        TreadmillSDK.initializeLogging(TreadmillSDK.LOG_LEVEL_INFO)

        val plaintext = "Hello, Device!"
        val userId = "550e8400-e29b-41d4-a716-446655440000"
        val snCode = "SN123456"

        // 加密测试
        val encryptedData = TreadmillSDK.encrypt(
            plaintext.toByteArray(),
            userId,
            snCode
        )
        if (encryptedData == null) {
            println("Failed to encrypt")
            return
        }
        println("Encrypted data length: " + encryptedData.size)

        // 解密测试
        val decryptedData = TreadmillSDK.decrypt(
            encryptedData,
            userId,
            snCode
        )
        if (decryptedData == null) {
            println("Failed to decrypt")
            return
        }
        println("Decrypted data length: " + decryptedData.size)

        if (decryptedData.toString(Charsets.UTF_8) != plaintext) {
            println("Decrypted plaintext does not match original plaintext")
        } else {
            println("Decrypted plaintext matches original plaintext")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testEncryptionAndDecryption()
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTheme {
        Greeting("Android")
    }
}