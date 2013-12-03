<%-- 
    Document   : list
    Created on : Nov 28, 2013, 12:27:50 PM
    Author     : Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.students.title">

    <s:layout-component name="header"></s:layout-component>
    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <s:useActionBean beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean" var="actionBean"/>
        <h2>
            <f:message key="admin.students.title"/>
        </h2>

        <fieldset>
            <legend><f:message key="student.list.label"/> <a href="#newStudent">[+]</a></legend>
            <table>
                <tr>
                    <th><f:message key="student.entity.id"/></th>
                    <th><f:message key="student.entity.name"/></th>
                    <th><f:message key="student.entity.surname"/></th>
                </tr>
                
                <c:forEach items="${actionBean.students}" var="student">
                    <tr>
                        <td class="content"><c:out value="${student.id}"/></td>
                        <td class="content"><c:out value="${student.firstName}"/></td>
                        <td class="content"><c:out value="${student.lastName}"/></td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean">
                                <s:hidden name="student.id" value="${student.id}"/>
                                <s:submit name="edit">
                                    <f:message key="student.update.label"/>
                                </s:submit>
                            </s:form>
                        </td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean">
                                <s:hidden name="student.id" value="${student.id}"/>
                                <s:submit name="delete">
                                    <f:message key="student.delete.label"/>
                                </s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>

        <s:form beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean">
            <a name="newStudent"/>
            <fieldset>
                <legend>
                    <f:message key="student.form.new"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="student.add.label"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>     
</s:layout-render>
