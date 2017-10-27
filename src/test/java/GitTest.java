/**
 * Created by marker on 2017/10/27.
 */

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 *
 *
 * 获取Git代码
 *
 *
 * @author marker
 * @create 2017-10-27 下午1:44
 **/
public class GitTest {


    public String remotePath = "https://github.com/wuweiit/demo-activiti.git";//远程库路径



    String filePath = "/Users/marker/Desktop/work/git/directory";

    /**
     * 初始化拉取GIt代码到本地
     *
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void test() throws IOException, GitAPIException {

        // 设置远程服务器上的用户名和密码
//        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
//                UsernamePasswordCredentialsProvider("903595558@qq.com"," ");

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

        Git git= cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch("master") //设置clone下来的分支
                .setDirectory(new File(filePath)) //设置下载存放路径

//                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();

        System.out.print(git.tag());
    }


    /**
     * pull 代码
     *
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void test1() throws IOException, GitAPIException {
        Git git = Git.open(new File(filePath));
        PullCommand pc = git.pull();
        pc.setRemoteBranchName("master");
        pc.call();

    }


    /**
     * 提交代码
     */
    @Test
    public void test2() throws IOException, GitAPIException {
        String url = "https://gitee.com/marker/deply_test.git";


        FileRepositoryBuilder builder = new FileRepositoryBuilder();

        String D = "/Users/marker/Desktop/work/git/directory/target/activiti-1.0-SNAPSHOT";
        Git git = Git.init().setDirectory(new File(D)).call();


        Repository repository = builder.setGitDir(new File( D ))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();



//        Git git = new Git(repository);

        AddCommand add = git.add();
        add.addFilepattern(".").call();//git add .
        CommitCommand commit = git.commit();
//        commit.setCommitter("marker", "admin@wuweibi.com");
//        commit.setAuthor("test","Kingson_Wu@163.com");
        commit.setAll(true);

        RevCommit revCommit = commit.setMessage("项目初始化").call();// git commit -m "use jgit"
        String commitId = revCommit.getId().name();
        System.out.println(commitId);



        PushCommand push = git.push();


        // 设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider("903595558@qq.com","worinima.");

        push.setCredentialsProvider(usernamePasswordCredentialsProvider);
        push.setForce(true);
//        push.setProgressMonitor(new ProgressMonitor() {})

        push.setRemote(url);
        push.call();// git push









    }



}
