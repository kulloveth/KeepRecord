package com.developer.kulloveth.keeprecord

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecordedItemModel::class], version = 3,exportSchema = false)
abstract class RecordRoomDatabase : RoomDatabase() {

    abstract fun recordDao(): Record


    companion object {

        @Volatile
        private var INSTANCE: RecordRoomDatabase? = null


        fun getDatabase(context: Context): RecordRoomDatabase {
//            val tempInstance = INSTANCE
         if (INSTANCE == null) {
//                return tempInstance
//            }
            synchronized(this) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, RecordRoomDatabase::class.java,
                    "record-database"
                ).fallbackToDestructiveMigration().build()
                // INSTANCE = instance
            }
            }
            }
            return INSTANCE as RecordRoomDatabase
        }




    }
}