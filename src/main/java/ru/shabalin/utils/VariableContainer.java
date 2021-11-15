package ru.shabalin.utils;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
    private Map<String, Object> map;


    public VariableContainer() {
        map = new HashMap<>();
    }

    public void setVar(String varName, Object var) {
        map.put(varName, var);
    }

    public Object getVar(String varName) {
        return map.get(varName);
    }
}
