package com.example.materialpass

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val email: String
)
@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>

    // Add more functions as needed (e.g., update, delete)
}
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

class UserRepository(private val userDao: UserDao) {
    val allUser = MutableLiveData<List<User>>()
    val foundUser = MutableLiveData<User>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(newUser)
        }

    }
    // TODO add user information
}


/*
data class SiteInformation(
    val title: String = "", val url: String = "N/A", val credentials: Credentials
)

data class Credentials(
    val username: String?, val email: String?, val password: String?
)
 */