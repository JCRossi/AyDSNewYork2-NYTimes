package ayds.aknewyork.external.service

import ayds.aknewyork.external.service.ArtistDataExternal.ArtistWithDataExternal
import ayds.aknewyork.external.service.ArtistDataExternal.EmptyArtistDataExternal
import java.io.IOException

interface NYTimesService {
    fun getArtistInfo(artistName: String?): ArtistDataExternal
}

class NYTimesServiceImpl(
    private val nyTimesAPI: NYTimesAPI,
    private val nyTimesToArtistResolver: NYTimesToArtistResolver,
) : NYTimesService {

    override fun getArtistInfo(artistName: String?): ArtistDataExternal {
        var infoArtist: String? = null
        try {
            infoArtist = nyTimesToArtistResolver.generateFormattedResponse(getInfoFromAPI(artistName), artistName)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return if(infoArtist == null){
            EmptyArtistDataExternal
        }
        else{
            val response = getInfoFromAPI(artistName)
            ArtistWithDataExternal(artistName, infoArtist, nyTimesToArtistResolver.getURL(response), false)
        }
    }

    private fun getInfoFromAPI(artistName: String?) = nyTimesAPI.getArtistInfo(artistName).execute()

}