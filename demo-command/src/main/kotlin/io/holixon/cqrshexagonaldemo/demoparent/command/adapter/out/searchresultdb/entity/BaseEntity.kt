package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @CreatedDate
    @Column(updatable = false)
    var created: Instant? = null

    @LastModifiedDate
    val updated: Instant? = null

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null

    @LastModifiedBy
    val updatedBy: String? = null
}