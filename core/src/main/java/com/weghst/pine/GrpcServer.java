package com.weghst.pine;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * gRPC 服务管理.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class GrpcServer implements InitializingBean, DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcServer.class);

    private int port;
    private Server server;

    @Autowired
    private UserGrpc.User userGrpc;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerBuilder serverBuilder = ServerBuilder.forPort(port);
        serverBuilder.addService(UserGrpc.bindService(userGrpc));

        server = serverBuilder.build();
        server.start();

        LOG.info("Started gRPC server port for {}", port);
    }

    @Override
    public void destroy() throws Exception {
        if (server != null) {
            server.shutdown();
        }
        LOG.info("Shutdown gRPC server");
    }

    public void setPort(int port) {
        this.port = port;
    }
}
