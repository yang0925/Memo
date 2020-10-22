package com.example.memo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.memo.room.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll() : List<Memo>

    @Insert
    fun addMemo(memo: Memo)

    @Query("delete from memo where title=:title and subtitle=:subtitle")
    fun delete(title : String, subtitle: String)
}