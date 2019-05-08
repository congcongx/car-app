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
import java.util.List;

@RestController
@RequestMapping("/sys/mixer")
public class MixerController extends BaseController{

    @Autowired
    private MixerService mixerService;
    @Autowired
    private DrvService drvService;
    /**
     * 查询车辆详情，准驾 司机，当班司机
     * @return
     */
    @GetMapping("/findMixerInfo/{mixerId}")
    public Result findMixerInfo(@PathVariable Integer mixerId) {
        Drv drv = super.getDrv();
        return Result.ok(mixerService.findMixerInfo(drv,mixerId));
    }

    /**
     * 接班
     * @return
     */
    @RequestMapping("/onDuty/{mixerId}")
    public Result onDuty(@PathVariable Integer mixerId) {
        Drv drv = super.getDrv();
        Mixer mixer = mixerService.findMixerById(mixerId);
        Drv drvOnduty = drvService.findDrvOndutyByMixerId(mixer.getMixerId());
        if(drvOnduty != null) {
            return Result.ok("当前车辆当班司机为："+ drvOnduty.getDrvName());
        }
        List<String> drvOndutyByDrvId = mixerService.findDrvOndutyByDrvId(drv.getDrvId());
        if(drvOndutyByDrvId.size() > 0) {
            return Result.error("您是"+drvOndutyByDrvId.toString()+"号车的当班司机，请先取消再接班");
        }
        mixerService.onDuty(mixer.getMixerId(), drv.getDrvId());

        return Result.ok("接班成功");
    }

    /**
     * 交班
     * @return
     */
    @RequestMapping("/offDuty/{mixerId}")
    public Result offDuty(@PathVariable Integer mixerId) {
        Drv drv = super.getDrv();
        Mixer mixer = mixerService.findMixerById(mixerId);
        Integer status = mixer.getMixerStatus();
        //车辆在制单状态
        if(status==Const.MIXER_LOADWAIT||status==Const.MIXER_LOAD||status==Const.MIXER_DLVTRANS
                ||status==Const.MIXER_SDLVTRANS){
            return Result.error("该送货单生产未结束，不能交班");
        }
        Drv drvOnduty = drvService.findDrvOndutyByMixerId(mixer.getMixerId());
        if(drvOnduty == null) {
            return Result.ok("当前车辆无当班司机");
        }
        if(!drv.getDrvId().equals(drvOnduty.getDrvId())) {
            return Result.ok("您当前不是当班司机");
        }
        int i = mixerService.offDuty(drv.getDrvId(),mixerId);
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
        Drv drv = super.getDrv();
        return Result.ok(mixerService.findMixerQueue(drv,mixerId));
    }


    /**
     * 查询所有生产线信息
     * @return
     */
    @GetMapping("/findAllLine")
    public Result findAllLine() {
        return Result.ok(mixerService.findAllLine());
    }

}
