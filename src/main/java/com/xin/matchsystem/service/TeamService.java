package com.xin.matchsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.matchsystem.model.domain.Team;
import com.xin.matchsystem.model.domain.User;
import com.xin.matchsystem.model.domain.request.TeamJoinRequest;
import com.xin.matchsystem.model.domain.request.TeamUpdateRequest;
import com.xin.matchsystem.model.dto.TeamQuery;
import com.xin.matchsystem.model.vo.TeamUserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    boolean updateTeam(TeamUpdateRequest team, User loginUser);

    boolean joinTeam(TeamJoinRequest teamJoinRequest, User logininUser);

    @Transactional(rollbackFor = Exception.class)
    boolean deleteTeam(long id, User loginUser);
}
