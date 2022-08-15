package config;

import api.mapper.ApiDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
//WEB-INF접근 2가지 방법(file, classpath)
//1)@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/root-context.xml"})
//2)@ContextConfiguration(locations={"classpath:WEB-INF/root-context.xml"})
//pom.xml에 testReources 설정으로 간결하게 사용
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class RootContextConfigTest {

    @Autowired
    DriverManagerDataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlFactory;

    @Autowired
    private ApiDao apiDao;

    @Test
    public void DataSource_생성여부테스트() {
        Assert.notNull(dataSource);
    }

    @Test
    public void DB_커넥션테스트() {
        try (Connection c = dataSource.getConnection()) {
            //mysql-connector-java의 버전은 mysql과 맞아야 함 / 안맞을 경우 다음 에러 발생 : Unknown system variable 'query_cache_size'

            System.out.println("Connection Object: " + c);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mybatis_연결테스트() {
        SqlSession session = sqlFactory.openSession();
        System.out.println("세션연결:" + session);
    }

    @Test
    public void sqlSession_insert_테스트() {
        apiDao.insertMember("1234");
    }
}
