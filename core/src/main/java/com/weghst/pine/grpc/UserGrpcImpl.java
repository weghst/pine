package com.weghst.pine.grpc;

import com.weghst.pine.UserGrpc;
import com.weghst.pine.UserRep;
import com.weghst.pine.UserReq;
import com.weghst.pine.service.UserService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
public class UserGrpcImpl implements UserGrpc.User {

    @Autowired
    private UserService userService;

    @Override
    public void save(UserReq request, StreamObserver<UserRep> responseObserver) {

    }
}
