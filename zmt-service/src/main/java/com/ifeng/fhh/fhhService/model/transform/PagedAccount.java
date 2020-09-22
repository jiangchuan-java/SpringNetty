package com.ifeng.fhh.fhhService.model.transform;

import com.ifeng.fhh.fhhService.model.domain.Account;

import java.util.List;

/**
 * 分页是的账号列表
 * <p>
 * Created by licheng1 on 2017/7/6.
 */
public class PagedAccount {

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 页号
     */
    private Integer pageNo;

    /**
     * 页长
     */
    private Integer pageSize;

    /**
     * 当前账号列表
     */
    private List<Account> accountList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
