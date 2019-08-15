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
public class ZoneVo extends BaseVo{

    private Long id;//主见 [id, bigint, 19]

    private String province ; //省

    private String city  ;   //城市

    private String county ;  //区

    private String zip  ; //区域代码





}
