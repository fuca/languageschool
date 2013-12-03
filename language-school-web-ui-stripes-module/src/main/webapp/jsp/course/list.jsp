<%-- 
    Document   : list
    Created on : Nov 28, 2013, 12:27:21 PM
    Author     : Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.courses.title">

    <s:layout-component name="header"></s:layout-component>
    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <s:useActionBean beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean" var="actionBean"/>
        <h2>
            <f:message key="admin.courses.title"/>
        </h2>

        <fieldset>
            <legend><f:message key="course.list.label"/> <a href="#newCourse">[+]</a></legend>
            <table>
                <tr>
                    <th><f:message key="course.entity.id"/></th>
                    <th><f:message key="course.entity.code"/></th>
                    <th><f:message key="course.entity.name"/></th>
                    <th><f:message key="course.entity.language"/></th>
                    <th><f:message key="course.entity.requiredLevel"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.courses}" var="course">
                    <tr>
                        <td class="content"><c:out value="${course.id}"/></td>
                        <td class="content"><c:out value="${course.code}"/></td>
                        <td class="content"><c:out value="${course.name}"/></td>
                        <td class="content"><c:out value="${course.taughtLanguage}"/></td>
                        <td class="content"><c:out value="${course.requiredLevel}"/></td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean">
                                <s:hidden name="course.id" value="${course.id}"/>
                                <s:submit name="edit">
                                    <f:message key="course.update.label"/>
                                </s:submit>
                            </s:form>
                        </td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean">
                                <s:hidden name="course.id" value="${course.id}"/>
                                <s:submit name="delete"><f:message key="course.delete.label"/></s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean">
                <a name="newCourse"/>
                <fieldset><legend><f:message key="course.form.new"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="add"><f:message key="course.add.label"/></s:submit>
                    </fieldset>
            </s:form>


        </body>
    </html>
</s:layout-component>     
</s:layout-render>

