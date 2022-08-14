package config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//Db 접근 관련만 java config로 사용
@Configuration
//@EnableTransactionManagement
public class RootContextConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        //임시
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mymovie?serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }
}
