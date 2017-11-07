package daydayshop.common.dto;

//封装分页请求参数类
public class Page {
    //当前页的页码
    private int page;
    //每页显示个数
    private int rows;
    //偏移量(当前页第一条记录的索引号)
    private int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        return (page - 1) * rows;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
