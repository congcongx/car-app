package com.car.controller.sys;

import com.car.commons.constants.Const;
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
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/countByDay")
    public Result countByDay(String date, HttpServletRequest request) throws ParseException {
        Drv drv = (Drv)request.getSession().getAttribute(Const.SESSION_KEY);
        List<Map<String, Object>> maps = reportService.countByDay(drv.getDrvCcode(), date);
        return Result.ok(maps);
    }

    @GetMapping("/countByMonth")
    public Result countByMonth(String date, HttpServletRequest request) throws ParseException {
        Drv drv = (Drv)request.getSession().getAttribute(Const.SESSION_KEY);
        List<Map<String, Object>> maps = reportService.countByMonth(drv.getDrvCcode(), date);
        return Result.ok(maps);
    }


}
