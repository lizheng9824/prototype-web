package net.itfans.prototype.web.demo2.service;

import net.itfans.prototype.web.demo2.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class Demo2DispatchService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private Demo2Biz1Service demo2Biz1Service;

    @Autowired
    private Demo2Biz2Service demo2Biz2Service;


    public void execute(UserEntity userEntyEntity, boolean biz1Commit, boolean biz2Commit) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            demo2Biz1Service.execute();
            if (biz1Commit) {
                transactionManager.commit(status);
            }
        } catch (Exception ex) {
            transactionManager.rollback(status);
        }

        try {
        demo2Biz2Service.execute();
        if (biz2Commit) {
            transactionManager.commit(status);
        }        } catch (Exception ex) {
            transactionManager.rollback(status);
        }

    }

}
