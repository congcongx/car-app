package com.car.controller.sys;

import com.car.commons.constants.Const;
import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.domain.sys.Mixer;
import com.car.service.sys.DrvService;
import com.car.service.sys.MixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/mixer")
public class MixerController {

    @Autowired
    private MixerService mixerService;
    @Autowired
    private DrvService drvService;
    /**
     * 查询车辆详情，准驾 司机，当班司机
     * @return
     */
    @GetMapping("/findMixerInfo/{mixerId}")
    public Result findMixerInfo(@PathVariable Integer mixerId,
            HttpServletRequest request) {
        Drv drv = (Drv) request.getSession().getAttribute(Const.SESSION_KEY);
        return Result.ok(mixerService.findMixerInfo(drv,mixerId));
    }

    /**
     * 接班
     * @param request
     * @return
     */
    @RequestMapping("/onDuty/{mixerId}")
    public Result onDuty(@PathVariable Integer mixerId, HttpServletRequest request) {
        Drv drv = (Drv) request.getSession().getAttribute(Const.SESSION_KEY);
        Mixer mixer = mixerService.findMixerById(mixerId);
        Drv drvOnduty = drvService.findDrvOndutyByMixerId(mixer.getMixerId());
        if(drvOnduty != null) {
            return Result.ok("当前车辆当班司机为："+ drvOnduty.getDrvName());
        }
        int i = mixerService.onDuty(mixer.getMixerId(), drv.getDrvId());
        if(i > 0) {
            return Result.ok("接班成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 交班
     * @param request
     * @return
     */
    @RequestMapping("/offDuty/{mixerId}")
    public Result offDuty(@PathVariable Integer mixerId, HttpServletRequest request) {
        Drv drv = (Drv) request.getSession().getAttribute(Const.SESSION_KEY);
        Mixer mixer = mixerService.findMixerById(mixerId);
        Drv drvOnduty = drvService.findDrvOndutyByMixerId(mixer.getMixerId());
        if(drvOnduty == null) {
            return Result.ok("当前车辆无当班司机");
        }
        if(!drv.getDrvId().equals(drvOnduty.getDrvId())) {
            return Result.ok("您当前不是当班司机");
        }
        int i = mixerService.offDuty(drv);
        if(i > 0) {
           return Result.ok("交班成功");
        }
        return Result.error("操作失败");
    }


    /**
     * 查看车辆排队
     * @return
     */
    @RequestMapping("/vehicleQueue/{mixerId}")
    public Result vehicleQueue(@PathVariable Integer mixerId, HttpServletRequest request) {
        Drv drv = (Drv) request.getSession().getAttribute(Const.SESSION_KEY);
        return Result.ok(mixerService.findMixerQueue(drv,mixerId));
    }

}
