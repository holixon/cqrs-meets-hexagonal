package io.holixon.cqrshexagonaldemo.demoparent.transactions.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "cqrs-demo-command")
@Component
class ReactiveProperties(
    var nasaApiSchema: String? = null,
    var nasaApiHostname: String? = null,
    var nasaApiSearchEndpoint: String? = null,
    var nasaApiAssetEndpoint: String? = null,
    var nasaApiCaptionsEndpoint: String? = null,
    var nasaApiAlbumEndpoint: String? = null,
    var apiKey: String? = null
)