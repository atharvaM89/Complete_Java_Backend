import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class JavaLifeCycle extends GenericServlet {
    public void init(){
        System.out.println("init Called - servlet Initilization");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("Service() Called- Handling Request");
        res.setContentType("text/html");//In this res object will request the text or HTML file form the server
        //If we want to write anything of HTML, so for that we need to use PrintWriter
        PrintWriter out=res.getWriter();
        out.println("<h2> Hello Form Atharva Mahulkar </h2>"); //Use to Perform Html Code in java
    }
}
