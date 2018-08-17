import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {


    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient();
        socketClient.createSocketClient("localhost",8088,"Hi!我是客户端");
    }

    private void createSocketClient(String ip,int port,String cmd){
        Socket socket = null;
        OutputStream os = null;
        PrintWriter pw = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            //客户端创建socket
            socket = new Socket(ip, port);
            //向服务端发送数据
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(cmd);
            pw.flush();
            //关闭输出流
            socket.shutdownOutput();

            //接收服务端的数据
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("***我是客户端，服务器说：" +info+"***");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
                if (pw != null) {
                    pw.close();
                }
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

