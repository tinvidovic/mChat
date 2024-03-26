package com.nticoding.mchat.di

import android.app.Application
import androidx.room.Room
import com.nticoding.mchat.data.ConversationDao
import com.nticoding.mchat.data.ConversationDatabase
import com.nticoding.mchat.domain.repository.ConversationRepository
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
    fun provideTrackerDatabase(app: Application): ConversationDatabase {
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
}