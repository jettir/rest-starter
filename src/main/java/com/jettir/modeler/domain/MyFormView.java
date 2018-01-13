package com.jettir.modeler.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MyFormView.
 */
@Entity
@Table(name = "my_form_view")
public class MyFormView implements Serializable {

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
    private MyEntity myEntity;

    @ManyToOne
    private MyBranch myBranch;

    @ManyToOne
    private MyRelease myRelease;

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

    public MyFormView name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public MyFormView created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public MyFormView modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public MyFormView modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MyFormView createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public MyEntity getMyEntity() {
        return myEntity;
    }

    public MyFormView myEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
        return this;
    }

    public void setMyEntity(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

    public MyBranch getMyBranch() {
        return myBranch;
    }

    public MyFormView myBranch(MyBranch myBranch) {
        this.myBranch = myBranch;
        return this;
    }

    public void setMyBranch(MyBranch myBranch) {
        this.myBranch = myBranch;
    }

    public MyRelease getMyRelease() {
        return myRelease;
    }

    public MyFormView myRelease(MyRelease myRelease) {
        this.myRelease = myRelease;
        return this;
    }

    public void setMyRelease(MyRelease myRelease) {
        this.myRelease = myRelease;
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
        MyFormView myFormView = (MyFormView) o;
        if (myFormView.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myFormView.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyFormView{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
