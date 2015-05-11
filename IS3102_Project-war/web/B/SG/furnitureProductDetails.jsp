<%@page import="java.net.URLDecoder"%>
<%@page import="HelperClasses.Furniture"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.PromotionEntity"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityManager.MemberEntity"%>
<jsp:include page="checkCountry.jsp" />
<%
    String sku = request.getParameter("sku");
    if (sku == null) {
%>
<jsp:forward page="index.jsp" />
<%
    }
    Boolean isMemberLoggedIn = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        isMemberLoggedIn = false;
    } else {
        isMemberLoggedIn = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <%
            List<Furniture> furnitures = (List<Furniture>) (session.getAttribute("furnitures"));
            Furniture furniture = new Furniture();
            List<StoreEntity> storesInCountry = (List<StoreEntity>) session.getAttribute("storesInCountry");
            if (furnitures != null) {
                for (int i = 0; i < furnitures.size(); i++) {
                    if (furnitures.get(i).getSKU().equals(sku)) {
                        furniture = furnitures.get(i);
                    }
                }
            }
        %>
        <div class="body">
            <jsp:include page="menu2.jsp" />
            <div class="body">
                <div role="main" class="main">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Furnitures</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">
                        <hr class="tall">
                        <div class="row">
                            <div class="col-md-6">
                                <div>
                                    <div class="thumbnail">
                                        <img alt="" class="img-responsive img-rounded" src="../../..<%=furniture.getImageUrl()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="summary entry-summary">
                                    <h2 class="shorter"><strong><%=furniture.getName()%></strong></h2>
                                            <%
                                                String finalPrice = "";
                                                String price = "Unavailable";
                                                price = "$" + furniture.getPrice() + "0";
                                                finalPrice = furniture.getPrice() + "";
                                                
                                                if (isMemberLoggedIn == true) {
                                            %>
                                    <a href="../../ECommerce_AddFurnitureToListServlet?id=<%=furniture.getId()%>&SKU=<%=furniture.getSKU()%>&price=<%=finalPrice%>&name=<%=furniture.getName()%>&imageURL=<%=furniture.getImageUrl()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                        <input type="button" name="btnEdit" class="btn btn-primary" id="<%=furniture.getSKU()%>" value="Add To Cart"/>
                                    </a>
                                    <%
                                        }
                                    %>
                                    <p class="price"><h4 class="amount"><%=price%></h4></p>

                                    <p class="taller">
                                        <%if (furniture.getDescription() != null) {
                                                out.println(furniture.getDescription());
                                            }
                                        %>
                                    </p>
                                    <p>
                                        Height: <%=furniture.getHeight() + " cm"%><br/>
                                        Length: <%=furniture.getLength() + " cm"%><br/>
                                        Width: <%=furniture.getWidth() + " cm"%>
                                    </p>
                                    <div class="product_meta">
                                        <span class="posted_in">Category: <a rel="tag" href="../../ECommerce_FurnitureCategoryServlet?cat=<%=URLEncoder.encode(furniture.getCategory())%>"><%=furniture.getCategory()%></a></span>
                                    </div>
                                    <br/><br/>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <form action="../../ECommerce_StockAvailability">
                                                View Item Availability<br/>
                                                <select style="color: black;" name="storeID">
                                                    <option> </option>
                                                    <%String storeIDstring = (request.getParameter("storeID"));
                                                        Long storeID = 1L;
                                                        if (storeIDstring != null) {
                                                            storeID = Long.parseLong(storeIDstring);
                                                        }
                                                        for (int i = 0; i < storesInCountry.size(); i++) {
                                                            if (!storesInCountry.get(i).getId().equals(storeID)) {%>
                                                    <option value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%} else {%>
                                                    <option selected value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select><br/><br/>
                                                <input type="submit" class="btn btn-primary btn-icon" value="Check Item Availability"/>
                                                <input type="hidden" name="sku" value="<%=sku%>"/>
                                                <input type="hidden" name="type" value="Furniture"/>
                                            </form>
                                        </div>
                                        <%
                                            String itemQty = (String) (request.getParameter("itemQty"));
                                            if (itemQty != null) {
                                        %>
                                        <div class="col-md-6">
                                            Status: <%if (Integer.parseInt(itemQty) > 0) {%>Available<%} else {%>Unavailable<%}%>
                                            <br/>
                                            Remaining Qty: <%=itemQty%>
                                            <%}%>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <hr class="tall">
                        </div>
                    </div>
                </div>
                <jsp:include page="footer.html" />
            </div>
    </body>
</html>
