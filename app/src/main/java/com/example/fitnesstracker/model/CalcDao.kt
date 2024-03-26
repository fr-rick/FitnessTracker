package com.example.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// Interface Dao serve para vc ter métodos para manipular seu banco de dados
@Dao
interface CalcDao {

    @Insert
    fun insert(calc: Calc)

    @Query("SELECT * FROM Calc WHERE type = :type")
    fun getRegisterByType(type: String) : List<Calc>

    // Query -> buscar
    // Update -> atualizar
    // Delete -> excluir

}