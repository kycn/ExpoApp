package com.kycn.expoapp.characters.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kycn.expoapp.characters.entity.CharacterItem
import com.kycn.expoapp.characters.service.CharactersApi
import com.kycn.expoapp.characters.service.model.CharactersResult
import com.kycn.expoapp.common.service.ApiResult
import com.kycn.expoapp.utils.Constants.CHARACTERS
import com.kycn.expoapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersRemoteDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: CharactersApi

    private lateinit var SUT: CharactersRemoteDataSourceImpl

    @Test
    fun `getCharacters called empty list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(Response.success(emptyList<CharacterItem>()))
                .`when`(api)
                .getCharacters()
            SUT = CharactersRemoteDataSourceImpl(api)
            api.getCharacters()

            val result = SUT.getCharacters()

            result.collect {
                MatcherAssert.assertThat(
                    it,
                    `is`(ApiResult.success(emptyList<CharacterItem>()))
                )
            }
        }
    }

    @Test
    fun `getCharacters called with predefined list, that list returned successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(Response.success(CHARACTERS))
                .`when`(api)
                .getCharacters()
            SUT = CharactersRemoteDataSourceImpl(api)
            api.getCharacters()

            val result = SUT.getCharacters()

            result.collect {
                MatcherAssert.assertThat(
                    it,
                    `is`(ApiResult.success(CHARACTERS))
                )
            }
        }
    }

    @Test
    fun `getCharacters called failure returned with bad request error api result value`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(
                Response.error<ApiResult<CharactersResult>>(
                    400, ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        "{\"key\":[\"value\"]}"
                    )
                )
            ).`when`(api).getCharacters()

            SUT = CharactersRemoteDataSourceImpl(api)
            api.getCharacters()

            val result = SUT.getCharacters()

            result.collect {
                MatcherAssert.assertThat(
                    it,
                    `is`(
                        ApiResult.error(
                            "Network call has failed for a following reason: 400 Response.error()",
                            null
                        )
                    )
                )
            }
        }
    }
}