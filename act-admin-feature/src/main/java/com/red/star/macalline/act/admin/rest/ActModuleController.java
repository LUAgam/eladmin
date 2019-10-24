package com.red.star.macalline.act.admin.rest;

import com.red.star.macalline.act.admin.aop.log.Log;
import com.red.star.macalline.act.admin.domain.ActModule;
import com.red.star.macalline.act.admin.domain.ActSpecLink;
import com.red.star.macalline.act.admin.domain.vo.ActResponse;
import com.red.star.macalline.act.admin.service.ActModuleService;
import com.red.star.macalline.act.admin.service.dto.ActModuleQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AMGuo
 * @date 2019-10-22
 */
@Api(tags = "TbWapActModule管理")
@RestController
@RequestMapping("api")
public class ActModuleController {

    @Autowired
    private ActModuleService actModuleService;

    @Log("查询TbWapActModule")
    @ApiOperation(value = "查询TbWapActModule")
    @GetMapping(value = "/actModule")
    @PreAuthorize("hasAnyRole('ADMIN','TBWAPACTMODULE_ALL','TBWAPACTMODULE_SELECT')")
    public ResponseEntity getTbWapActModules(ActModuleQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(actModuleService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增TbWapActModule")
    @ApiOperation(value = "新增TbWapActModule")
    @PostMapping(value = "/actModule")
    @PreAuthorize("hasAnyRole('ADMIN','TBWAPACTMODULE_ALL','TBWAPACTMODULE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ActModule resources) {
        return new ResponseEntity(actModuleService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改TbWapActModule")
    @ApiOperation(value = "修改TbWapActModule")
    @PutMapping(value = "/actModule")
    @PreAuthorize("hasAnyRole('ADMIN','TBWAPACTMODULE_ALL','TBWAPACTMODULE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ActModule resources) {
        actModuleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除TbWapActModule")
    @ApiOperation(value = "删除TbWapActModule")
    @DeleteMapping(value = "/actModule/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TBWAPACTMODULE_ALL','TBWAPACTMODULE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {
        actModuleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 查询所有活动信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/actInfo")
    public ActResponse findActInfo() {
        List<ActModule> actInfo = actModuleService.findActInfo();
        return ActResponse.buildSuccessResponse("actsInfo", actInfo);
    }

    /**
     * 活动信息添加
     *
     * @param actInfo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/actInfo/add")
    public ActResponse addActInfo(@RequestBody ActModule actInfo) {
        ActResponse actResponse = actModuleService.addActInfo(actInfo);
        return actResponse;
    }
    /**
     * 活动信息修改
     *
     * @param actInfo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/actInfo/save")
    public ActResponse saveActInfo(@RequestBody ActModule actInfo) {
        ActResponse actResponse = actModuleService.saveActInfo(actInfo);
        return actResponse;
    }


    /**
     * 当前活动优先级改变
     *
     * @param actCode
     * @param isDown
     * @return
     */
    @ResponseBody
    @PostMapping("/actInfo/changeLevel")
    public ActResponse changeActInfoLevel(@RequestParam("actCode") String actCode, @RequestParam("isDown") Boolean isDown) {
        ActResponse actResponse = actModuleService.changActInfoLeveL(isDown, actCode);
        return actResponse;
    }

    /**
     * 查询某次活动特殊链接
     *
     * @param actCode
     * @return
     */
    @ResponseBody
    @GetMapping("/{actCode}/actSpecLink")
    public ActResponse findSpecLink(@PathVariable("actCode") String actCode) {
        ActResponse actResponse = actModuleService.findSpecLink(actCode);
        return actResponse;
    }

    /**
     * 某次活动特殊链接新增
     *
     * @param actCode
     * @param actSpecLink
     */
    @ResponseBody
    @PostMapping("/{actCode}/actSpecLink/add")
    public ActResponse addSpecLink(@PathVariable("actCode") String actCode, @RequestBody ActSpecLink actSpecLink) {
        ActResponse actResponse = actModuleService.addSpecLink(actCode, actSpecLink);
        return actResponse;
    }

    /**
     * 特殊链接保存
     *
     * @param actCode
     * @param actSpecLink
     */
    @ResponseBody
    @PostMapping("/{actCode}/actSpecLink/save")
    public ActResponse saveSpecLink(@PathVariable("actCode") String actCode, @RequestBody ActSpecLink actSpecLink) {
        return actModuleService.saveSpecLink(actCode, actSpecLink);
    }

    /**
     * 特殊链接的删除
     *
     * @param actCode
     * @param actSpecLink
     * @return
     */
    @ResponseBody
    @PostMapping("/{actCode}/actSpecLink/delete")
    public ActResponse deleteSpecLink(@PathVariable("actCode") String actCode, @RequestBody ActSpecLink actSpecLink) {
        return actModuleService.deleteSpecLink(actCode, actSpecLink);
    }

}