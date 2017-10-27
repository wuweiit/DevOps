package com.wuweiit.devops;
/**
 * Created by marker on 2017/10/27.
 */

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 启动类
 *
 * @author marker
 *  2017-10-27 下午3:40
 **/
public class Main {

    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(Main.class);


    /**
     * 启动
     * @param args 参数
     */
    public static void main(String[] args) {
        logger.info("启动DepOps");



        // 服务器的监听端口
        Server server = new Server(8088);
        // 关联一个已经存在的上下文
        WebAppContext context = new WebAppContext();
        // 设置描述符位置
        context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
        // 设置Web内容上下文路径
        context.setResourceBase("./src/main/webapp");
        // 设置上下文路径
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);


        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server is  start");


    }



}
