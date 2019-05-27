

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Random;


@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String credit_card_statement = "insert into creditcards (id, cardNumber, type, cvv, expiration)"+ 
		 	"values (?, ?, ?, ?, ?)";
	
	String sales_statement = "insert into sale ( productId, quantity, shippingMethod, firstName, lastName,ccId,address, phoneNumber)" + 
		 	"values (?, ?, ?, ?, ?, ?, ?, ?)";
       
	private String username="root";
    private String password="inf124";
    private String loginUrl="jdbc:mysql://centaurus-3.ics.uci.edu:1043/MemeChan"; 
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(loginUrl, username, password);
			
			PreparedStatement stmt = con.prepareStatement(credit_card_statement);

			int ccID = new Random().nextInt(1000);
			
			stmt.setString(1, String.valueOf(ccID));
			stmt.setString(2, request.getParameter("cc_number"));
			stmt.setString(3, request.getParameter("cc_type"));
			stmt.setString(4, request.getParameter("cc_cvv"));
			stmt.setString(5, request.getParameter("cc_exp_month") + request.getParameter("cc_exp_year"));
//			stmt.execute();
			
			//iterate through product ids and set sale for each
			stmt = con.prepareStatement(sales_statement, Statement.RETURN_GENERATED_KEYS);
			String address = request.getParameter("address_street");
			if(request.getParameter("address_apt") != null) {
				address += request.getParameter("address_apt");
			}
			address += request.getParameter("address_city") + request.getParameter("address_state") +
					request.getParameter("address_country");
					
			
	        HttpSession session = request.getSession();
	        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
	        
	        for(int i = 0; i < cart.size(); i++) {
				stmt.setString(1, cart.get(i));
				stmt.setString(2, "1");
				stmt.setString(3, request.getParameter("cc_shipping"));
				stmt.setString(4, request.getParameter("first_name"));
				stmt.setString(5, request.getParameter("last_name"));
				stmt.setString(6, String.valueOf(ccID));
				stmt.setString(7, address);
				stmt.setString(8, request.getParameter("phone_number"));
				stmt.execute();	  
				
	        }
	        int result = -1;
	        ResultSet rs = stmt.getGeneratedKeys();
	        if(rs.next()) {
	        	result = rs.getInt(1);
	        }
			
			
			String url = "/OrderServlet?order=" + String.valueOf(result);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
			
		}catch(Exception e) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("errorMessage", e.getMessage());
			PrintWriter out = response.getWriter();
			out.write(jsonObject.toString());
			

			response.setStatus(500);
			e.printStackTrace();
			out.close();
		}
		
	}

}
