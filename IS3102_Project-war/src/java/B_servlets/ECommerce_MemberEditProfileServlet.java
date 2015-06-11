package B_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ECommerce_MemberEditProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            System.out.println("ECommerce_MemberEditProfileServlet:");
            boolean result = false;
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String country = request.getParameter("country");
            if (country == null) {
                country = "";
            }
            String address = request.getParameter("address");
            String securityQuestion = request.getParameter("securityQuestion");
            String securityAnswer = request.getParameter("securityAnswer");
            String ageString = request.getParameter("age");
            Integer age = null;
            if (ageString.equals("")) {
                ageString = null;
            } else {
                age = Integer.parseInt(ageString);
            }
            String incomeString = request.getParameter("income");
            Integer income = null;
            if (incomeString.equals("")) {
                incomeString = null;
            } else {
                income = Integer.parseInt(incomeString);
            }
            result = editMember(email, name, phone, country, address, Integer.parseInt(securityQuestion), securityAnswer, age, income, password);
            if (result) {
                response.sendRedirect("ECommerce_GetMember?goodMsg=Account updated successfully.");
            } else {
                response.sendRedirect("ECommerce_GetMember?errMsg=Account failed to update.");
            }
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
    }

    public Boolean editMember(String email, String name, String phone, String city, String address, Integer securityQuestion, String securityAnswer, Integer age, Integer income, String password) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client
//                .target("http://dmit.bulletplus.com/WebService_Mobile/webresources/entity.memberentity").path("editMember")
//                .queryParam("email", email)
//                .queryParam("name", name)
//                .queryParam("phone", phone)
//                .queryParam("city", city)
//                .queryParam("address", address)
//                .queryParam("securityQuestion", securityQuestion)
//                .queryParam("securityAnswer", securityAnswer)
//                .queryParam("age", age)
//                .queryParam("income", income)
//                .queryParam("password", password);
//        Invocation.Builder invocationBuilder = target.request();
//        Response response = invocationBuilder.post(null);
//        System.out.println("status: " + response.getStatus());
//
//        if (response.getStatus() != 200) {
//            return false;
//        } else {
//            return true;
//        }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
