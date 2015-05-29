package B_servlets;

import HelperClasses.Member;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ECommerce_GetMember extends HttpServlet {

    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("memberEmail");
        String URLprefix = (String) session.getAttribute("URLprefix");
        String location = "";
        String errMsg = request.getParameter("errMsg");
        String goodMsg = request.getParameter("goodMsg");
        try {
            Member member = getMember(email);
            if (member == null) {
                result = "Error retrieving member details. Please login again.";
                location = "/IS3102_Project-war/B/" + URLprefix + "memberLogin.jsp?errMsg=" + result;
            } else {
                session.setAttribute("member", member);
                session.setAttribute("memberName", member.getName());
                if (errMsg != null) {
                    location = "/IS3102_Project-war/B/" + URLprefix + "memberProfile.jsp?errMsg=" + errMsg;
                } else if (goodMsg != null) {
                    location = "/IS3102_Project-war/B/" + URLprefix + "memberProfile.jsp?goodMsg=" + goodMsg;
                } else {
                    location = "/IS3102_Project-war/B/" + URLprefix + "memberProfile.jsp";
                }
            }
        } catch (Exception ex) {
            result = "Error retrieving member details. Please login again.";
            location = "/IS3102_Project-war/B/" + URLprefix + "memberLogin.jsp?errMsg=" + result;
        }
        response.sendRedirect(location);
    }

    public Member getMember(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("http://dmit.bulletplus.com/WebService_Mobile/webresources/entity.memberentity").path("getMember")
                .queryParam("email", email);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println("status: " + response.getStatus());

        if (response.getStatus() != 200) {
            return null;
        }

        Member member = response.readEntity(Member.class);
        return member;
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
