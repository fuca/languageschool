<%-- 
    Document   : New lecture content form
    Created on : Nov 28, 2013, 12:27:44 PM
    Author     : Michal Fucik (395624) michal.fuca.fucik(at)gmail.com
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="lecture.label"/></th> <%--nazev pole, bere podle lokalizace proto vse male--%>
        <td><s:text id="b1" name="lecture.label"/></td> <%--nazev atributu usera. Bere z UserDTO, proto camelCase--%>
    </tr>
    <tr>
        <th><s:label for="b2" name="lecture.topicDescription"/></th>
        <td><s:textarea id="b2" name="lecture.topicDescription"/></td>
    </tr>
    <tr>
        <th><s:label for="basicExample" name="lecture.tpTime"/></th>
        <td><s:text id="basicExample" name="lecture.tpTime"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="courseId"/></th>
        <td><s:select id="b3" name="courseId">
                <s:options-collection collection="${actionBean.courses}" value="id" label="code"/>
            </s:select></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="lecture.tpDay"/></th>
        <td><s:select id="b5" name="lecture.tpDay">
                <s:options-enumeration enum="cz.muni.fi.pa165.languageschool.utils.DayOfWeek"/>
            </s:select></td>
    </tr>    
    
    <tr>
        <th><s:label for="b6" name="lecture.lecturer"/></th>
        <td><s:select id="b6" name="lecture.lecturer">
                <s:options-collection collection="${actionBean.lecturers}" value="id" label="lastName"/>
            </s:select></td>
    </tr>
</table>