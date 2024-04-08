package com.omega.entity;

import lombok.Data;

import java.util.List;

/**
 * Class Page
 *
 * @author KennySo
 * @date 2024/4/2
 */
@Data
public class Page<T> {

    public static final Integer DEFAULT_PAGE_SIZE = 3;

    // 第几页(当前页)
    private Integer pageNo;
    // 默认分页 3
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    // 总页数
    private Integer totalSize;
    // 总数据数
    private Integer totalRow;
    private List<T> items;
    // 分页导航 (用来存放可变的 url)
    private String url;


    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize, List<T> items, Integer totalSize, String url) {
        this.pageNo = pageNo;
        if (pageSize != 0) {
            this.pageSize = pageSize;
        }
        this.items = items;
        this.totalSize = totalSize;
        this.url = url;
    }
}
