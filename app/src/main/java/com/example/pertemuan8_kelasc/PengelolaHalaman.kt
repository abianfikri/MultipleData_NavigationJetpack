package com.example.pertemuan8_kelasc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan8_kelasc.ui.layout.HalamanDua
import com.example.pertemuan8_kelasc.ui.layout.HalamanSatu

enum class PengelolaHalaman {
    Formulir,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactApp(
    viewModel: ContactViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold { innerpadding ->
        val uiState by viewModel.stateUI.collectAsState()

        NavHost(
            navController = navHostController,
            startDestination = PengelolaHalaman.Formulir.name,
            modifier = Modifier.padding(innerpadding)
        )
        {
            composable(route = PengelolaHalaman.Formulir.name) {
                HalamanSatu(
                    onSubmitButtonClicked = {
                        viewModel.setContact(it)
                        navHostController.navigate(PengelolaHalaman.Detail.name)
                    }
                )
            }

            composable(route = PengelolaHalaman.Detail.name) {
                HalamanDua(
                    contactUiState = uiState,
                    onBackButtonClicked = {
                        navHostController.popBackStack(
                            PengelolaHalaman.Formulir.name,
                            false
                        )
                    }
                )
            }
        }
    }
}