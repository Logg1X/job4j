package ood.tdd;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator {

    public String generated(String template, Map<String, String> keys) {
        this.validateKeys(template, keys);
        for (String s : keys.keySet()) {
            template = template.replaceAll(s.replaceAll("[${}]", ""), keys.get(s))
                    .replaceAll("[${}]", "");
        }
        return template;
    }

    private Set<String> getKeysFromString(String template) {
        Set<String> result = new HashSet<>();
        Matcher matcher = Pattern.compile("\\$\\{\\w*\\}").matcher(template);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    private void validateKeys(String template, Map<String, String> keys) {
        if (!template.contains("${") || keys.keySet().isEmpty()) {
            throw new UnsupportedOperationException("The list of keys is empty or the string does not contain any keys!");
        }
        Set<String> templateKey = getKeysFromString(template);
        for (String tkey : templateKey) {
            if (!keys.containsKey(tkey)) {
                throw new UnsupportedOperationException("Not enough keys!");
            }
        }
        if (keys.keySet().size() > templateKey.size()) {
            throw new UnsupportedOperationException("Excess keys!");
        }
    }
}
