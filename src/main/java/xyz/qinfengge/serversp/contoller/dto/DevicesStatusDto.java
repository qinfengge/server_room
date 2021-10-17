package xyz.qinfengge.serversp.contoller.dto;

import lombok.Data;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/16 0:48
 */
@Data
public class DevicesStatusDto {
    private Integer count;
    private Integer status;
    private String statusEx;
}
