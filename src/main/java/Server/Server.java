package Server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    public void runServer() throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress("192.168.1.44", 8080), 0);

        server.createContext("/ReciptizerServer", new ServerHttpHandler());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);

        server.start();
    }
}