package com.chengyu.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lix.
 * @title
 * @program project-cloud
 * @description
 * @createtime 2021-03-14 15:46
 */
@ApiModel(value = "项目对象")
@Data
public class ProjectRequest
{
    @ApiModelProperty(value = "项目名称", example = "金民工程")
    private String name;

    @ApiModelProperty(value = "项目描述", example = "民政系统")
    private String des;

    @ApiModelProperty(value = "项目负责人ID", example = "")
    @JsonProperty("principal_id")
    private String principalId;

    @ApiModelProperty(value = "项目负责人名称", example = "张三")
    @JsonProperty("principal_name")
    private String principalName;
}
