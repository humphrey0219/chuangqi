package com.chuangqi.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrgVo extends BaseVo{

    private Long id;//主见 [id, bigint, 19]

    private String orgName ;// 机构名称

    private String agent  ; //联系人

    private String phone    ; //联系电话

    private String address   ; //联系地址

    private Integer status ;  //机构状态


}
