

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    private String query = "Select * from Products";
    private String type_query = "Select name from Types where id in (select typeId from Product_Type where productId=?)";
    
    private String username="root";
    private String password="inf124";
    private String loginUrl="jdbc:mysql://centaurus-3.ics.uci.edu:1043/MemeChan";    
      
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(loginUrl, username, password);
	        JsonArray json = new JsonArray();
	        
	        
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()) {
	        	JsonObject jsonObject = new JsonObject();
	        	String id = rs.getString("id");
                String url = rs.getString("url");
	            String title = rs.getString("title");
	            String price = rs.getString("price");
	            String origin = rs.getString("origin");
	            
	            jsonObject.addProperty("id", id);
	            jsonObject.addProperty("url", url);
	            jsonObject.addProperty("title", title);
	            jsonObject.addProperty("price", price);
	            jsonObject.addProperty("origin", origin);
	            
	            
	            JsonArray typeArray = new JsonArray();
	            PreparedStatement type_statement = con.prepareStatement(type_query);
	            type_statement.setString(1, id);
	            ResultSet rs1 = type_statement.executeQuery();
	            while(rs1.next()) {
	            	JsonObject type = new JsonObject();
	            	String n = rs1.getString("name");
	            	type.addProperty("name", n);
	            	typeArray.add(type);
	            }
	            jsonObject.add("types", typeArray);
	            
	        	json.add(jsonObject);
	        }
	        
	        out.print(json);
	        response.setStatus(200);
	        
            String url = "/VisitedProducts";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.include(request, response);
			
			
            
	        
	        
	        con.close();
			
		} catch (Exception e) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("errorMessage", e.getMessage());
			out.write(jsonObject.toString());

			response.setStatus(500);
			e.printStackTrace();
		} 
        

		
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
