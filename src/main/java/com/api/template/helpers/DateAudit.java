package com.api.template.helpers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"dateCreated", "lastUpdated"},
        allowGetters = true
        )
public abstract class DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    private Date dateCreated;

    @LastModifiedDate
    @Column(name = "last_updated", nullable = false, updatable = true)
    private Date lastUpdated;

}
