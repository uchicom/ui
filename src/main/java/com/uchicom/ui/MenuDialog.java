// (C) 2018 uchicom
package com.uchicom.ui;

import com.uchicom.ui.util.MenuUtil;
import com.uchicom.ui.util.UIStore;
import java.awt.Component;
import java.awt.Window;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.Action;
import javax.swing.JMenuBar;

public abstract class MenuDialog<T extends Component> extends ResumeDialog implements UIStore<T> {

  /** */
  private static final long serialVersionUID = 1L;

  protected Map<String, Action> actionMap = new HashMap<>();

  public MenuDialog(
      Window owner, Properties config, String dialogKey, Properties resource, String menuKey) {
    super(owner, config, dialogKey);
    initMenu(resource, menuKey);
  }

  public MenuDialog(ResumeFrame owner, String dialogKey, Properties resource, String menuKey) {
    super(owner, dialogKey);
    initMenu(resource, menuKey);
  }

  public MenuDialog(Properties config, String dialogKey, Properties resource, String menuKey) {
    super(config, dialogKey);
    initMenu(resource, menuKey);
  }

  private void initMenu(Properties resource, String menuKey) {
    JMenuBar menuBar = new JMenuBar();
    String[] menus = resource.getProperty(menuKey).split(",");
    for (String menuProp : menus) {
      menuBar.add(MenuUtil.createMenu(this, resource, actionMap, menuKey + "." + menuProp));
    }
    setJMenuBar(menuBar);
  }
}
