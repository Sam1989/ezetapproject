package com.ds.myapplication.model

import com.google.gson.annotations.SerializedName

data class NearByRestResponse(

	@field:SerializedName("next_page_token")
	val nextPageToken: String? = null,

	@field:SerializedName("html_attributions")
	val htmlAttributions: List<Any?>? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Northeast1(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class OpeningHours1(

	@field:SerializedName("open_now")
	val openNow: Boolean? = null
)

data class PhotosItem1(

	@field:SerializedName("photo_reference")
	val photoReference: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("html_attributions")
	val htmlAttributions: List<String?>? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Geometry1(

	@field:SerializedName("viewport")
	val viewport: Viewport1? = null,

	@field:SerializedName("location")
	val location: Location1? = null
)

data class ResultsItem(

	@field:SerializedName("types")
	val types: List<String?>? = null,

	@field:SerializedName("business_status")
	val businessStatus: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("photos")
	val photos: List<PhotosItem1?>? = null,

	@field:SerializedName("reference")
	val reference: String? = null,

	@field:SerializedName("user_ratings_total")
	val userRatingsTotal: Int? = null,

	@field:SerializedName("scope")
	val scope: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("opening_hours")
	val openingHours: OpeningHours1? = null,

	@field:SerializedName("geometry")
	val geometry: Geometry1? = null,

	@field:SerializedName("vicinity")
	val vicinity: String? = null,

	@field:SerializedName("plus_code")
	val plusCode: PlusCode1? = null,

	@field:SerializedName("place_id")
	val placeId: String? = null
)

data class PlusCode1(

	@field:SerializedName("compound_code")
	val compoundCode: String? = null,

	@field:SerializedName("global_code")
	val globalCode: String? = null
)

data class Location1(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class Southwest1(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class Viewport1(

	@field:SerializedName("southwest")
	val southwest: Southwest1? = null,

	@field:SerializedName("northeast")
	val northeast: Northeast1? = null
)
