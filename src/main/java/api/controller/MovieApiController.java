package api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller //의존성 spring-webmvc
public class MovieApiController {
    private static Logger log = LoggerFactory.getLogger(MovieApiController.class);

    @Value("${clientId}")
    private String clientId;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${apiUrl}")
    private String apiUrl;

    @RequestMapping("/")
    public String init() {
        //스프링 시큐리티 로그인시 main 리다이렉트 처리
        return "redirect:/main";
    }

    @RequestMapping("/main")
    public String main() {
        log.debug("main page start");

        return "/movie/main";
    }

    /**
     * 영화API로 리스트 조회
     * TODO : 예외처리, 파라미터(검색어 이외) 처리, api 상태코드별 처리, 로직 서비스단 이동
     * @param title, genre, country
     * @return
     */
    @RequestMapping(value = "/movie-list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String movieList(String title, String genre, String country) {
        log.debug("get Movie Lists");
        String result = "";

        try {
            String queryParameter = "?query=" + URLEncoder.encode(title, "UTF-8") + "&display=30&genre=" + genre + "&country=" + country;

            HttpURLConnection huc = this.apiCall(queryParameter);

            InputStream in = huc.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024*8];
            int length = 0;

            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }

            result = new String(out.toByteArray(), "UTF-8");
        } catch(Exception e) {
            //api call exception
        }

        return result;
    }

    private HttpURLConnection apiCall(String queryParameter) throws IOException {
        URL url = new URL(apiUrl + queryParameter);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();

        huc.setRequestMethod("GET");
        huc.setRequestProperty("X-Naver-Client-Id", clientId);
        huc.setRequestProperty("X-Naver-Client-Secret", secretKey);

        return huc;
    }
}
