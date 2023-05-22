package ayds.aknewyork.external.service.data.entities

sealed class ArtistDataExternal {

    data class ArtistWithDataExternal(
        val name: String?,
        val info: String?,
        val url: String,
        var isInDatabase: Boolean,
    ): ArtistDataExternal()

    object EmptyArtistDataExternal : ArtistDataExternal()
}