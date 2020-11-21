package com.example.pokerapp.viewmodel

import android.app.Application
import com.example.pokerapp.rest.repository.PokerRepository
import com.example.pokerapp.util.SecurityPreferences
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun is_logged_return(){
        val mainActivityViewModel = MainViewModel(Mockito.mock(Application::class.java),
            Mockito.mock(PokerRepository::class.java),
        Mockito.mock(SecurityPreferences::class.java)
        )

        mainActivityViewModel.islogged()
        assertTrue(mainActivityViewModel.logged.value!!)
    }

    @Test
    fun is_foounf_result_by_name(){
        val mainActivityViewModel = MainViewModel(Mockito.mock(Application::class.java),
            Mockito.mock(PokerRepository::class.java),
            Mockito.mock(SecurityPreferences::class.java)
        )

        mainActivityViewModel.getPokemonByName("pikachu")
    }
    
}