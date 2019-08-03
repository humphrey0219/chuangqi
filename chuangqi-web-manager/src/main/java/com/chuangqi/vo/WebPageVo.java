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
public class WebPageVo extends BaseVo{

    private Long id;//主见 [id, bigint, 19]

    private String name ; //页面名称

    private String number  ;   //页面编号

    private String  title ;     // 标题

    private String keyword  ;   //关键词

    private String description ;    // 简介


}
