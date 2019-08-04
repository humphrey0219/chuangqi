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
public class PageImageVo extends BaseVo{

    private Long id;//主键 [id, bigint, 19]

    private Long page ; //页面

    private String number  ;   //图片编号

    private String  url ;     // 图片路径


    private String description ;    // 简介


}
