package com.example.blogs.mapper;

import com.example.blogs.domain.po.AttuserPO;
import com.example.blogs.domain.po.UserPO;
import com.example.blogs.domain.po.UserinfoPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AttUserMapper {

    /**
     * 查询用户关注用户的数目
     * @param userid
     * @return
     */
    @Select("select count(user_atted_id) from attuser where user_att_id = #{userid}")
    Integer queryAttSum(String userid);


    /**
     * 查看用户被关注的数目
     * @param userid
     * @return
     */
    @Select("select count(user_att_id) from attuser where user_atted_id = #{userid}")
    Integer queryAttedSum(String userid);

    /**
     * 查看用户关注的用户的详细信息
     * @param userid
     * @return
     */
    @Select("select user_id,user_name,user_sex,user_real_name from users,attuser where user_atted_id = user_phone and user_att_id = #{userid}")
    List<UserPO> queryattUser(String userid);

    /**
     * 查看被关注的用户的详细信息
     * @param userid
     * @return
     */
    @Select("select user_id,user_name,user_sex,user_real_name from users,attuser where user_att_id = user_phone and user_atted_id = #{userid}")
    List<UserPO> queryattedUser(String userid);

    /**
     * 增加关注的用户
     * @param attuserPO
     * @return
     */
    @Insert("insert into attuser(user_att_id,user_atted_id)values(#{userAttId},#{userAttedId})")
    Boolean addAttUser(AttuserPO attuserPO);


    /**
     * 删除关注的用户
     * @param attuserPO
     * @return
     */
    @Delete("delete from attuser where user_att_id = #{userAttId} and user_atted_id = #{userAttedId}")
    Boolean deleteAttUser(AttuserPO attuserPO);

}
