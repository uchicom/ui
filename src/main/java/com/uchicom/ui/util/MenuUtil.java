// (C) 2024 uchicom
package com.uchicom.ui.util;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuUtil {

  public static <T extends Component> JMenuItem createMenu(
      UIStore<T> uiStore, Properties resource, Map<String, Action> actionMap, String key) {
    String name = key + ".NAME";
    if (resource.containsKey(name)) {
      JMenu menu = new JMenu(resource.getProperty(name));
      if (resource.containsKey(key)) {
        String[] childMenu = resource.getProperty(key).split(",");
        for (String child : childMenu) {
          if ("".equals(child)) continue;
          if (child.charAt(0) == '[' && child.charAt(child.length() - 1) == ']') {
            String special = child.substring(1, child.length() - 1);
            if (special.length() == 0) continue;
            if (special.length() == 1) {
              // ラインセパレータ
              if ("-".equals(special)) {
                menu.addSeparator();
                continue;
              }
            }
          }
          menu.add(createMenu(uiStore, resource, actionMap, key + "." + child));
        }
      }
      return menu;
    } else {
      try {
        String actionKey = key + ".ACTION";
        Action action = createAction(uiStore, resource, actionMap, actionKey);
        if (action != null) {
          return new JMenuItem(action);
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    return null;
  }

  public static <T extends Component> Action createAction(
      UIStore<T> uiStore, Properties resource, Map<String, Action> actionMap, String actionKey)
      throws InstantiationException,
          IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchMethodException,
          SecurityException,
          ClassNotFoundException {
    if (resource.containsKey(actionKey)) {
      String className = resource.getProperty(actionKey);
      Action action = actionMap.get(className);
      if (action == null) {
        action =
            (Action) Class.forName(className).getConstructor(UIStore.class).newInstance(uiStore);
        actionMap.put(className, action);
      }
      return action;
    } else {
      return null;
    }
  }
}
