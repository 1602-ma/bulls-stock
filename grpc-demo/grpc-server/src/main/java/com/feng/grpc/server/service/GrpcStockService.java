package com.feng.grpc.server.service;

import com.feng.grpc.lib.StockServiceGrpc;
import com.feng.grpc.lib.StockServiceReply;
import com.feng.grpc.lib.StockServiceRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

/**
 * @author f
 * @date 2022/10/23 12:42
 */
@GrpcService
public class GrpcStockService extends StockServiceGrpc.StockServiceImplBase {

    /**
     * 获取股票价格接口
     * @param request           request
     * @param responseObserver  Oberver
     */
    @Override
    public void getStockPrice(StockServiceRequest request, StreamObserver<StockServiceReply> responseObserver) {
        String msg = "股票名称： " + request.getName() + ", 股票价格： " + (new Random().nextInt(100- 20) + 20);
        StockServiceReply reply = StockServiceReply.newBuilder().setMessage(msg).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
