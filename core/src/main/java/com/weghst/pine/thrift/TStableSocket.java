package com.weghst.pine.thrift;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class TStableSocket extends TTransport {

    private static final Logger LOG = LoggerFactory.getLogger(TStableSocket.class);

    private boolean autoConnected = true;
    private TSocket socket;

    /**
     * @param socket
     */
    public TStableSocket(TSocket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("TSocket 不能为 null");
        }
        this.socket = socket;
    }

    @Override
    public boolean isOpen() {
        return socket.isOpen();
    }

    @Override
    public void open() throws TTransportException {
        socket.open();
    }

    @Override
    public void close() {
        // 如果手动调用 close 方法则不进行自动重连
        autoConnected = false;

        socket.close();
    }

    @Override
    public int read(byte[] buf, int off, int len) throws TTransportException {
        ensureOpen();
        return socket.read(buf, off, len);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws TTransportException {
        ensureOpen();
        socket.write(buf, off, len);
    }

    @Override
    public void flush() throws TTransportException {
        socket.flush();
    }

    private void ensureOpen() throws TTransportException {
        if (!isOpen()) {
            if (!autoConnected) {
                throw new TTransportException("TStableSocket 已被手动关闭，无法自动重连");
            }

            socket.close();
            try {
                socket.open();
            } catch (TTransportException e) {
                LOG.error("连接[thrift]服务失败 [{}]", socket.getSocket().getInetAddress());
                throw e;
            }
        }
    }
}
