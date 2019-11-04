package cn.servant.crm.pojo;

/**
 * 分页参数
 */
public class Page {
    // 总记录数
    private int totalCount;
    // 总页数
    private int totalPages;
    // 每页显示的记录数
    private Integer pageSize;
    // 当前页码
    private Integer pageNow;
    // 进行分页查询时的开始索引
    private int startIndex;

    // 进行排序所根据的字段
    private String sortField;
    // 以升序还是降序
    private String ascOrDesc;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        setStartIndex();
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
        setStartIndex();
    }

    public int getStartIndex() {
        return startIndex;
    }

    private void setStartIndex() {
        if (pageNow != null && pageSize != null) {
            startIndex = ( pageNow - 1 ) * pageSize;
        }
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    public void setAscOrDesc(String ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }
}
