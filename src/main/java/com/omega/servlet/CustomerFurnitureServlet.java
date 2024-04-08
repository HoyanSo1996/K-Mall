package com.omega.servlet;

import com.omega.entity.Furniture;
import com.omega.entity.Page;
import com.omega.service.FurnitureService;
import com.omega.service.impl.FurnitureServiceImpl;
import com.omega.util.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.omega.util.CommonUtils.CUSTOMER_FURNITURE_PATH;

/**
 * Class CustomerFurnitureServlet
 *
 * @author KennySo
 * @date 2024/4/8
 */
@WebServlet("/customerFurnitureServlet")
public class CustomerFurnitureServlet extends BasicServlet {

    private final FurnitureService furnitureService = new FurnitureServiceImpl();


    public void page(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageNo = request.getParameter("pageNo");
        // 校验数据
        if (!DataUtils.transformStringToInteger(pageNo)) {
            response.sendRedirect(CUSTOMER_FURNITURE_PATH);
            return;
        }
        String pageSize = request.getParameter("pageSize");
        if (pageSize == null || "".equals(pageNo)) {
            pageSize = Page.DEFAULT_PAGE_SIZE.toString();
        }

        // 不管url上有没有name参数, 都设置为"", 防止恶意攻击
        String name = request.getParameter("name");

        Page<Furniture> page;
        if (name == null || name.isEmpty()) {
            // 全查询
            page = furnitureService.pageFurniture(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
            page.setUrl("customerFurnitureServlet?action=page");
        } else {
            // 按名字查询
            page = furnitureService.pageFurnitureByName(Integer.valueOf(pageNo), Integer.valueOf(pageSize), name);
            page.setUrl("customerFurnitureServlet?action=page&name=" + name);
        }

        request.setAttribute("furniturePage", page);
        request.getRequestDispatcher(CUSTOMER_FURNITURE_PATH).forward(request, response);
    }
}
