<%-- 
    Document   : An entry point of lectures administration
    Created on : Nov 28, 2013, 12:27:50 PM
    Author     : Michal Fucik (395624) michal.fuca.fucik(at)gmail.com
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="admin.lectures.title">

    <s:layout-component name="header"></s:layout-component>
    <s:layout-component name="body">
        <%@include file="../menu.jsp"%>
        <s:useActionBean beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean" var="actionBean"/>
        <h2><f:message key="admin.lectures.title"/></h2>
        
        <fieldset>
            <legend><f:message key="lecture.list.label"/> <a href="#newLecture">[+]</a></legend>
            <table>
                <tr>
                    <th><f:message key="lecture.entity.id"/></th>
                    <th><f:message key="lecture.entity.label"/></th>
                    <th><f:message key="lecture.entity.topicDescription"/></th>
                    <th><f:message key="lecture.entity.lecturer"/></th>
                    <th><f:message key="course.entity.code"/></th>
                    <th><f:message key="course.entity.language"/></th>
                    <th><f:message key="lecture.entity.tpDay"/></th>
                </tr>

                <c:forEach items="${actionBean.lectures}" var="lecture">
                    <tr>
                        <td class="content" title="${lecture.topicDescription}">${lecture.id}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.label}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.topicDescription}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.course.code}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.lecturer.firstName} ${lecture.lecturer.lastName}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.course.language}</td>
                        <td class="content" title="${lecture.topicDescription}">${lecture.tpDay}</td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean">
                                <s:hidden name="lecture.id" value="${lecture.id}"/>
                                <s:submit name="deleteLecture">
                                    <f:message key="lecture.delete.label"/>
                                </s:submit>
                            </s:form>
                        </td>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean">
                                <s:hidden name="lecture.id" value="${lecture.id}"/>
                                <s:submit name="editLecture">
                                    <f:message key="lecture.update.label"/>
                                </s:submit>
                            </s:form>                            
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>

        <s:form beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean">
            <a name="newLecture"/>
            <fieldset>
                <legend>
                    <f:message key="lecture.form.new"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="addLecture"><f:message key="lecture.add.label"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>     
</s:layout-render>
