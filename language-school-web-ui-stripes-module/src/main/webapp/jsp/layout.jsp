f<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-definition>
    <DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="${pageContext.request.contextPath}/js/jonthornton-jquery-timepicker-83399f0/jquery.timepicker.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/messages.js"></script>
            <script>
                $('#basicExample').timepicker();
            </script>
            <s:layout-component name="header"/>
        </head>
        <body>
            <a name="top"/>
            <div class="wrapper">
                <div class="layout-header">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}">
                            <img src="${pageContext.request.contextPath}/image/logo.png" alt="logo"/></a>
                    </div>
                    <div class="heading">
                    <h1>
                        <f:message key="app.header.title"/>
                    </h1>
                        <span class="credo"><f:message key="app.header.credo"/></span>
                    </div>
                    <span class="sign-details">User <strong>not signed in</strong></span>
                </div>

                <div class="content">
                    <s:layout-component name="body"/>
                </div>
            </div>
        </body>
    </html>
</s:layout-definition>