package pdp.android.homeworktwo.model

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("CcyNm_EN")
                val ccyNmEN: String = "",
                @SerializedName("CcyNm_UZC")
                val ccyNmUZC: String = "",
                @SerializedName("Diff")
                val diff: String = "",
                @SerializedName("Rate")
                val rate: String = "",
                @SerializedName("Ccy")
                val ccy: String = "",
                @SerializedName("CcyNm_RU")
                val ccyNmRU: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("CcyNm_UZ")
                val ccyNmUZ: String = "",
                @SerializedName("Code")
                val code: String = "",
                @SerializedName("Nominal")
                val nominal: String = "",
                @SerializedName("Date")
                val date: String = "")