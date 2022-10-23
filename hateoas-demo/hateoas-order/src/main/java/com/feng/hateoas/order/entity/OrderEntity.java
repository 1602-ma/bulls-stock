package com.feng.hateoas.order.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
@Entity
@Data
@Table(name="T_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String user;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 成交数量
     */
    private Integer volume;

    /**
     * 成交价格
     */
    private Double price;
}
