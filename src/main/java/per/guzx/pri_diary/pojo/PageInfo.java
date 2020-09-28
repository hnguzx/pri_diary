package per.guzx.pri_diary.pojo;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageInfo<T> {

    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 结果集
     */
    private List<T> result;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null || currentPage < 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        this.pages = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
    }

    public int getPages() {
        return pages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
