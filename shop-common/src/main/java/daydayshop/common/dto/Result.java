package daydayshop.common.dto;

import java.util.List;

//封装分页请求响应参数类
public class Result<T> {
    //符合条件的总记录数
    private int total;
    //当前页的记录集合
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
