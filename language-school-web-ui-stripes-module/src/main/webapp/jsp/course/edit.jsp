<%-- 
    Document   : Curse edit jsp
    Created on : Nov 28, 2013, 12:27:07 PM
    Author     : Michal Fucik (395624) michal.fuca.fucik(at)gmail.com
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.courses.title">
    <s:layout-component name="header"></s:layout-component>

    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <h2><f:message key="admin.courses.title"/></h2>
        <s:form beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean">
            <s:hidden name="course.id"/>
            <fieldset>
                <legend>
                    <f:message key="course.form.update"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="save">
                    <f:message key="course.update.label"/>
                </s:submit>
                <s:submit name="cancelEdit">
                    <f:message key="course.cancel.label"/>
                </s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>