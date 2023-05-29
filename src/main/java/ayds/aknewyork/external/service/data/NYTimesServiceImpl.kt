package ayds.aknewyork.external.service.data

import ayds.aknewyork.external.service.data.entities.ArtistDataExternal.ArtistWithDataExternal
import ayds.aknewyork.external.service.data.entities.ArtistDataExternal.EmptyArtistDataExternal
import ayds.aknewyork.external.service.data.entities.ArtistDataExternal
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
            ArtistWithDataExternal(artistName, infoArtist, nyTimesToArtistResolver.getURL(response))
        }
    }

    private fun getInfoFromAPI(artistName: String?) = nyTimesAPI.getArtistInfo(artistName).execute()

}