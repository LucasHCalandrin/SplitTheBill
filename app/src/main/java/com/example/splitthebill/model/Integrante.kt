package com.example.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Integrante (
    var id: Int,
    var nome: String,
    var valorPago: String,
    var itensComprados : String
): Parcelable