package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @CreatedDate
    @Column(updatable = false)
    open var created: Instant?,

    @LastModifiedDate
    open var updated: Instant?,

    @CreatedBy
    @Column(updatable = false)
    open var createdBy: String?,

    @LastModifiedBy
    open var updatedBy: String?

) : Serializable {


}