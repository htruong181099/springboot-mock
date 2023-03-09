package com.hoangtm14.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@Audited
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class AuditedEntity {
    @JsonIgnore
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;
    @JsonIgnore
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;
    @JsonIgnore
    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

}
