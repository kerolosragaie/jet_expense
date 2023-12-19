package hoods.com.jetexpense.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import hoods.com.jetexpense.data.repo.ExpenseRepo
import hoods.com.jetexpense.data.repo.ExpenseRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ExpenseRepoModule {
    @Binds
    @Singleton
    abstract fun bindExpenseRepo(
        repoImpl: ExpenseRepoImpl,
    ): ExpenseRepo
}