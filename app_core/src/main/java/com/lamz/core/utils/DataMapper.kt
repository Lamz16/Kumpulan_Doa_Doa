package com.lamz.core.utils

import com.lamz.core.data.resource.local.entity.KumpulanDoaEntity
import com.lamz.core.data.resource.remote.response.ListItemKumpulanDoaResponse
import com.lamz.core.domain.model.KumpulanDoaDoa

object DataMapper {
    fun mapResponsesToEntities(input: List<ListItemKumpulanDoaResponse>): List<KumpulanDoaEntity> {
        val doaList = ArrayList<KumpulanDoaEntity>()
        input.map {
            val doa = KumpulanDoaEntity(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                latin = it.latin,
                artinya = it.artinya,
                favorite = false
            )
            doaList.add(doa)
        }
        return doaList
    }

    fun mapEntitiesToDomain(input : List<KumpulanDoaEntity>) : List<KumpulanDoaDoa> =
        input.map {
            KumpulanDoaDoa(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                latin =  it.latin,
                artinya = it.artinya,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input : KumpulanDoaDoa) = KumpulanDoaEntity(
                id = input.id,
                doa = input.doa,
                ayat = input.ayat,
                latin =  input.latin,
                artinya = input.artinya,
                favorite = input.favorite
            )
}