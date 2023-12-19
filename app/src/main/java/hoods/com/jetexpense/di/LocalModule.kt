package hoods.com.jetexpense.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import hoods.com.jetexpense.data.local.dao.ExpenseDao
import hoods.com.jetexpense.data.local.dao.IncomeDao
import hoods.com.jetexpense.data.local.database.ExpenseDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideExpenseDatabase(
        @ApplicationContext context: Context,
    ): ExpenseDatabase = Room.databaseBuilder(
        context,
        ExpenseDatabase::class.java,
        "transaction_db",
    ).build()

    @Provides
    @Singleton
    fun provideExpenseDao(expenseDatabase: ExpenseDatabase): ExpenseDao = expenseDatabase.expenseDao

    @Provides
    @Singleton
    fun provideIncomeDao(expenseDatabase: ExpenseDatabase):IncomeDao = expenseDatabase.incomeDao
}