package xyz.qinfengge.serversp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName msg
 */
@TableName(value ="msg")
@Data
public class Msg implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String msg;

    /**
     * 
     */
    private Date dataTime;

    private Integer isShow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}