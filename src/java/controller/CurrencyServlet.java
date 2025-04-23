package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Currency;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to handle currency selection and conversion.
 */
@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    // For demonstration, using in-memory currency list. In real app, connect to DB or external API.
    private List<Currency> currencyList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize supported currencies
        currencyList.add(new Currency("USD", "$", 1.0));
        currencyList.add(new Currency("LSL", "M", 14.5)); // Example conversion rate
        // Add more currencies as needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedCurrency = request.getParameter("currency");
        if (selectedCurrency == null || selectedCurrency.isEmpty()) {
            selectedCurrency = "USD";
        }

        // Set selected currency in session
        HttpSession session = request.getSession();
        session.setAttribute("currency", selectedCurrency);

        // Redirect back to referring page or homepage
        String referer = request.getHeader("referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
