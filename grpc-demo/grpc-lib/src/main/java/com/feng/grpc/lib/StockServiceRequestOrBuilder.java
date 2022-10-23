package com.feng.grpc.lib;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/**
 * @author f
 * @date 2022/10/22 16:20
 */
public interface StockServiceRequestOrBuilder extends MessageOrBuilder {

    /**
     * 股票名称
     * @return name
     */
    String getName();

    /**
     * 股票名称
     * @return name
     */
    ByteString getNameBytes();
}
