package com.feng.client.service;

import com.feng.grpc.lib.StockServiceGrpc;
import com.feng.grpc.lib.StockServiceReply;
import com.feng.grpc.lib.StockServiceRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author f
 * @date 2022/10/22 0:03
 */
@Service
public class GrpcClientService {

    @GrpcClient("grpc-server")
    private StockServiceGrpc.StockServiceBlockingStub stockServiceBlockingStub;

    public String getStockPrice(final String name) {
        try {
            StockServiceRequest request = StockServiceRequest.newBuilder().setName(name).build();
            final StockServiceReply response = stockServiceBlockingStub.getStockPrice(request);
            return response.getMessage();
        } catch (Exception e) {
            return "error!";
        }
    }

}
