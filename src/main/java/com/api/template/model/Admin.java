package com.api.template.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User{
    private static final long serialVersionUID = 1L;
}
