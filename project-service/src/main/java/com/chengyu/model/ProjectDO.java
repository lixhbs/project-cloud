package com.chengyu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lix
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project")
public class ProjectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String des;

    /**
     * 负责人ID
     */
    private String principalId;

    /**
     * 负责人
     */
    private String principalName;

    /**
     * 项目工程名称
     */
    private String moduleName;

    /**
     * 项目状态
     */
    private String status;


}
