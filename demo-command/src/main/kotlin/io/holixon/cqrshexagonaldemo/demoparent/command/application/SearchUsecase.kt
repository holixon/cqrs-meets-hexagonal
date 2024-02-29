package io.holixon.cqrshexagonaldemo.demoparent.command.application

import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.inbound.searchquery.SearchQueryInPort
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.nasaapi.NasaApiOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.searchresult.SearchResultOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.DataItem

open class SearchUsecase(
    val nasaApi: NasaApiOutPort,
    val outPort: SearchResultOutPort
) : SearchQueryInPort {

    override fun search(searchTerm: String): MutableList<DataItem> {
        return nasaApi.findItemsBySearchTerm(searchTerm)
            .stream()
            .map { searchResultItem -> outPort.saveSearchResult(searchResultItem) }
            .map { searchResultItem ->
                searchResultItem.data[0]
            }
            .toList()
    };

}
