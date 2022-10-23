package com.feng.grpc.lib;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/**
 * @author f
 * @date 2022/10/22 23:05
 */
public final class StockServiceProto {
    private StockServiceProto() {

    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistry) registry) ;
    }

    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_StockServiceRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_StockServiceRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_StockServiceReply_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_StockServiceReply_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        java.lang.String[] descriptorData = {
                "\n\022StockService.proto\"#\n\023StockServiceRequ" +
                        "est\022\014\n\004name\030\001 \001(\t\"$\n\021StockServiceReply\022\017" +
                        "\n\007message\030\001 \001(\t2K\n\014StockService\022;\n\rGetSt" +
                        "ockPrice\022\024.StockServiceRequest\032\022.StockSe" +
                        "rviceReply\"\000B*\n\023com.itcast.grpc.libB\021Sto" +
                        "ckServiceProtoP\001b\006proto3"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        });
        internal_static_StockServiceRequest_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_StockServiceRequest_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_StockServiceRequest_descriptor,
                new java.lang.String[] { "Name", });
        internal_static_StockServiceReply_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_StockServiceReply_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_StockServiceReply_descriptor,
                new java.lang.String[] { "Message", });
    }
}
