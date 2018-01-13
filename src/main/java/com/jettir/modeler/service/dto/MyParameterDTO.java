package com.jettir.modeler.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the MyParameter entity.
 */
public class MyParameterDTO implements Serializable {

    private Long id;

    private String name;

    private Instant created;

    private Instant modified;

    private String modifiedBy;

    private String createdBy;

    private Long myBranchId;

    private Long myReleaseId;

    private Long myParameterTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getMyBranchId() {
        return myBranchId;
    }

    public void setMyBranchId(Long myBranchId) {
        this.myBranchId = myBranchId;
    }

    public Long getMyReleaseId() {
        return myReleaseId;
    }

    public void setMyReleaseId(Long myReleaseId) {
        this.myReleaseId = myReleaseId;
    }

    public Long getMyParameterTypeId() {
        return myParameterTypeId;
    }

    public void setMyParameterTypeId(Long myParameterTypeId) {
        this.myParameterTypeId = myParameterTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyParameterDTO myParameterDTO = (MyParameterDTO) o;
        if(myParameterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myParameterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyParameterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
