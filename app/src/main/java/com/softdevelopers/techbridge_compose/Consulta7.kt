package com.softdevelopers.techbridge_compose

import com.google.gson.annotations.SerializedName

data class Consulta7(
    @SerializedName("Calendar Hierarchy") val Calendar_Hierarchy: String,
    @SerializedName("Year") val Year: Int,
    @SerializedName("Sum of Profit Margin Without Nulls") val Sum_of_Profit_Margin_Without_Nulls: Int,
    @SerializedName("Count of Payment Amount Without Nulls") val Count_of_Payment_Amount_Without_Nulls: Int
)
