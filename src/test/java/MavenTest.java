/**
 * Created by marker on 2017/10/27.
 */
import org.junit.Test;

import java.io.*;

/**
 *
 * Maven 单元测试
 *
 * @author marker
 * @create 2017-10-27 下午2:08
 **/
public class MavenTest {


    String filePath = "/Users/marker/Desktop/work/git/directory";


    String MavenHome = "/DATA/buildtools/apache-maven-3.3.9";
    @Test
    public void test() throws IOException {
        String mvn = MavenHome+"/bin/mvn";

        Runtime runtime =  Runtime.getRuntime();
        String [] cmd={"/bin/sh","-c", mvn + " install -Dmaven.test.skip=true"};

        Process process = runtime.exec(cmd, null,  new File(filePath));

        // 取得命令结果的输出流
        InputStream fis=process.getInputStream();
        // 用一个读输出流类去读
        InputStreamReader isr=new InputStreamReader(fis);
        // 用缓冲器读行
        BufferedReader br=new BufferedReader(isr);
        String line = null;
        // 直到读完为止
        while((line=br.readLine())!=null)
        {
            System.out.println(line);
        }


    }


}
