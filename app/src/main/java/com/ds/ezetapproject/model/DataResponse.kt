package com.ds.ezetapproject.model

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("uidata")
	val uidata: List<UidataItem?>? = null,

	@field:SerializedName("logo-url")
	val logoUrl: String? = null,

	@field:SerializedName("heading-text")
	val headingText: String? = null
)

data class UidataItem(

	@field:SerializedName("uitype")
	val uitype: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("hint")
	val hint: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
