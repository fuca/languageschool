<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.students.title">
    <s:layout-component name="header"></s:layout-component>

    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <h2><f:message key="admin.students.title"/></h2>
        <s:form beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean">
            <s:hidden name="student.id"/>
            <fieldset>
                <legend>
                    <f:message key="student.form.update"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="save">
                    <f:message key="student.update.label"/>
                </s:submit>
                <s:submit name="cancelEdit">
                    <f:message key="student.cancel.label"/>
                </s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>