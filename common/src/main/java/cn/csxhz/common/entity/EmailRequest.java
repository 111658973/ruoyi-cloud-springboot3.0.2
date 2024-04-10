package cn.csxhz.common.entity;

import lombok.Data;

/**
 * @Author: Cbf
 * @Date: 2024/3/19
 * @Description:
 */

@Data
public class EmailRequest {
    String from="1557090170@qq.com";
    String to;
    String content;
    String subject;
}