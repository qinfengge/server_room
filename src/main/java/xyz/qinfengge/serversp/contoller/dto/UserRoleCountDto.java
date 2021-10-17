package xyz.qinfengge.serversp.contoller.dto;

import lombok.Data;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/16 0:20
 */
@Data
public class UserRoleCountDto {
    private Integer count;
    private Integer role;
    private String roleEx;
}
