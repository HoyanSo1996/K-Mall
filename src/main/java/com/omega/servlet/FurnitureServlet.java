package com.omega.servlet;

import com.omega.entity.Furniture;
import com.omega.service.FurnitureService;
import com.omega.service.impl.FurnitureServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.omega.util.CommonUtils.FURNITURE_MANAGE_PATH;

/**
 * Class FurnitureServlet
 *
 * @author KennySo
 * @date 2024/3/29
 */
@WebServlet("/furnitureServlet")
public class FurnitureServlet extends BasicServlet {

    private final FurnitureService furnitureService = new FurnitureServiceImpl();

    /**
     * 查询家居列表
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Furniture> furnitureList = furnitureService.listAll();
        request.setAttribute("furnitureList", furnitureList);
        request.getRequestDispatcher(FURNITURE_MANAGE_PATH).forward(request, response);
    }


    /**
     * 添加家居
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Integer sales = Integer.valueOf(request.getParameter("sales"));
        Integer stock = Integer.valueOf(request.getParameter("stock"));
        String imgPath = request.getParameter("imgPath");
        // Todo 验证数据的合法性

        // 封装数据
        Furniture furniture = new Furniture();
        furniture.setName(name);
        furniture.setManufacturer(manufacturer);
        furniture.setPrice(price);
        furniture.setSales(sales);
        furniture.setStock(stock);
        furniture.setImgPath("assets/images/product-image/default.jpg");
        furnitureService.add(furniture);

        // 防止刷新浏览器页面导致重复添加数据, 这里不能用请求转发, 而要用重定向（这里记得把url的 "/" 掉）
        // request.getRequestDispatcher("/furnitureServlet?action=list").forward(request, response);
        response.sendRedirect("furnitureServlet?action=list");
    }
}
