package com.nticoding.mchat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nticoding.mchat.data.entity.ConversationEntity
import com.nticoding.mchat.data.entity.MessageEntity

@Database(
    entities = [ConversationEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ConversationDatabase : RoomDatabase() {

    abstract val dao: ConversationDao
}