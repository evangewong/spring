

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String query = "Select * from Products where id=$product;";
    private String type_query = "Select name from Types where id in (select typeId from Product_Type where productId=?)";
       
    private String username="root";
    private String password="inf124";
    private String loginUrl="jdbc:mysql://centaurus-3.ics.uci.edu:1043/MemeChan"; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("product");
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(loginUrl, username, password);
			JsonObject jsonObject = new JsonObject();
			
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, product);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                jsonObject.addProperty("id", rs.getString("id"));
                jsonObject.addProperty("url", rs.getString("url"));
                jsonObject.addProperty("price", rs.getString("price"));
                jsonObject.addProperty("title", rs.getString("title"));
                jsonObject.addProperty("origin", rs.getString("origin"));
            }
            
            
            PreparedStatement typeStatement = con.prepareStatement(type_query);
            typeStatement.setString(1,product);
            ResultSet rs1 = typeStatement.executeQuery();
            
            JsonArray typeArray = new JsonArray();
            while(rs1.next()){
                JsonObject type = new JsonObject();
                String n = rs1.getString("name");
                type.addProperty("name",n);
                typeArray.add(type);
            }
            
            jsonObject.add("type", typeArray); //adds type to individual object
            
            out.print(jsonObject);
            out.flush(); 
			
			
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
