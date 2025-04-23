package controller;

import dao.ProductDAO;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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

        // Retrieve filter parameters
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");

        try {
            List<Product> products = productDAO.getAllProducts(); // For now, get all products

            // TODO: Implement filtering and sorting in DAO or here

            request.setAttribute("products", products);
            request.getRequestDispatcher("/productCatalog.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving products", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle adding new product

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

        // Handle file upload
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
    }
}
