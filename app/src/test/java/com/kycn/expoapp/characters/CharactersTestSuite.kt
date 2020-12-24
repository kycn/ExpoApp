package com.kycn.expoapp.characters

import com.kycn.expoapp.characters.datasource.CharactersRemoteDataSourceImplTest
import com.kycn.expoapp.characters.repository.CharactersRepositoryImplTest
import com.kycn.expoapp.characters.usecase.GetCharactersUseCaseTest
import com.kycn.expoapp.characters.viewmodel.CharactersViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CharactersRemoteDataSourceImplTest::class,
    CharactersRepositoryImplTest::class,
    GetCharactersUseCaseTest::class,
    CharactersViewModelTest::class
)
class CharactersTestSuite