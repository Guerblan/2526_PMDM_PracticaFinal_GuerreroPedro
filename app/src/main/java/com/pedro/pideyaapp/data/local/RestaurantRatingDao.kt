package com.pedro.pideyaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantRatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRating(rating: RestaurantRatingEntity)

    @Query("SELECT * FROM restaurant_ratings")
    suspend fun getRatings(): List<RestaurantRatingEntity>
}
