package com.cavista.cavistaapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import javax.sql.DataSource

@Dao
interface ImageDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(comment : ImageComment)

        @Query("SELECT * from ImageComment WHERE imageID = :imageId ORDER BY uid DESC")
        fun getAllComment(imageId:String) : LiveData<List<ImageComment>>

        @Delete
        fun delete(comment : ImageComment)

        @Update
        fun update(comment : ImageComment)

}