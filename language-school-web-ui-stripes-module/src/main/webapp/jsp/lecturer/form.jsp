<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="lecturer.firstName"/></th>
        <td><s:text id="b1" name="lecturer.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="lecturer.lastName"/></th>
        <td><s:text id="b2" name="lecturer.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="lecturer.nativeSpeaker"/></th>
        <td><s:select id="b3" name="lecturer.nativeSpeaker">
                <s:options-enumeration enum="cz.muni.fi.pa165.languageschool.Language"/>
            </s:select></td>
    </tr>

    <tr>
        <th><s:label for="b4" name="lecturer.languages"/></th>
        <td><select id="b4" name="lecturer.languages" multiple="multiple" size="4" required>
                <option value="CZECH" selected="selected"/>Czech</option>
                <option value="ENGLISH" />English</option>
                <option value="GERMAN" />German</option>
                <option value="SPANISH" />Spanish</option>
                <option value="FRENCH" />French</option>
                <option value="MANDARIN" />Mandarin</option>
                <option value="HINDI" />Hindi</option>
                <option value="ARABIC" />Arabic</option>
                <option value="PORTUGUESE" />Portuguese</option>
                <option value="RUSSIAN" />Russian</option>
                <option value="SWAHILI" />Swahili</option>
                <option value="TAMIL" />Tamil</option>
                <option value="ITALIAN" />Italian</option>
                <option value="DUTCH" />Dutch</option>
            </select></td>
    </tr>
    <%--tr>
        <th><s:label for="b3" name="lecturer.entity.lectures"/></th>
        <td><s:select id="b3" name="lecturer.lectures">
                <s:options-collection collection="${actionBean.lectures}" value="id" label="code"/>
            </s:select></td>
    </tr--%>
</table>