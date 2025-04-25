package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.SQLException;
import java.util.List;


import model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle product listing, filtering, sorting, and adding.
 */
@WebServlet("/products")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
                 maxFileSize = 1024 * 1024 * 10,      // 10 MB
                 maxRequestSize = 1024 * 1024 * 15)   // 15 MB
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check login
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Removed role check to allow all logged-in users to see products
        // model.User user = (model.User) session.getAttribute("user");
        // if (user.getRoleId() != 1) {
        //     response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        //     return;
        // }

        // Retrieve filter parameters
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");

        try {
            List<Product> products = productDAO.getFilteredProducts(type, size, color, priceRange, sortBy);

            // Get distinct filter values for dropdowns
            List<String> types = productDAO.getDistinctTypes();
            List<String> sizes = productDAO.getDistinctSizes();
            List<String> colors = productDAO.getDistinctColors();

            request.setAttribute("products", products);
            request.setAttribute("types", types);
            request.setAttribute("sizes", sizes);
            request.setAttribute("colors", colors);

            // If priceRange filter applied but no products found, set message
            if (priceRange != null && !priceRange.isEmpty() && products.isEmpty()) {
                request.setAttribute("filterMessage", "No products found in the specified price range.");
            }

            request.getRequestDispatcher("/productCatalog.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving products", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Existing add product logic
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            String priceStr = request.getParameter("price");
            String stockStr = request.getParameter("stock");
            String currency = request.getParameter("currency");
            String ecoFriendlyStr = request.getParameter("ecoFriendly");

            double price = 0;
            int stock = 0;
            boolean ecoFriendly = false;

            try {
                price = Double.parseDouble(priceStr);
                stock = Integer.parseInt(stockStr);
                ecoFriendly = Boolean.parseBoolean(ecoFriendlyStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid number format for price or stock.");
                request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
                return;
            }

            Part filePart = request.getPart("imageFile");
            String fileName = null;
            String imageUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File file = new File(uploadDir, fileName);
                filePart.write(file.getAbsolutePath());
                imageUrl = "images/products/" + fileName;
            }

            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setType(type);
            newProduct.setSize(size);
            newProduct.setColor(color);
            newProduct.setPrice(price);
            newProduct.setStock(stock);
            newProduct.setCurrency(currency);
            newProduct.setImageUrl(imageUrl);
            newProduct.setEcoFriendly(ecoFriendly);

            try {
                productDAO.addProduct(newProduct);
                response.sendRedirect("adminProductManagement.jsp?success=true");
            } catch (SQLException e) {
                request.setAttribute("error", "Error adding product: " + e.getMessage());
                request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            // Update product logic
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            String priceStr = request.getParameter("price");
            String stockStr = request.getParameter("stock");
            String currency = request.getParameter("currency");
            String ecoFriendlyStr = request.getParameter("ecoFriendly");

            int id = 0;
            double price = 0;
            int stock = 0;
            boolean ecoFriendly = false;

            try {
                id = Integer.parseInt(idStr);
                price = Double.parseDouble(priceStr);
                stock = Integer.parseInt(stockStr);
                ecoFriendly = Boolean.parseBoolean(ecoFriendlyStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid number format for ID, price or stock.");
                request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
                return;
            }

            Part filePart = request.getPart("imageFile");
            String fileName = null;
            String imageUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File file = new File(uploadDir, fileName);
                filePart.write(file.getAbsolutePath());
                imageUrl = "images/products/" + fileName;
            }

            Product updatedProduct = new Product();
            updatedProduct.setId(id);
            updatedProduct.setName(name);
            updatedProduct.setType(type);
            updatedProduct.setSize(size);
            updatedProduct.setColor(color);
            updatedProduct.setPrice(price);
            updatedProduct.setStock(stock);
            updatedProduct.setCurrency(currency);
            updatedProduct.setEcoFriendly(ecoFriendly);
            if (imageUrl != null) {
                updatedProduct.setImageUrl(imageUrl);
            } else {
                // Keep existing image URL if no new image uploaded
                try {
                    Product existingProduct = productDAO.getProductById(id);
                    if (existingProduct != null) {
                        updatedProduct.setImageUrl(existingProduct.getImageUrl());
                    }
                } catch (SQLException e) {
                    // Log or handle error
                }
            }

            try {
                productDAO.updateProduct(updatedProduct);
                response.sendRedirect("adminProductManagement.jsp?success=true");
            } catch (SQLException e) {
                request.setAttribute("error", "Error updating product: " + e.getMessage());
                request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            // Delete product logic
            String idStr = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(idStr);
                productDAO.deleteProduct(id);
                response.sendRedirect("adminProductManagement.jsp?success=true");
            } catch (NumberFormatException | SQLException e) {
                request.setAttribute("error", "Error deleting product: " + e.getMessage());
                request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
            }
        } else {
            // Unknown action
            request.setAttribute("error", "Unknown action: " + action);
            request.getRequestDispatcher("/adminProductManagement.jsp").forward(request, response);
        }
    }
}
