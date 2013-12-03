<%-- 
    Document   : base
    Created on : Dec 1, 2013, 2:13:07 AM
    Author     : fuca
--%>    
<div class="upper-menu">
                 <ul>
                    <li>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.LecturerActionBean" event="list">
                        <f:message key="admin.navigation.lecturers"/>
                    </s:link>
                    </li>

                    <li>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.LectureActionBean">
                        <f:message key="admin.navigation.lectures"/>
                    </s:link>
                    </li>

                    <li>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.CourseActionBean" event="list">
                        <f:message key="admin.navigation.courses"/>
                    </s:link>
                    </li>

                    <li>
                    <s:link beanclass="cz.muni.fi.pa165.languageschool.web.StudentActionBean" event="list">
                        <f:message key="admin.navigation.students"/>
                    </s:link>
                    </li>
                </ul>
            </div>
            <s:messages/>