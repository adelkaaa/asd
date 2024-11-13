package com.example.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apiapp.ui.theme.ApiappTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApiService {
    @GET("alpha/{code}")
    suspend fun getCountryByCode(@Path("code") countryCode: String): List<CountryResponse>
}

data class CountryResponse(
    val name: Name,
    val capital: List<String> = listOf(),
    val population: Long,
    val area: Double,
    val flags: Flags
)

data class Name(val common: String, val official: String)
data class Flags(val png: String)

class MainActivity : ComponentActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(RestCountriesApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiappTheme {
                MainScreen(apiService)
            }
        }
    }
}

@Composable
fun MainScreen(apiService: RestCountriesApiService) {
    var countryCode by remember { mutableStateOf("") }
    var countryInfo by remember { mutableStateOf<List<CountryResponse>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = countryCode,
                    onValueChange = { countryCode = it },
                    label = { Text("Введите код страны") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                errorMessage = null
                                val response = apiService.getCountryByCode(countryCode)
                                countryInfo = response

                                // Если список пустой, выводим сообщение об ошибке
                                if (countryInfo.isNullOrEmpty()) {
                                    errorMessage = "Страна с таким кодом не найдена."
                                }
                            } catch (e: Exception) {
                                errorMessage = "Ошибка: не удалось получить данные"
                                countryInfo = null
                            }
                        }
                    }
                ) {
                    Text("Получить информацию о стране")
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (errorMessage != null) {
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                } else if (!countryInfo.isNullOrEmpty()) {
                    // Передаем первый элемент списка, если он существует
                    CountryInfoDisplay(countryInfo!!.first())
                }
            }
        }
    )
}

@Composable
fun CountryInfoDisplay(country: CountryResponse) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Страна: ${country.name.common}", style = MaterialTheme.typography.titleLarge)
        Text(text = "Официальное название: ${country.name.official}")
        Text(text = "Столица: ${country.capital.joinToString()}")
        Text(text = "Население: ${country.population}")
        Text(text = "Площадь: ${country.area} км²")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ApiappTheme {
        MainScreen(apiService = object : RestCountriesApiService {
            override suspend fun getCountryByCode(countryCode: String) = listOf(
                CountryResponse(
                    name = Name("Россия", "Российская Федерация"),
                    capital = listOf("Москва"),
                    population = 144478050,
                    area = 17098242.0,
                    flags = Flags("")
                )
            )
        })
    }
}
