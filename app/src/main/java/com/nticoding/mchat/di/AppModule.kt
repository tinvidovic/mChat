package com.nticoding.mchat.di

import android.app.Application
import androidx.room.Room
import com.nticoding.mchat.data.ConversationDatabase
import com.nticoding.mchat.domain.repository.ConversationRepository
import com.nticoding.mchat.domain.use_case.GetConversationMessagesUseCase
import com.nticoding.mchat.domain.use_case.InsertMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConversationDatabase(app: Application): ConversationDatabase {
        return Room.databaseBuilder(
            app,
            ConversationDatabase::class.java,
            "conversation_db"
        ).createFromAsset("database/conversations_preload.db").build()
    }

    @Provides
    @Singleton
    fun provideConversationRepository(
        db: ConversationDatabase
    ): ConversationRepository {

        return com.nticoding.mchat.data.repository.ConversationRepository(
            db.dao
        )
    }

    @Provides
    @Singleton
    fun provideGetConversationMessagesUseCase(
        conversationRepository: ConversationRepository
    ): GetConversationMessagesUseCase {
        return GetConversationMessagesUseCase(conversationRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMessageUseCase(
        conversationRepository: ConversationRepository
    ): InsertMessageUseCase {
        return InsertMessageUseCase(conversationRepository)
    }
}