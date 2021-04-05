package com.library.batch.service;

import com.library.batch.Beans.BookBean;
import com.library.batch.Beans.BorrowingBean;
import com.library.batch.Beans.UserBean;
import com.library.batch.Beans.WaitListBean;
import com.library.batch.mails.EmailService;
import com.library.batch.proxies.BatchProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@EnableScheduling
@EnableAsync
public class ScheduledTasks {
    @Autowired
    private BatchProxy batchProxy;
    @Autowired
    private EmailService emailService;

    private static final String BATCH = "batch";
    private static final String BATCHPWD = "batch";

    @Scheduled(fixedDelay = 4000)
    public void scheduled() {
        UserBean batchUser = new UserBean();
        batchUser.setUserName(BATCH);
        batchUser.setPassword(BATCHPWD);
        String token = "bearer " + batchProxy.login(batchUser);
        Set<BorrowingBean> borrowings = batchProxy.findAllTooLateBorrowings(token);
        for (BorrowingBean borrowingBean : borrowings) {
            /*emailService.send(borrowingBean.getUser().getEmail(), "NE PAS REPONDRE retard", "votre livre est en retard, pensez à le rendre, ou à prolonger la durée si vous le pouvez.");*/
//            System.out.println(borrowingBean.getUser().getEmail());
        }
    }

    @Scheduled(fixedDelay = 180000)
    public void waitListChecking() {
        UserBean batchUser = new UserBean();
        LocalDate now = LocalDate.now();
        batchUser.setUserName(BATCH);
        batchUser.setPassword(BATCHPWD);
        String token = "bearer " + batchProxy.login(batchUser);
        List<BookBean> books = batchProxy.checkAvailableBooks(token);
        for (BookBean book : books) {
            if (batchProxy.isWaitListed(book.getId(), token)) {
                WaitListBean waitListBean = batchProxy.checkWaitListOfBook(book.getId(), token);
                if (waitListBean.getContactDate().equals(now)) {
//                    emailService.send(waitListBean.getUser().getEmail(), "Livre disponible", "Le livre que vous avez réservé est disponible, vous avez 48h pour venir le récupérer");
                    System.out.println(waitListBean.getUser().getEmail());
                }else{

                }
            }
        }
    }
}

