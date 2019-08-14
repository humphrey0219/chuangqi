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

    private Long orgId;//主见 [id, bigint, 19]

    private String orgName ;// 机构名称

    private String agent  ; //联系人

    private String phone    ; //联系电话

    private Long address   ; //联系地址

    private String code     ; //地址代码

    private String province  ; //机构所在省份

    private String city     ; //城市

    private String county   ; // 县区

    private Integer status ;  //机构状态


}
