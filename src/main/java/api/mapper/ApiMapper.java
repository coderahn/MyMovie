package api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface ApiMapper {
    List<String> selectMember() throws Exception;
}
