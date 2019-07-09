package com.dingmj.bigmall.admin.service;

import com.alibaba.druid.util.StringUtils;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.domain.BigmallLog;
import com.dingmj.bigmall.db.service.BigmallLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>日志类 分为四种</h1>
 * <p>一般日志:默认日志级别</p> {@code LOG_TYPE_GENERAL}: 0
 * <p>安全日志:用户安全相关的操作：如登录、删除管理员等</p> {@code LOG_TYPE_AUTH}: 1
 * <p>订单日志</p>{@code LOG_TYPE_ORDER}: 2
 * <p>其他日志</p>{@code LOG_TYPE_OTHER}: 3
 * @author DMJ
 * @date 2019-06-29 17:36
 */
@Component
public class LogHelper {
    public final static Integer LOG_TYPE_GENERAL = 0;
    public final static Integer LOG_TYPE_AUTH = 1;
    public final static Integer LOG_TYPE_ORDER = 2;
    public final static Integer LOG_TYPE_OTHER = 3;

    @Autowired
    private BigmallLogService logService;

    public void logGeneralSucceed(String action){
        logAdmin(LOG_TYPE_GENERAL,action,true,"","");
    }

    public void logGeneralSucceed(String action,String result){
        logAdmin(LOG_TYPE_GENERAL,action,true,result,"");
    }

    public void logGeneralFail(String action,String error){
        logAdmin(LOG_TYPE_GENERAL,action,false,error,"");
    }

    public void logAuthSucceed(String action){
        logAdmin(LOG_TYPE_AUTH,action,true,"","");
    }

    public void logAuthSucceed(String action,String result){
        logAdmin(LOG_TYPE_AUTH,action,true,result,"");
    }

    public void logAuthFail(String action,String error){
        logAdmin(LOG_TYPE_AUTH,action,false,error,"");
    }

    public void logOrderSucceed(String action){
        logAdmin(LOG_TYPE_ORDER,action,true,"","");
    }

    public void logOrderSucceed(String action,String result){
        logAdmin(LOG_TYPE_ORDER,action,true,result,"");
    }


    public void logOrderFail(String action,String error){
        logAdmin(LOG_TYPE_ORDER,action,false,error,"");
    }


    public void logOtherSucceed(String action){
        logAdmin(LOG_TYPE_OTHER,action,true,"","");
    }

    public void logOtherSucceed(String action,String result){
        logAdmin(LOG_TYPE_OTHER,action,true,result,"");
    }

    public void logOtherFail(String action,String error){
        logAdmin(LOG_TYPE_OTHER,action,false,error,"");
    }

    public void logAdmin(Integer type, String action,Boolean succeed,String result,String comment){
        BigmallLog log = new BigmallLog();

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null){
            BigmallAdmin admin = (BigmallAdmin) currentUser.getPrincipal();
            if (admin !=null){
                log.setAdmin(admin.getUsername());
            }
            else{
                log.setAdmin("匿名用户");
            }

        }
        else {
            log.setAdmin("匿名用户");
        }

        /**
         * 获取 IP 地址
         */
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        if (request != null){
//            log.setIp();
//        }

        log.setType(type);
        log.setAction(action);
        log.setStatus(succeed);
        log.setResult(result);
        log.setComment(comment);
        logService.add(log);
    }

}
