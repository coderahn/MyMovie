package config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

//Db 접근 관련만 java config로 사용
@Configuration
//@EnableTransactionManagement
//@MapperScan사용시 DAO를 인터페이스로만 정의 가능(인터페이스DAO에는 @Mapper 추가)
@ComponentScan(basePackages = {"api.mapper"})
public class RootContextConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DriverManagerDataSource dataSource() {
        String resources = "properties/db.properties";
        Properties properties = new Properties();

        try {
            Reader reader = Resources.getResourceAsReader(resources);
            properties.load(reader);
        } catch(Exception e) {
            e.printStackTrace();
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("db.driver"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        //factoryBean.setMapperLocations(applicationContext.getResources("classpath*:**/*.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
