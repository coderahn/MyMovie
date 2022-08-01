package api.controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

public class MovieApiControllerTest {

    @Test
    public void movieList() throws IOException {
        //given
        String cilentId = "n1NSzTDIYljgEbxM1gSm";
        String secretKey = "pVjPoekqUN";
        String apiUrl = "https://openapi.naver.com/v1/search/movie.json";

        String getParam = "?query=dumpling";
        URL url = new URL(apiUrl + getParam);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();

        huc.setRequestMethod("GET");
        huc.setRequestProperty("X-Naver-Client-Id", cilentId);
        huc.setRequestProperty("X-Naver-Client-Secret", secretKey);

        //when
        System.out.println("호잇:" + huc.getContent());

        //then
        InputStream in = huc.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buf = new byte[1024*8];
        int length = 0;

        while ((length = in.read(buf)) != -1) {
            out.write(buf, 0, length);
        }

        System.out.println(new String(out.toByteArray(), "UTF-8"));



    }

    public static void main(String[] args) throws IOException {
        //given
        String cilentId = "n1NSzTDIYljgEbxM1gSm";
        String secretKey = "pVjPoekqUN";
        String apiUrl = "https://openapi.naver.com/v1/search/movie.json";

        String getParam = "?query=만두";
        URL url = new URL(apiUrl + getParam);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();

        huc.setRequestMethod("GET");
        huc.setRequestProperty("X-Naver-Client-Id", cilentId);
        huc.setRequestProperty("X-Naver-Client-Secret", secretKey);

        //when
        System.out.println("호잇:" + huc.getResponseMessage());

        //then
    }
}