<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!-- display errors if they occur -->
<s:errors/>
<table>
    <tr>
        <th><s:label for="c1" name="course.code"/></th>
        <td><s:text id="c1" name="course.code"/></td>
    </tr>
    <tr>
        <th><s:label for="c2" name="course.name"/></th>
        <td><s:text id="c2" name="course.name"/></td>
    </tr>
     <tr>
        <th><s:label for="c3" name="course.taughtLanguage"/></th>
        <td><s:select id="c3" name="course.taughtLanguage">
                <s:options-enumeration enum="cz.muni.fi.pa165.languageschool.Language"/>
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="c4" name="course.requiredLevel"/></th>
        <td><s:text id="c4" name="course.requiredLevel"/></td>
    </tr>
</table>
