package com.nexdew.wallet.entity;

import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_date = new Date();

    @Column
    private Boolean status = true;

    @Column
    private T created_by;

    @Column
    private T updated_by;
}
