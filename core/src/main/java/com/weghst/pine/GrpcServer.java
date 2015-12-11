package com.weghst.pine;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class GrpcServer implements InitializingBean, DisposableBean {

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
        System.out.println("Started............................port for " + port);
    }

    @Override
    public void destroy() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
}
