package com.pablojc.marvelapp.data.persistence.room.entities

import androidx.room.*

@Entity(tableName = "heroe", indices = [Index("name",unique = true)])
data class HeroEntity(@ColumnInfo(name = "name") val name: String?,
                      @ColumnInfo(name = "photo") val photo: String?,
                      @ColumnInfo(name = "realName") val realName: String?,
                      @ColumnInfo(name = "height") val height: String?,
                      @ColumnInfo(name = "power") val power: String?,
                      @ColumnInfo(name = "abilities") val abilities: String?,
                      @ColumnInfo(name = "groups") val groups: String?) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}

