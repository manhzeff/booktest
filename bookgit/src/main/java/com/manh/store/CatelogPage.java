package com.manh.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/catelog-page")
public class CatelogPage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();    
        Map<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        
        List<Product> products = ProductService.getInstance().getAllProducts();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(docType + "<HTML>\n" +
            "<HEAD><TITLE>Catalog Page</TITLE></HEAD>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" + 
            "<BODY BGCOLOR=\"#FDF5E6\">\n");         
        out.println("<ul>");
        for (Product product : products) {
            out.println("<div class='book'>");
            out.println("<h3>" + product.getName() + "</h3>");
            out.println("<p>Category: " + product.getCategory() + "</p>");
            out.println("<p>Description: " + product.getDescription() + "</p>");
            out.println("<p>Price: $" + product.getPrice() + "</p>");
            out.println("<form action=\"/booktest/cart\" METHOD=\"post\">");
            out.println("<input type='hidden' name='productId' value='" + product.getId() + "'>");
            out.println("<input type='submit' value='Add to Cart'>");
            out.println("</form>");
            out.println("</div>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");        
    }
}
