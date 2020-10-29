package com.example.memo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll() : List<Memo>

    @Insert
    fun addMemo(memo: Memo)

    @Query("delete from memo where title=:title and contents=:contents")
    fun delete(title : String, contents: String)
}