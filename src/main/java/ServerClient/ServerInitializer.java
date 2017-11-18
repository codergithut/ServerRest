package ServerClient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    HealthServerHandler healthServerHandler;

    private SslContext sslCtx;

    public SslContext getSslCtx() {
        return sslCtx;
    }

    public void setSslCtx(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new HttpServerCodec());/*HTTP 服务的解码器*/
        p.addLast(new HttpObjectAggregator(2048));/*HTTP 消息的合并处理*/
        p.addLast(healthServerHandler); /*自己写的服务器逻辑处理*/
    }
}