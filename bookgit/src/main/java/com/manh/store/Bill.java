package com.manh.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkout")
public class Bill extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        displayBill(request, response, cart);
    }

    private void displayBill(HttpServletRequest request, HttpServletResponse response, Map<String, Integer> cart) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US); // Set currency format to US Dollars

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Bill</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">"); 
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Bill</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Name</th><th>Quantity</th><th>Total Price</th></tr>");
        double total = 0;
        ProductService productService = ProductService.getInstance();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productService.getProductById(productId);
            if (product != null) {
                double price = product.getPrice();
                double subTotal = price * quantity;
                total += subTotal;
                out.println("<tr><td>" + product.getName() + "</td><td>" + quantity + "</td><td>" + formatter.format(subTotal) + "</td></tr>");
            }
        }
        out.println("<tr><td colspan=\"2\">Total:</td><td>" + formatter.format(total) + "</td></tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
