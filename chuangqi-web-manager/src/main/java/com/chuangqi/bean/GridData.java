package com.chuangqi.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用于返回 ligergrid 组件所要求的数据结构 {Rows: 数据，Total: 数据数量}
 */
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GridData<T> {

    @JSONField(name = "Rows")
    List<T> Rows ;

    @JSONField(name = "Total")
    Integer Total ;
}
