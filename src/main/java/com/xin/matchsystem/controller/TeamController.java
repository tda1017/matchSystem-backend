package com.xin.matchsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.matchsystem.common.BaseResponse;
import com.xin.matchsystem.common.ErrorCode;
import com.xin.matchsystem.common.ResultUtils;
import com.xin.matchsystem.exception.BusinessException;
import com.xin.matchsystem.model.domain.Team;
import com.xin.matchsystem.model.domain.User;
import com.xin.matchsystem.model.dto.TeamQuery;
import com.xin.matchsystem.request.TeamAddRequest;
import com.xin.matchsystem.service.TeamService;
import com.xin.matchsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: TDA
 * @date: 2024/3/14 17:22
 * @description:
 */
@RestController
@RequestMapping("/team")
//@CrossOrigin(origins = {"http://localhost:3000/"})
@Slf4j
public class TeamController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private TeamService teamService;

//    @PostMapping("/add")
//    public BaseResponse<Long> addTeam(@RequestBody Team team){
//        if (team == null){
//            throw new BusinessException(ErrorCode.NULL_ERROR);
//        }
//        boolean save = teamService.save(team);
//        if (!save){
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"插入失败");
//        }
//        return ResultUtils.success(team.getId());
//    }

    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest request){
        if (teamAddRequest == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User logininUser = userService.getLogininUser(request);
        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest,team);
        long teamId = teamService.addTeam(team,logininUser);
        return ResultUtils.success(teamId);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody long id){
        if (id <= 0){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        boolean result = teamService.removeById(id);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"删除失败");
        }
        return ResultUtils.success(true);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody Team team){
        if (team == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = teamService.updateById(team);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新失败");
        }
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(long id){
        if (id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = teamService.getById(id);
        if (team == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(team);
    }


    @GetMapping("/list")
    public BaseResponse<List<Team>> listTeams(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = new Team();
        BeanUtils.copyProperties(team, teamQuery);
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>(team);
        List<Team> teamList = teamService.list(queryWrapper);
        return ResultUtils.success(teamList);
    }

    @GetMapping("/list/page")
    public BaseResponse<Page<Team>> listPageTeams(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = new Team();
        BeanUtils.copyProperties(teamQuery, team);
        Page<Team> page = new Page<>(teamQuery.getPageNum(),teamQuery.getPageSize());
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>(team);
        Page<Team> resultPage = teamService.page(page,queryWrapper);
        return ResultUtils.success(resultPage);
    }
}

