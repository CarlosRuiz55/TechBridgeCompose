package com.softdevelopers.techbridge_compose

import com.google.gson.annotations.SerializedName

data class Consulta5(
    @SerializedName("project name") val project_name: String,
    @SerializedName("Sum of Payment Amount Without Nulls") val sum_of_payment_amount_without_nulls: Int,
    @SerializedName("Sum of Profit Margin Without Nulls") val sum_of_Profit_Margin_Without_Nulls: Int,
    @SerializedName("Count of Payment Amount Without Nulls") val count_of_payment_amount_without_nulls  : Int,

)
