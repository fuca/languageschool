<%-- 
    Document   : edit
    Created on : Nov 28, 2013, 12:27:39 PM
    Author     : Michal Fucik (395624) michal.fuca.fucik(at)gmail.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.lecturers.title">
    <s:layout-component name="header"></s:layout-component>

    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <h2><f:message key="admin.lecturers.title"/></h2>
        <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean">
            <s:hidden name="lecturer.id"/>
            <fieldset>
                <legend>
                    <f:message key="lecturer.form.update"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="save">
                    <f:message key="lecturer.update.label"/>
                </s:submit>
                <s:submit name="cancelEdit">
                    <f:message key="lecturer.cancel.label"/>
                </s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>