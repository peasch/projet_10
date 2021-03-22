package com.library.batch.service;

import com.library.batch.Beans.BorrowingBean;
import com.library.batch.Beans.UserBean;
import com.library.batch.mails.EmailService;
import com.library.batch.proxies.BatchProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Set;

@Service
@EnableScheduling
@EnableAsync
public class ScheduledTasks {
    @Autowired
    private BatchProxy batchProxy;
    @Autowired
    private EmailService emailService;

    private static final String BATCH="batch";
    private static final String BATCHPWD="batch";

    @Scheduled(fixedDelay =4000)
  /*  @Scheduled(cron="0 0 11 * * *")*/
    public void scheduled (){
        UserBean batchUser = new UserBean();
        batchUser.setUserName(BATCH);
        batchUser.setPassword(BATCHPWD);
        Set<BorrowingBean> borrowings =batchProxy.findAllTooLateBorrowings(batchProxy.login(batchUser));
        for (BorrowingBean borrowingBean : borrowings){
            emailService.send(borrowingBean.getUser().getEmail(),"NE PAS REPONDRE retard","votre livre est en retard, pensez à le rendre, ou à prolonger la durée si vous le pouvez.");
            System.out.println(borrowingBean.getUser().getEmail());
        }
    }
}
