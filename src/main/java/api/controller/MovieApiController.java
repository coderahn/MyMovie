package api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller //의존성 spring-webmvc
public class MovieApiController {
    private static Logger log = LoggerFactory.getLogger(MovieApiController.class);

    @Value("${clientId}")
    private String clientId;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${apiUrl}")
    private String apiUrl;

    @RequestMapping("/main")
    public String main() {
        log.debug("main page start");

        return "/movie/main";
    }


    /**
     * 영화API로 리스트 조회
     * TODO : 예외처리, 파라미터(검색어) 처리
     * @param query
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/movie-list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String movieList(String query) throws Exception {
        log.debug("영화리스트 가져오기");

        String getParam = "?query=dumpling";
        URL url = new URL(apiUrl + getParam);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();

        huc.setRequestMethod("GET");
        huc.setRequestProperty("X-Naver-Client-Id", clientId);
        huc.setRequestProperty("X-Naver-Client-Secret", secretKey);

        InputStream in = huc.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buf = new byte[1024*8];
        int length = 0;

        while ((length = in.read(buf)) != -1) {
            out.write(buf, 0, length);
        }

        String result = new String(out.toByteArray(), "UTF-8");
        System.out.println(result);
        return result;
    }
}
