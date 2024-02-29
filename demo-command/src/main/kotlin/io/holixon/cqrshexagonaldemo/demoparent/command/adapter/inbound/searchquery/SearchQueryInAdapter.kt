package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.mapper.SearchQueryMapper
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.model.UserDataItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.service.gen.ImageSearchApi
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.inbound.searchquery.SearchQueryInPort
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.RestController

@RestController
open class SearchQueryInAdapter @Autowired constructor(
    private val inPort: SearchQueryInPort,
    private val mapper: SearchQueryMapper
) : ImageSearchApi {

    companion object : KLogging()

    override fun searchGet(q: String): ResponseEntity<List<UserDataItemDto>> {
        val headers = LinkedMultiValueMap<String, String>();
        val search = inPort.search(q);
        val userDataItemDtos = search.stream()
            .map { dataItem -> val toUserDto = mapper.toUserDto(dataItem)
                toUserDto
            }
            .toList()
        return ResponseEntity(userDataItemDtos, headers, HttpStatus.OK)
    }

}