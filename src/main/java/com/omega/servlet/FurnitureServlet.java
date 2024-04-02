package com.omega.servlet;

import com.omega.entity.Furniture;
import com.omega.service.FurnitureService;
import com.omega.service.impl.FurnitureServiceImpl;
import com.omega.util.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.omega.util.CommonUtils.*;

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
        String price = request.getParameter("price");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        // 验证数据的合法性
        if (!(DataUtils.transformStringToBigDecimal(price) && DataUtils.transformStringToInteger(sales, stock))) {
            request.setAttribute("error_msg", "数据格式有误...");
            request.getRequestDispatcher(FURNITURE_ADD_PATH).forward(request, response);
            return;
        }

        // 使用BeanUtils封装数据
        Furniture furniture = DataUtils.copyParamToBean(request.getParameterMap(), new Furniture());
        furnitureService.add(furniture);

        // 防止刷新浏览器页面导致重复添加数据, 这里不能用请求转发, 而要用重定向（这里记得把url的 "/" 掉）
        // request.getRequestDispatcher("/furnitureServlet?action=list").forward(request, response);
        response.sendRedirect("furnitureServlet?action=list");
    }


    /**
     * 更新家居时的数据回显
     */
    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        // 验证数据的合法性
        if (!DataUtils.transformStringToInteger(id)) {
            response.sendRedirect("furnitureServlet?action=list");
            return;
        }

        Furniture furniture = furnitureService.getFurnitureById(Integer.valueOf(id));
        request.setAttribute("furniture", furniture);
        request.getRequestDispatcher(FURNITURE_UPDATE_PATH).forward(request, response);
    }


    /**
     * 更新家居信息
     */
    public void modify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String price = request.getParameter("price");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        // 验证数据的合法性
        if (!(DataUtils.transformStringToBigDecimal(price) && DataUtils.transformStringToInteger(sales, stock))) {
            request.setAttribute("error_msg", "数据格式有误...");
            request.getRequestDispatcher("furnitureServlet?action=query&id=" + id).forward(request, response);
            return;
        }

        // 封装数据
        Furniture furniture = DataUtils.copyParamToBean(request.getParameterMap(), new Furniture());
        Boolean flag = furnitureService.modifyFurniture(furniture);
        if (flag) {
            System.out.println("更新成功...");
            response.sendRedirect("furnitureServlet?action=list");
        }
    }


    /**
     * 删除家居
     */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        // 验证数据的合法性
        if (!DataUtils.transformStringToInteger(id)) {
            response.sendRedirect("furnitureServlet?action=list");
            return;
        }

        Furniture furniture = new Furniture();
        furniture.setId(Integer.valueOf(id));
        Boolean flag = furnitureService.removeFurniture(furniture);
        if (flag) {
            System.out.println("删除成功...");
            response.sendRedirect("furnitureServlet?action=list");
        }
    }
}
