package com.ziad.school.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class Person extends AbstractPersistable<UUID> implements Serializable {
    // Read an article about using AbstractPerishable and AbstractAuditable
    // which is some common fields to use rather than creating it from the beginning

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column
    private Boolean isMale;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 15)
    private String mobile;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    private Boolean isActive;

    //TODO think of adding this fields
    // lastLoginDate and lastLoginIp

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
