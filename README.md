# AyDSNewYork2-NYTimes
Para utilizar el servicio externo ofrecido "NYTimesService" es necesario obtener el servicio de la siguiente forma:

NYTimesService nyTimesService = NYTimesInjector.nyTimesService

Para obtener una entidad ArtistDataExternal asociada a un artista solo deben realizarse una llamada a la función getArtistInfo(artistName: String):

val infoNYTimes = nyTimesService.getArtistInfo(artistName)

El valor de infoNYTimes ahora será:
-Una entidad ArtistWithDataExternal, en caso de existir, con toda la informacion.
-EmptyArtistDataExternal, en caso de que la búsqueda no sea exitosa.
