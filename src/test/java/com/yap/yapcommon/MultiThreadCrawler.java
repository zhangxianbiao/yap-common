package com.yap.yapcommon;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.*;

public class MultiThreadCrawler {

    @Test
    public void getSomePicWithMultiThread() throws Exception{
        String url = "https://www.sohu.com/a/446005764_423129";

        final Document document = Jsoup.parse(new URL(url), 10000);

        final Element imgContent = document.getElementById("mp-editor");

        final Elements imgs = imgContent.getElementsByTag("img");


        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,  // 核心线程数量,线程池大小
                10,// 线程池的最大容量
                1L, // 线程存活时间，大于corePoolSize的线程的存活时间
                TimeUnit.SECONDS,// 存活时间的单位
                new ArrayBlockingQueue<>(100),// 等待队列，存储等待执行的任务
                Executors.defaultThreadFactory(),// 线程工厂，用来创建线程对象
                new ThreadPoolExecutor.AbortPolicy()// 拒绝策略，当等待队列满的时候新加入任务时的策略,AbortPolicy会直接抛出异常
        );

        try {
            int index = 0;
            for (Element img: imgs) {
                final String attr = img.attr("data-src");
                String realUrl = decrypt(attr);

                if (StringUtils.isNotBlank(realUrl)) {
                    index ++;
                    final Integer finalIndex = index;
                    TimeUnit.MILLISECONDS.sleep(10);
                    //TimeUnit.SECONDS.sleep(1);
                    // 提交线程池执行，并发提高效率
                    threadPoolExecutor.execute(() -> downloadImg(realUrl, finalIndex));
                }
            }
            // 提交完成后，必须关闭线程池(会等待所有线程执行完毕才会关闭)，awaitTermination才有用
            threadPoolExecutor.shutdown();
        } catch (Exception e) {
            threadPoolExecutor.shutdown();
            e.printStackTrace();
        }

        // 主线程必须等待线程池执行完毕，否则主线程结束，线程池还在执行就会退出程序
        // 最多等5分钟
        threadPoolExecutor.awaitTermination(5, TimeUnit.MINUTES);
        System.out.println("所有图片下载完成");
    }

    public void downloadImg(String url, Integer index) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            URL targetUrl = new URL(url);
            inputStream = targetUrl.openConnection().getInputStream();

            // 输出流
            fileOutputStream = new FileOutputStream("/Users/zhangxianbiao/temp/" + index + ".jpeg");

            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                fileOutputStream.write(temp);
            }
            System.out.println("图片" + index + "下载完毕...");
            fileOutputStream.close();
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 可以直接在try上new stream，就不用这么写了
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String decrypt(String urlContent) {
        // 使用AES-128 ECB模式加密，AES默认的最简单的加密方式(不建议使用)
        // 然后使用base64编码
        //String urlContent =  "8MY9RGaCWaBsd6kkgG/pScmJ5ULT+qz1crB9Zo+mUNP08Ph6ptsqtz/h4h/aJiIJICHpPhqAjr0/1XdwJKbXie14JiYMqwrpivnhpeHDWTI=";

        // 解密文章来源 https://blog.csdn.net/qqjinxiaolei/article/details/123942803
        final String keyPass = "www.sohu.com6666";
        final SecretKeySpec secretKey = new SecretKeySpec(keyPass.getBytes(StandardCharsets.UTF_8), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");// 默认AES-128 ECB 模式
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // 先使用Base64解码并转成byte数组
            final byte[] decode = Base64.getDecoder().decode(urlContent);

            // 解密
            final byte[] bytes = cipher.doFinal(decode);
            return new String(bytes);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return "";
    }
}
