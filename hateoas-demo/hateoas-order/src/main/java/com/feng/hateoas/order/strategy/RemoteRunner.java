package com.feng.hateoas.order.strategy;

import com.feng.hateoas.order.entity.OrderEntity;
import com.feng.hateoas.order.entity.StocksEntity;
import com.feng.hateoas.order.repository.OrderRespository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author f
 * @date 2022/10/19 22:24
 */
@Component
@Log4j2
public class RemoteRunner implements ApplicationRunner {
    /** 指定股票服务接口地址 */
    private static final URI ROOT_URI = URI.create("http://localhost:8080/");

    /**
     * 负责 http 远程调用
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * order 数据层操作接口
     */
    @Autowired
    private OrderRespository orderRespository;

    /**
     * 服务启动后执行
     * @param args args
     * @throws Exception ex
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Link stocksLink = getLink(ROOT_URI, "stocksEntities");
        // 1.查询股票信息
        queryStocks(stocksLink);
        // 2.更新股票价格
        Link updateLink = getLink(ROOT_URI.resolve("stocks/1"), "stocksEntity");
        Resource<StocksEntity> americano = updateStocks(updateLink);
        // 3.重新查询打印股票信息
        queryStocks(stocksLink);
        // 4.生成订单信息
        OrderEntity order = OrderEntity.builder()
                .user("mirson")
                .stockName("建设银行")
                .volume(1000)
                .price(99.9)
                .build();
        orderRespository.save(order);
    }

    /**
     * 获取请求链接
     * @param uri uri
     * @param rel rel
     * @return link
     */
    private Link getLink(URI uri, String rel) {
        ResponseEntity<Resource<Link>> rootResp = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Link>>() {
        });
        Link link = rootResp.getBody().getLink(rel);
        log.info("Link:{}", link);
        return link;
    }

    /**
     * 查询股票信息
     * @param stocksLink stocksLink
     */
    private void queryStocks(Link stocksLink) {
        ResponseEntity<PagedResources<Resource<StocksEntity>>> stocksResp = restTemplate.exchange(stocksLink.getTemplate().expand(), HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<Resource<StocksEntity>>>() {
        });
        if (null != stocksResp.getBody() && null != stocksResp.getBody().getContent()) {
            StringBuffer strs = new StringBuffer();
            stocksResp.getBody().getContent().forEach((s) -> {
                strs.append(s.getContent().getName()).append(":").append(s.getContent().getPrice()).append(",");
            });
            String resp = strs.toString().replaceAll(",$", "");
            log.info("query stocks ==>" + resp);
        }else {
            log.info(" query stocks ==> empty ");
        }
    }

    /**
     * 更新股票信息
     * @param link link
     * @return stocks
     */
    private Resource<StocksEntity> updateStocks(Link link) {
        StocksEntity americano = StocksEntity.builder()
                .name("中国平安")
                .price(68.9)
                .build();
        RequestEntity<StocksEntity> req = RequestEntity.put(link.getTemplate().expand()).body(americano);
        ResponseEntity<Resource<StocksEntity>> resp = restTemplate.exchange(req, new ParameterizedTypeReference<Resource<StocksEntity>>() {
        });
        log.info("add Stocks ==> {}", resp);
        return resp.getBody();
    }
}
