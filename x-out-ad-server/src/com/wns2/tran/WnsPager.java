package com.wns2.tran;

import java.util.Map;

/**
 * 《分页》实现类
 * 
 * @author 
 */
public final class WnsPager
{
    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * 每页显示的行数
     */
    private int pageSize    = 8;

    /**
     * 当前页在数据库中的起始行
     */
    private int startRow;

    /**
     * 总行数
     */
    private int totalRows;

    /**
     * 构造方法
     */
    public WnsPager()
    {
    }

    /**
     * 构造方法
     * 
     * @param totalRows
     */
    public WnsPager(int totalRows)
    {
        this.totalRows = totalRows;
    }

    /**
     * 构造方法
     * 
     * @param totalRows
     * @param currentPage
     */
    public WnsPager(int totalRows, int currentPage)
    {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
    }

    /**
     * 构造方法
     * 
     * @param totalRows
     * @param currentPage
     * @param pageSize
     */
    public WnsPager(int totalRows, int currentPage, int pageSize)
    {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        refresh();
    }

    /**
     * 当前页
     * 
     * @return 当前页
     */
    public int getCurrentPage()
    {
        return currentPage;
    }

    /**
     * 分页大小
     * 
     * @return 分页大小
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * 当前页在数据库中的起始行
     * 
     * @return 起始行
     */
    public int getStartRow()
    {
        return startRow;
    }

    /**
     * 总记录数
     * 
     * @return 总记录数
     */
    public int getTotalRows()
    {
        return totalRows;
    }

    /**
     * 重新计算
     * 
     * @return 返回true表示未计算，否则已计算
     */
    public boolean refresh()
    {
        if (this.totalRows == 0)
        {
            return true;
        }
        if (currentPage < 1)
        {
            currentPage = 1;
        }

        startRow = (currentPage - 1) * pageSize;
        return false;
    }

    /**
     * 设置当前页
     * 
     * @param currentPage
     */
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;

        refresh();
    }

    /**
     * 设置当前页
     * 
     * @param currentPage
     */
    public void setCurrentPage(String currentPage)
    {
        if (currentPage != null && currentPage.matches("([\\d]+)"))
        {
            this.currentPage = Integer.parseInt(currentPage);
        }

        refresh();
    }

    /**
     * 设置分页大小
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;

        refresh();
    }

    /**
     * 设置分页大小
     * 
     * @param pageSize
     */
    public void setStartRow(int currentPage, int pagerSize)
    {
        this.startRow = (currentPage - 1) * pagerSize;

        refresh();
    }

    /**
     * 设置总行数
     * 
     * @param totalRows
     */
    public void setTotalRows(int totalRows)
    {
        this.totalRows = totalRows;

        refresh();
    }

    /**
     * 
     * @param request
     * @param totalRows
     * @return Pager对象
     */
    public static WnsPager getPager(final Map<String, Object> hm)
    {
        Object s = hm.get("currentpage");
        int currentPage = 0;
        if (s != null && s.toString().matches("[\\d]+"))
        {
            currentPage = Integer.parseInt(s.toString());
        }
        s = hm.get("totalRows");
        int totalRows = 0;
        if (s != null && s.toString().matches("[\\d]+"))
        {
            totalRows = Integer.parseInt(s.toString());
        }
        s = hm.get("pagesize");
        int pageSize = 0;
        if (s != null && s.toString().matches("[\\d]+"))
        {
            pageSize = Integer.parseInt(s.toString());
        }
        if (currentPage > 0 || totalRows > 0 || pageSize > 0)
        {
            WnsPager pager = new WnsPager(totalRows, currentPage);
            if (pageSize == 0)
            {
                pageSize = 20;
            }
            pager.setPageSize(pageSize);
            return pager;
        }
        return null;
    }

    /**
     * 
     * 
     * @param totalRows
     * @return Pager对象
     */
    public static WnsPager getPager(int totalRows)
    {
        WnsPager pager = new WnsPager(totalRows);
        pager.refresh();

        return pager;
    }

    /**
     * 
     * @param totalRows
     * @param pageSize
     * @return Pager对象
     */
    public static WnsPager getPager(int totalRows, int pageSize)
    {
        WnsPager pager = new WnsPager(totalRows);
        pager.setPageSize(pageSize);

        return pager;
    }
}
