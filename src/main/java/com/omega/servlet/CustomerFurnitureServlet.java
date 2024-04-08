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

        // 逻辑操作
        Page<Furniture> page = furnitureService.pageFurniture(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        request.setAttribute("furniturePage", page);
        request.getRequestDispatcher(CUSTOMER_FURNITURE_PATH).forward(request, response);
    }
}
