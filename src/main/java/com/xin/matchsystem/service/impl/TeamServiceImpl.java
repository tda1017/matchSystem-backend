package com.xin.matchsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.matchsystem.mapper.TeamMapper;
import com.xin.matchsystem.model.domain.Team;
import com.xin.matchsystem.model.domain.User;
import com.xin.matchsystem.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author TDawn
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2024-03-14 17:27:53
*/
@Service
@Slf4j
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService {

    @Override
    public long addTeam(Team team, User loginUser) {
        return 0;
    }
}




