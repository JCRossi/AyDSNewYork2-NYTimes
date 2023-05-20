package ayds.aknewyork.external.service

sealed class ArtistDataExternal {

    data class ArtistWithDataExternal(
        val name: String?,
        val info: String?,
        val url: String,
        var isInDatabase: Boolean,
    ): ArtistDataExternal()

    object EmptyArtistDataExternal : ArtistDataExternal()
}