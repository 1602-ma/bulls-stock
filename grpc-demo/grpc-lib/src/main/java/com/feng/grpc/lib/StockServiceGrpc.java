package com.feng.grpc.lib;

import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

import javax.annotation.Generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * @author f
 * @date 2022/10/22 11:45
 */
@Generated(value = "by gRPC proto compiler (version 1.10.0)", comments = "Source: StockService.proto")
public final class StockServiceGrpc {



    private StockServiceGrpc() {}

    public static final String SERVICE_NAME = "StockService";

    // Static method descriptors that strictly reflect the proto.
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
    @java.lang.Deprecated // Use {@link #getGetStockPriceMethod()} instead. 
    public static final io.grpc.MethodDescriptor<com.feng.grpc.lib.StockServiceRequest,
            com.feng.grpc.lib.StockServiceReply> METHOD_GET_STOCK_PRICE = getGetStockPriceMethodHelper();

    private static volatile io.grpc.MethodDescriptor<com.feng.grpc.lib.StockServiceRequest,
            com.feng.grpc.lib.StockServiceReply> getGetStockPriceMethod;

    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
    public static io.grpc.MethodDescriptor<com.feng.grpc.lib.StockServiceRequest,
            com.feng.grpc.lib.StockServiceReply> getGetStockPriceMethod() {
        return getGetStockPriceMethodHelper();
    }

    private static io.grpc.MethodDescriptor<com.feng.grpc.lib.StockServiceRequest,
            com.feng.grpc.lib.StockServiceReply> getGetStockPriceMethodHelper() {
        io.grpc.MethodDescriptor<com.feng.grpc.lib.StockServiceRequest, com.feng.grpc.lib.StockServiceReply> getGetStockPriceMethod;
        if ((getGetStockPriceMethod = StockServiceGrpc.getGetStockPriceMethod) == null) {
            synchronized (StockServiceGrpc.class) {
                if ((getGetStockPriceMethod = StockServiceGrpc.getGetStockPriceMethod) == null) {
                    StockServiceGrpc.getGetStockPriceMethod = getGetStockPriceMethod =
                            io.grpc.MethodDescriptor.<com.feng.grpc.lib.StockServiceRequest, com.feng.grpc.lib.StockServiceReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(
                                            "StockService", "GetStockPrice"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.feng.grpc.lib.StockServiceRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.feng.grpc.lib.StockServiceReply.getDefaultInstance()))
                                    .setSchemaDescriptor(new StockServiceMethodDescriptorSupplier("GetStockPrice"))
                                    .build();
                }
            }
        }
        return getGetStockPriceMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static StockServiceStub newStub(io.grpc.Channel channel) {
        return new StockServiceStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static StockServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new StockServiceBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static StockServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new StockServiceFutureStub(channel);
    }

    /**
     * <pre>
     * 定义Grpc接口
     * </pre>
     */
    public static abstract class StockServiceImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * 定义股票价格访问的接口
         * </pre>
         */
        public void getStockPrice(com.feng.grpc.lib.StockServiceRequest request,
                                  io.grpc.stub.StreamObserver<com.feng.grpc.lib.StockServiceReply> responseObserver) {
            asyncUnimplementedUnaryCall(getGetStockPriceMethodHelper(), responseObserver);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getGetStockPriceMethodHelper(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.feng.grpc.lib.StockServiceRequest,
                                            com.feng.grpc.lib.StockServiceReply>(
                                            this, METHODID_GET_STOCK_PRICE)))
                    .build();
        }
    }

    /**
     * <pre>
     * 定义Grpc接口
     * </pre>
     */
    public static final class StockServiceStub extends io.grpc.stub.AbstractStub<StockServiceStub> {
        private StockServiceStub(io.grpc.Channel channel) {
            super(channel);
        }

        private StockServiceStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StockServiceStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            return new StockServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         * 定义股票价格访问的接口
         * </pre>
         */
        public void getStockPrice(com.feng.grpc.lib.StockServiceRequest request,
                                  io.grpc.stub.StreamObserver<com.feng.grpc.lib.StockServiceReply> responseObserver) {
            ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getGetStockPriceMethodHelper(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * <pre>
     * 定义Grpc接口
     * </pre>
     */
    public static final class StockServiceBlockingStub extends io.grpc.stub.AbstractStub<StockServiceBlockingStub> {
        private StockServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private StockServiceBlockingStub(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StockServiceBlockingStub build(io.grpc.Channel channel,
                                                 io.grpc.CallOptions callOptions) {
            return new StockServiceBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * 定义股票价格访问的接口
         * </pre>
         */
        public com.feng.grpc.lib.StockServiceReply getStockPrice(com.feng.grpc.lib.StockServiceRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetStockPriceMethodHelper(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * 定义Grpc接口
     * </pre>
     */
    public static final class StockServiceFutureStub extends io.grpc.stub.AbstractStub<StockServiceFutureStub> {
        private StockServiceFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private StockServiceFutureStub(io.grpc.Channel channel,
                                       io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StockServiceFutureStub build(io.grpc.Channel channel,
                                               io.grpc.CallOptions callOptions) {
            return new StockServiceFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * 定义股票价格访问的接口
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.feng.grpc.lib.StockServiceReply> getStockPrice(
                com.feng.grpc.lib.StockServiceRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetStockPriceMethodHelper(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_STOCK_PRICE = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final StockServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(StockServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_STOCK_PRICE:
                    serviceImpl.getStockPrice((com.feng.grpc.lib.StockServiceRequest) request,
                            (io.grpc.stub.StreamObserver<com.feng.grpc.lib.StockServiceReply>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class StockServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        StockServiceBaseDescriptorSupplier() {}

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return com.feng.grpc.lib.StockServiceProto.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("StockService");
        }
    }

    private static final class StockServiceFileDescriptorSupplier
            extends StockServiceBaseDescriptorSupplier {
        StockServiceFileDescriptorSupplier() {}
    }

    private static final class StockServiceMethodDescriptorSupplier
            extends StockServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        StockServiceMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (StockServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new StockServiceFileDescriptorSupplier())
                            .addMethod(getGetStockPriceMethodHelper())
                            .build();
                }
            }
        }
        return result;
    }
}
