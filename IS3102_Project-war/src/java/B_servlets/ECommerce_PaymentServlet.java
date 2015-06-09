package B_servlets;

import EntityManager.MemberEntity;
import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
public class ECommerce_PaymentServlet extends HttpServlet {

    private String URLprefix = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("ECommerce_AddFurnitureToListServlet");
        try {
            HttpSession session = request.getSession();

            URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
                return;
            }

            Long countryID = (Long) session.getAttribute("countryID");
            MemberEntity m = (MemberEntity) session.getAttribute("member");
            Long memberId = m.getId();

            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");

//            double amountPaid = 0.0;
//            for (ShoppingCartLineItem item : shoppingCart) {
//                amountPaid += item.getPrice() * item.getQuantity();
//            }
//            String salesRecordID = createECommerceTransactionRecord(memberId, amountPaid, countryID);
//            if (salesRecordID.equals("0")) {
//                //error
//                System.out.println("Error creating ECommerce Transaction Record. Sales record ID returned 0.");
//                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error processing transaction.");
//
//                return;
//            }
//            for (ShoppingCartLineItem item : shoppingCart) {
//                String itemID = item.getId();
//                int quantity = item.getQuantity();
//                //call ws to insert lineitem and salesrecordentity_lineitementity based on salesRecordID and lineItemID
//                String result = createECommerceLineItemRecord(salesRecordID, itemID, quantity, countryID);
//                if (!result.equals("0")) {
//                    System.out.println("createECommerceLineItemRecord successful");
//                } else {
//                    System.out.println("Error creating createECommerceLineItemRecord, returned 0.");
//                    response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error checking out.");
//                    return;
//                }
//            }

            session.setAttribute("shoppingCart", null);

            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?goodMsg=Thank you for shopping at Island Furniture. You have checkout successfully!");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error checking out.");
        }
    }

    public String createECommerceLineItemRecord(String salesRecordID, String itemID, int quantity, Long countryID) {
//        try {
//            Client client = ClientBuilder.newClient();
//            WebTarget target = client
//                    .target("http://dmit.bulletplus.com/WebService_Mobile/webresources/commerce")
//                    .path("createECommerceLineItemRecord")
//                    .queryParam("salesRecordID", salesRecordID)
//                    .queryParam("itemID", itemID)
//                    .queryParam("quantity", quantity)
//                    .queryParam("countryID", countryID);
//
//            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
//            Response response = invocationBuilder.put(Entity.entity("", "application/json"));
//            if (response.getStatus() != 201) {
//                return "0";
//            }
//            String path = response.getLocation().getPath();
//            String result = path.substring(path.indexOf('=') + 1);
//            return result + "";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "0";
//        }
        return null;
    }

    public String createECommerceTransactionRecord(Long memberId, Double amountPaid, Long countryId) {
//        try {
//            Client client = ClientBuilder.newClient();
//            WebTarget target = client
//                    .target("http://dmit.bulletplus.com/WebService_Mobile/webresources/commerce")
//                    .path("createECommerceTransactionRecord")
//                    .queryParam("memberID", memberId)
//                    .queryParam("amountPaid", amountPaid)
//                    .queryParam("countryID", countryId);
//
//            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
//            Response response = invocationBuilder.put(Entity.entity("", "application/json"));
//            if (response.getStatus() != 201) {
//                return "0";
//            }
//            String path = response.getLocation().getPath();
//            String result = path.substring(path.indexOf('=') + 1);
//            return result + "";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "0";
//        }
        return null;
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
