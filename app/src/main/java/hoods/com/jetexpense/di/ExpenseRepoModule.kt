package hoods.com.jetexpense.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import hoods.com.jetexpense.data.repo.ExpenseRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExpenseRepoModule {
    @Binds
    @Singleton
    abstract fun bindExpenseRepo(
        repoImpl: ExpenseRepoImpl,
    ): ExpenseRepo
}