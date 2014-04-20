<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../css/main.css"/>
<div>
    <%  List<Address> addressList = user.getAdressList();
        if (addressList.size() == 1)
        {
            Address address = addressList.get(0);
    %>
    <div>Liefer- und Rechnungsadresse:</div>
    <div><%=address.getStreet()%> <%=address.getHousenumber()%></div>
    <div><%=address.getPostalCode()%> <%=address.getCity()%></div>
    <% if (address.getRegion() != null)
        {
    %>
    <div><%=address.getRegion()%></div>
    <% }
    %>
    <div><%=address.getCountry()%></div>
    <% } else
    {
        for (Address address : addressList)
        {
            if (address.isInvoiceAddress())
            {
    %>
    <div>Rechnungsadresse:</div>
    <%      } else
    {
    %>
    <div>Lieferadresse:</div>
    <%        }
    %>
    <div><%=address.getStreet()%> <%=address.getHousenumber()%></div>
    <div><%=address.getPostalCode()%> <%=address.getCity()%></div>
    <% if (address.getRegion() != null)
        {
    %>
    <div><%=address.getRegion()%></div>
    <% }
    %>
    <div><%=address.getCountry()%></div>
    <% }
        }
    %>
</div>