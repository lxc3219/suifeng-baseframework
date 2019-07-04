package org.suifeng.model.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体对象
 */
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标记
     */
    private Integer deleted;

    @Override
    public String toString() {
        return "BaseDO{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseDO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}