// (C) 2024 uchicom
package com.uchicom.ui.action;

import com.uchicom.ui.util.UIStore;
import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * アクションのリソースを設定する
 *
 * @author uchicom: Shigeki Uchiyama
 */
public abstract class AbstractResourceAction<T extends Component> extends AbstractAction {

  private static final long serialVersionUID = 1L;

  public String CONVERT_IMAGE = "img:";

  public String CONVERT_KEY = "key:";

  public String CONVERT_INT = "int:";

  protected UIStore<T> uiStore;

  public AbstractResourceAction(UIStore<T> uiStore) {
    this.uiStore = uiStore;
    StringBuffer strBuff = new StringBuffer(128);
    String className = getClass().getCanonicalName();
    Field[] fields = getClass().getFields();
    for (Field field : fields) {
      // staticかを判定する
      int mod = field.getModifiers();
      if (Modifier.isFinal(mod) && Modifier.isStatic(mod) && Modifier.isPublic(mod)) {
        // staticならプロパティを設定する。
        setValue(uiStore.getActionResource(), strBuff, className, field);
      }
    }
  }

  protected void setValue(
      Properties resource, StringBuffer strBuff, String className, Field field) {
    strBuff.setLength(0);
    strBuff.append(className);
    strBuff.append(".");
    strBuff.append(field.getName());
    String key = strBuff.toString();
    // キーが存在していたら設定する
    if (resource.containsKey(key)) {
      try {
        String value = resource.getProperty(key);
        if (value.startsWith(CONVERT_IMAGE)) {
          // img:で始まっている場合イメージファイルをロードする。
          putValue((String) field.get(null), new ImageIcon(value.substring(4)));
        } else if (value.startsWith(CONVERT_KEY)) {
          putValue((String) field.get(null), KeyStroke.getKeyStroke(value.substring(4)));
        } else if (value.startsWith(CONVERT_INT)) {
          putValue((String) field.get(null), Integer.valueOf(value.substring(4).toCharArray()[0]));
        } else {
          putValue((String) field.get(null), value);
        }
      } catch (IllegalArgumentException e) {
        // 握りつぶしてなにもしない。
      } catch (IllegalAccessException e) {
        // 握りつぶしてなにもしない。
      }
    }
  }
}
