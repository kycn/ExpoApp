package com.kycn.expoapp.characters.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kycn.expoapp.characters.datasource.CharactersRemoteDataSourceImpl
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.utils.Constants.CHARACTERS
import com.kycn.expoapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var charactersRemoteDataSourceImpl: CharactersRemoteDataSourceImpl

    private lateinit var SUT: CharactersRepositoryImpl

    @Test
    fun `getCharacters called empty list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.success(emptyList<CharacterItem>())))
                .`when`(charactersRemoteDataSourceImpl)
                .getCharacters()
            SUT = CharactersRepositoryImpl(charactersRemoteDataSourceImpl)
            charactersRemoteDataSourceImpl.getCharacters()

            val result = SUT.getCharacters()
            result.collect {
                MatcherAssert.assertThat(it, `is`(ApiResult.success(emptyList<CharacterItem>())))
            }
        }
    }

    @Test
    fun `getCharacters called with predefined list, that list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.success(CHARACTERS)))
                .`when`(charactersRemoteDataSourceImpl)
                .getCharacters()
            SUT = CharactersRepositoryImpl(charactersRemoteDataSourceImpl)
            charactersRemoteDataSourceImpl.getCharacters()

            val result = SUT.getCharacters()
            result.collect {
                MatcherAssert.assertThat(it, `is`(ApiResult.success(CHARACTERS)))
            }
        }
    }

    @Test
    fun `getCharacters called loading returned with loading api result value`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.loading(null)))
                .`when`(charactersRemoteDataSourceImpl)
                .getCharacters()
            SUT = CharactersRepositoryImpl(charactersRemoteDataSourceImpl)
            charactersRemoteDataSourceImpl.getCharacters()

            val result = SUT.getCharacters()
            result.collect {
                MatcherAssert.assertThat(it, `is`(ApiResult.loading(null)))
            }
        }
    }

    @Test
    fun `getCharacters called failure returned with error api result value`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(flowOf(ApiResult.error("Error message", null)))
                .`when`(charactersRemoteDataSourceImpl)
                .getCharacters()
            SUT = CharactersRepositoryImpl(charactersRemoteDataSourceImpl)
            charactersRemoteDataSourceImpl.getCharacters()

            val result = SUT.getCharacters()
            result.collect {
                MatcherAssert.assertThat(it, `is`(ApiResult.error("Error message", null)))
            }
        }
    }
}