package api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiDao {
    private static String ns = "api.mapper.ApiDao.";

    @Autowired
    private SqlSessionTemplate sqlSession;

    public int insertMember(String memberId) {
        return sqlSession.insert(ns + "insertMember", memberId);
    }
}
