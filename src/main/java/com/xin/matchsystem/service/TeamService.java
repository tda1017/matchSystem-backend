package com.xin.matchsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.matchsystem.model.domain.Team;
import com.xin.matchsystem.model.domain.User;

/**
* @author TDawn
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-03-14 17:27:53
*/
public interface TeamService extends IService<Team> {
    /**
     *   添加队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);
}
