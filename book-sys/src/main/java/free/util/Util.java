package free.util;

import free.model.Page;

import javax.servlet.http.HttpServletRequest;

public class Util {

    public static Page parse(HttpServletRequest req){
        Page p = new Page();
        //req.getParameter("xxxx")可能是空字符串，也可能没有xxx，获取的就是null
        p.setSearchText(req.getParameter("searchText"));
        p.setSortOrder(req.getParameter("sortOrder"));
        p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        p.setPageNumber(Integer.parseInt(req.getParameter("pageNumber")));
        return p;
    }
}
