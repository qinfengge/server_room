package xyz.qinfengge.serversp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName warning
 */
@TableName(value ="warning")
@Data
public class Warning implements Serializable {
    /**
     * 预警ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 设备ID
     */
    private Integer did;

    /**
     * 取值范围最小值
     */
    private Integer min;

    /**
     * 取值范围最大值
     */
    private Integer max;

    /**
     * 设备类型
     */
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}