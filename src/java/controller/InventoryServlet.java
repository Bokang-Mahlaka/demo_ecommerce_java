package controller;

import dao.InventoryDAO;
import model.Inventory;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle inventory management for admin users.
 */
@WebServlet("/admin/inventory")
public class InventoryServlet extends HttpServlet {

    private InventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        inventoryDAO = new InventoryDAO();
    }

    private boolean isAdmin(User user) {
        // Assuming roleId 1 corresponds to admin
        return user != null && user.getRoleId() == 1;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (!isAdmin(user)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            List<Inventory> inventoryList = inventoryDAO.getAllInventory();
            request.setAttribute("inventoryList", inventoryList);
            request.getRequestDispatcher("/adminInventory.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving inventory", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (!isAdmin(user)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int stockLevel = Integer.parseInt(request.getParameter("stockLevel"));

        try {
            switch (action) {
                case "add":
                    inventoryDAO.addInventory(new Inventory(productId, stockLevel));
                    break;
                case "update":
                    inventoryDAO.updateInventory(new Inventory(productId, stockLevel));
                    break;
                case "remove":
                    inventoryDAO.deleteInventory(productId);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error updating inventory", e);
        }

        response.sendRedirect(request.getContextPath() + "/admin/inventory");
    }
}
