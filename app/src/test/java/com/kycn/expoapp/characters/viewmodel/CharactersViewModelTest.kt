package com.kycn.expoapp.characters.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.characters.usecase.GetCharactersUseCase
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.utils.Constants.CHARACTERS
import com.kycn.expoapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    private lateinit var apiResultObserver: Observer<ApiResult<CharactersResult>>

    private lateinit var SUT: CharactersViewModel

    @After
    fun tearDown() {
        SUT.characters.removeObserver(apiResultObserver)
    }

    @Test
    fun `getCharacters called empty list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.success(CharactersResult(emptyList()))))
                .`when`(getCharactersUseCase)
                .getCharacters()
            SUT = CharactersViewModel(getCharactersUseCase)
            SUT.characters.observeForever(apiResultObserver)

            Mockito.verify(getCharactersUseCase).getCharacters()
            Mockito.verify(apiResultObserver).onChanged(
                ApiResult.success(CharactersResult(emptyList()))
            )
        }
    }

    @Test
    fun `getCharacters called with predefined list, that list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.success(CHARACTERS)))
                .`when`(getCharactersUseCase)
                .getCharacters()
            SUT = CharactersViewModel(getCharactersUseCase)
            SUT.characters.observeForever(apiResultObserver)

            Mockito.verify(getCharactersUseCase).getCharacters()
            Mockito.verify(apiResultObserver).onChanged(
                ApiResult.success(CHARACTERS)
            )
        }
    }

    @Test
    fun `getCharacters called loading returned with loading api result value`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.loading(null)))
                .`when`(getCharactersUseCase)
                .getCharacters()
            SUT = CharactersViewModel(getCharactersUseCase)
            SUT.characters.observeForever(apiResultObserver)

            Mockito.verify(getCharactersUseCase).getCharacters()
            Mockito.verify(apiResultObserver).onChanged(
                ApiResult.loading(null)
            )
        }
    }

    @Test
    fun `getCharacters called failure returned with error api result value`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.error("Error message", null)))
                .`when`(getCharactersUseCase)
                .getCharacters()
            SUT = CharactersViewModel(getCharactersUseCase)
            SUT.characters.observeForever(apiResultObserver)

            Mockito.verify(getCharactersUseCase).getCharacters()
            Mockito.verify(apiResultObserver).onChanged(
                ApiResult.error("Error message", null)
            )
        }
    }
}