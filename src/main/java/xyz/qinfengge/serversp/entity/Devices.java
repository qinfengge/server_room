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
 * @TableName devices
 */
@TableName(value ="devices")
@Data
public class Devices implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 设备类型
     */
    private Integer type;

    /**
     * 设备名称
     */
    private String dname;

    /**
     * 设备状态,1是ok,0是error
     */
    private Integer status;

    /**
     * 设备开关,1是on,0是off
     */
    private Integer onsw;

    /**
     * 设备地址
     */
    private String location;

    /**
     * 设备添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}