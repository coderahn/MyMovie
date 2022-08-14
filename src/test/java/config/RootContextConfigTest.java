package config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
//WEB-INF접근 2가지 방법(file, classpath)
//1)@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/root-context.xml"})
//2)@ContextConfiguration(locations={"classpath:WEB-INF/root-context.xml"})
//pom.xml에 testReources 설정으로 간결하게 사용
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class RootContextConfigTest {

    @Autowired
    DriverManagerDataSource dataSource;

    @Test
    public void DataSource_생성여부체크() {
        Assert.notNull(dataSource);
    }
}
