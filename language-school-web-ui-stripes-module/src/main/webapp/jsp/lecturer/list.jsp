<%-- 
    Document   : list
    Created on : Nov 28, 2013, 12:27:50 PM
    Author     : Milos Petrovic
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.lecturers.title">

    <s:layout-component name="header"></s:layout-component>
    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <s:useActionBean beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean" var="actionBean"/>
        <h2>
            <f:message key="admin.lecturers.title"/>
        </h2>
        <fieldset>
            <legend><f:message key="lecturer.list.label"/> <a href="#newLecturer">[+]</a></legend>
            <table>
                <tr>
                    <th><f:message key="lecturer.entity.id"/></th>
                    <th><f:message key="lecturer.entity.name"/></th>
                    <th><f:message key="lecturer.entity.surname"/></th>
                    <th><f:message key="lecturer.entity.nativeSpeaker"/></th>
                </tr>

                <c:forEach items="${actionBean.lecturers}" var="lecturer">
                    <tr>
                        <td class="content"><c:out value="${lecturer.id}"/></td>
                        <td class="content"><c:out value="${lecturer.firstName}"/></td>
                        <td class="content"><c:out value="${lecturer.lastName}"/></td>
                        <td class="content"><c:out value="${lecturer.nativeSpeaker}"/></td>
                        <td class="content"><c:out value="${lecturer.languages}"/></td>
                        
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean">
                                <s:hidden name="lecturer.id" value="${lecturer.id}"/>
                                <s:submit name="edit">
                                    <f:message key="lecturer.update.label"/>
                                </s:submit>
                            </s:form>
                        </td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean">
                                <s:hidden name="lecturer.id" value="${lecturer.id}"/>
                                <s:submit name="delete"><f:message key="lecturer.delete.label"/></s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>

            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean">
                <a name="newLecturer"/>
                <fieldset><legend><f:message key="lecturer.form.new"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="add"><f:message key="lecturer.add.label"/></s:submit>
                    </fieldset>
            </s:form>
        </s:layout-component>     
    </s:layout-render>
