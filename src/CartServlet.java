

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //get whole cart
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        JsonArray cartArray = new JsonArray();
        
        HttpSession session = request.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        
        if(cart == null) {
        	cart = new ArrayList<String>();
        }
        
        for(int i = 0; i < cart.size(); i++){
            JsonObject o = new JsonObject();
            o.addProperty("id",cart.get(i));
            cartArray.add(o);
        }
        
        String url = "/ProductsServlet";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.include(request, response);
        
        
        out.print("*" + cartArray.toString());
        out.flush();
        out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //add to cart
		String param = request.getParameter("product");

        HttpSession session = request.getSession(true);
        ArrayList<String> cart  = (ArrayList<String>) session.getAttribute("cart");
        if(cart == null){ //if cart list doesnt exist, create it
            cart = new ArrayList<>();
        }

	    if(cart.contains(param) == false) { //add product to cart if its not already in
	    	cart.add(param);
            session.setAttribute("cart", cart);
	    }
	    
	    response.setContentType("application/json");
        PrintWriter out = response.getWriter();
	    JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", "success");
		out.print(jsonObject);
	}

}
