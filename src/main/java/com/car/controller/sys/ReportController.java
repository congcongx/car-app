package com.car.controller.sys;

import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.service.sys.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController extends BaseController{

    @Autowired
    private ReportService reportService;

    @GetMapping("/countByDay")
    public Result countByDay(String startDate,String endDate, HttpServletRequest request) throws ParseException {
        Drv drv = super.getDrv();
        List<Map<String, Object>> maps = reportService.countByDay(drv.getDrvCcode(),startDate,endDate);
        return Result.ok(maps);
    }

    @GetMapping("/countByMonth")
    public Result countByMonth(String endDate) throws ParseException {
        Drv drv = super.getDrv();
        List<Map<String, Object>> maps = reportService.countByMonth(drv.getDrvCcode(), endDate);
        return Result.ok(maps);
    }

    @GetMapping("/countByYear")
    public Result countByYear(String endDate, HttpServletRequest request) throws ParseException {
        Drv drv = super.getDrv();
        List<Map<String, Object>> maps = reportService.countByYear(drv.getDrvCcode(),endDate);
        return Result.ok(maps);
    }


    /**
     * 报表聚合信息汇总
     * @param type 汇总类型，day天，month月,year年
     * @return
     */
    @GetMapping("reportSum")
    public Result reportSum(String crtTime,String type) {
        Drv drv = super.getDrv();
        Map<String, Object> map = reportService.countByType(drv.getDrvCcode(), crtTime, type);
        return Result.ok(map);
    }
}
