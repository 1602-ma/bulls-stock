package com.feng.hateoas.order.repository;

import com.feng.hateoas.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author f
 * @date 2022/10/19 22:32
 */
@RepositoryRestResource(path = "/order")
public interface OrderRespository extends JpaRepository<OrderEntity, Long> {

    /**
     * 根据用户查找获取所有的订单信息
     * @param user user
     * @return orders
     */
    public List<OrderEntity> findByUser(@Param("user") String user);
}
