<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/jsp/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <table class="index-grid">
            <tr>
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean" event="list">
                        <f:message key="admin.navigation.lecturers"/>
                    </s:link>
                </td>
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean">
                        <f:message key="admin.navigation.lectures"/>
                    </s:link>
                </td>
            </tr>
            <tr>
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean" event="list">
                        <f:message key="admin.navigation.courses"/>
                    </s:link>
                </td>

                <td>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean" event="list">
                        <f:message key="admin.navigation.students"/>
                    </s:link>
                </td>

            </tr>
        </table>
    </s:layout-component>
</s:layout-render>
