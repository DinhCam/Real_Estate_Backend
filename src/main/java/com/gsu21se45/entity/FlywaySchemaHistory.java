package com.gsu21se45.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "flyway_schema_history", schema = "real_estate", catalog = "")
public class FlywaySchemaHistory {
    private int installedRank;
    private String version;
    private String description;
    private String type;
    private String script;
    private Integer checksum;
    private String installedBy;
    private Timestamp installedOn;
    private int executionTime;
    private byte success;

    @Id
    @Column(name = "installed_rank", nullable = false)
    public int getInstalledRank() {
        return installedRank;
    }

    public void setInstalledRank(int installedRank) {
        this.installedRank = installedRank;
    }

    @Basic
    @Column(name = "version", nullable = true, length = 50)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "script", nullable = false, length = 1000)
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Basic
    @Column(name = "checksum", nullable = true)
    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    @Basic
    @Column(name = "installed_by", nullable = false, length = 100)
    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    @Basic
    @Column(name = "installed_on", nullable = false)
    public Timestamp getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(Timestamp installedOn) {
        this.installedOn = installedOn;
    }

    @Basic
    @Column(name = "execution_time", nullable = false)
    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    @Basic
    @Column(name = "success", nullable = false)
    public byte getSuccess() {
        return success;
    }

    public void setSuccess(byte success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlywaySchemaHistory that = (FlywaySchemaHistory) o;
        return installedRank == that.installedRank && executionTime == that.executionTime && success == that.success && Objects.equals(version, that.version) && Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(script, that.script) && Objects.equals(checksum, that.checksum) && Objects.equals(installedBy, that.installedBy) && Objects.equals(installedOn, that.installedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installedRank, version, description, type, script, checksum, installedBy, installedOn, executionTime, success);
    }
}
