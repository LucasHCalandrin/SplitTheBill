package com.example.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Integrante (
    var nome: String,
    var valorPago: Float,
    var itensComprados : String
): Parcelable