package com.jettir.modeler.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MyParameter.
 */
@Entity
@Table(name = "my_parameter")
public class MyParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private Instant created;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    private MyBranch myBranch;

    @ManyToOne
    private MyRelease myRelease;

    @ManyToOne
    private MyParameterType myParameterType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MyParameter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public MyParameter created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public MyParameter modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public MyParameter modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MyParameter createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public MyBranch getMyBranch() {
        return myBranch;
    }

    public MyParameter myBranch(MyBranch myBranch) {
        this.myBranch = myBranch;
        return this;
    }

    public void setMyBranch(MyBranch myBranch) {
        this.myBranch = myBranch;
    }

    public MyRelease getMyRelease() {
        return myRelease;
    }

    public MyParameter myRelease(MyRelease myRelease) {
        this.myRelease = myRelease;
        return this;
    }

    public void setMyRelease(MyRelease myRelease) {
        this.myRelease = myRelease;
    }

    public MyParameterType getMyParameterType() {
        return myParameterType;
    }

    public MyParameter myParameterType(MyParameterType myParameterType) {
        this.myParameterType = myParameterType;
        return this;
    }

    public void setMyParameterType(MyParameterType myParameterType) {
        this.myParameterType = myParameterType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyParameter myParameter = (MyParameter) o;
        if (myParameter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myParameter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyParameter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
