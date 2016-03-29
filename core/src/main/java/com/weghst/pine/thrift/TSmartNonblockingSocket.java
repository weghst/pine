package com.weghst.pine.thrift;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自动连接的非阻塞<code>Socket</code>。如果服务器停止或者网络闪断导致连接中断<code>TSmartNonblockingSocket</code>
 * 会在每次调用读/写接口时尝试自动连接，如果手动调用{@link #close()} 方法关闭<code>Socket</code>将会永远关闭自动连接。
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class TSmartNonblockingSocket extends TNonblockingTransport {

    private static final Logger LOG = LoggerFactory.getLogger(TSmartNonblockingSocket.class);

    private boolean autoConnected = true;
    private TNonblockingSocket socket;

    /**
     * 构建自动连接非阻塞<code>Socket</code>实例。
     *
     * @param socket 非阻塞<code>Socket</code>
     */
    public TSmartNonblockingSocket(TNonblockingSocket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("TNonblockingSocket 不能为 null");
        }
        this.socket = socket;
    }

    @Override
    public boolean startConnect() throws IOException {
        return socket.startConnect();
    }

    @Override
    public boolean finishConnect() throws IOException {
        return socket.finishConnect();
    }

    @Override
    public SelectionKey registerSelector(Selector selector, int interests) throws IOException {
        return socket.registerSelector(selector, interests);
    }

    @Override
    public int read(ByteBuffer buffer) throws IOException {
        ensureOpen();
        return socket.read(buffer);
    }

    @Override
    public int write(ByteBuffer buffer) throws IOException {
        ensureOpen();
        return socket.write(buffer);
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
        try {
            ensureOpen();
        } catch (IOException e) {
            throw new TTransportException(e.getMessage(), e);
        }
        return socket.read(buf, off, len);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws TTransportException {
        try {
            ensureOpen();
        } catch (IOException e) {
            throw new TTransportException(e.getMessage(), e);
        }
        socket.write(buf, off, len);
    }

    private void ensureOpen() throws IOException {
        if (!isOpen()) {
            if (!autoConnected) {
                throw new IOException("TSmartNonblockingSocket 已被手动关闭，无法自动重连");
            }

            try {
                socket.startConnect();
            } catch (IOException e) {
                LOG.error("连接[thrift]服务失败 [{}]", socket.getSocketChannel().getRemoteAddress());
                throw e;
            }
        }
    }
}
