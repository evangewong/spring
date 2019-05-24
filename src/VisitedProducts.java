

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/VisitedProducts")
public class VisitedProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        JsonArray visitedArray = new JsonArray();
        
        HttpSession session = request.getSession();
        ArrayList<String> visited = (ArrayList<String>) session.getAttribute("visited");
        
        if(visited == null) {
        	visited = new ArrayList<String>();
        }
        
        for(int i = 0; i < visited.size(); i++){
            JsonObject o = new JsonObject();
            o.addProperty("id",visited.get(i));
            visitedArray.add(o);
        }
        
        
        out.print("*" + visitedArray.toString());
        out.flush();
        out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String param = request.getParameter("visited");

        HttpSession session = request.getSession(true);
        ArrayList<String> visited  = (ArrayList<String>) session.getAttribute("visited");
        if(visited == null){ //if visited list doesnt exist, create it
            visited = new ArrayList<>();
        }

	    if(visited.contains(param) == false) { //add product to visited if its not already in
		    visited.add(param);
            session.setAttribute("visited", visited);
	    }
	    
	    response.setContentType("application/json");
        PrintWriter out = response.getWriter();
	    JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", "success");
		out.write(jsonObject.toString());
	    
	}

}
