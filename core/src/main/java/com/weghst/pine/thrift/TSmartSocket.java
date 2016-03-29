package com.weghst.pine.thrift;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自动连接的阻塞<code>Socket</code>。如果服务器停止或者网络闪断导致连接中断<code>TSmartSocket</code>
 * 会在每次调用读/写接口时尝试自动连接，如果手动调用{@link #close()} 方法关闭<code>Socket</code>将会永远关闭自动连接。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class TSmartSocket extends TTransport {

    private static final Logger LOG = LoggerFactory.getLogger(TSmartSocket.class);

    private boolean autoConnected = true;
    private TSocket socket;

    /**
     * 构建自动连接阻塞<code>Socket</code>实例。
     *
     * @param socket 阻塞<code>Socket</code>
     */
    public TSmartSocket(TSocket socket) {
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
                throw new TTransportException("TSmartSocket 已被手动关闭，无法自动重连");
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
