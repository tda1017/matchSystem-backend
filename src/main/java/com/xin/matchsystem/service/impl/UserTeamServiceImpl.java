package com.xin.matchsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.matchsystem.mapper.UserTeamMapper;
import com.xin.matchsystem.model.domain.UserTeam;
import com.xin.matchsystem.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author TDawn
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-03-14 18:52:33
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




