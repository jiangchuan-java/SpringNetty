package com.ifeng.fhh.fhhService.event.Account;

import com.ifeng.fhh.fhhService.model.domain.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Des: 账号事件小助手
 *
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
@Component
public class AccountEventHelper {

    private static List<AccountEvent> eventList = new ArrayList<>();

    static {
        AccountEvent[] events =  AccountEvent.values();
        for(AccountEvent event : events){
            eventList.add(event);
        }
    }

    public static List<AbstractAccountEvent> parser(Account updateAccount, Account oldAccount) throws Exception{
        List<AbstractAccountEvent> list = new ArrayList<>();
        for(AccountEvent event : eventList){
            AbstractAccountEvent abstractAccountEvent =  event.checkEvent(updateAccount, oldAccount);
            if(abstractAccountEvent == null){
                continue;
            }
            list.add(abstractAccountEvent);
        }
        return list;
    }


    //public static void main(String[] args) throws Exception{
    //    Account updateAccount = new Account();
    //    Account oldAccount = new Account();
    //    List<AbstractAccountEvent> list = parser(updateAccount, oldAccount);
    //
    //    for(AbstractAccountEvent event : list){
    //        System.out.println(event.getTopic());
    //        System.out.println(event.detail());
    //
    //    }
    //}
}
