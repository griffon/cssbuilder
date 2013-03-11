/*
 * Copyright 2009-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.builder.css;

import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import static com.feature50.clarity.ClarityConstants.CLIENT_PROPERTY_CLASS_KEY;

/**
 * @author Andres Almiray
 */
public class CSSBuilderExtension {
    public static String getCssClass(JComponent component) {
        Object clientProperty = component.getClientProperty(CLIENT_PROPERTY_CLASS_KEY);
        if (clientProperty == null) {
            clientProperty = new CssClass();
            component.putClientProperty(CLIENT_PROPERTY_CLASS_KEY, clientProperty);
        }
        return clientProperty.toString();
    }

    public static void setCssClass(JComponent component, String cssClass) {
        component.putClientProperty(CLIENT_PROPERTY_CLASS_KEY, new CssClass(cssClass));
    }

    public static CssClass cssClass(JComponent component) {
        Object clientProperty = component.getClientProperty(CLIENT_PROPERTY_CLASS_KEY);
        if (clientProperty == null) {
            clientProperty = new CssClass();
            component.putClientProperty(CLIENT_PROPERTY_CLASS_KEY, clientProperty);
        }
        return clientProperty instanceof CssClass ? (CssClass) clientProperty : null;
    }

    public static JComponent $(Container container, String name) {
        return CSSBuilder.$(container, name);
    }

    public static JComponent[] $s(Container container, String... names) {
        return CSSBuilder.$s(container, names);
    }

    public static JComponent[] $$(Container container, String name) {
        return CSSBuilder.$$(container, name);
    }

    public static BorderUtils.BorderType getBorderType(Border border) {
        return BorderUtils.getBorderType(border);
    }

    public static void setBorderType(Border border, BorderUtils.BorderType borderType) {
        BorderUtils.setBorderType(border, borderType);
    }

    public static Color getBoderColor(Border border) {
        return BorderUtils.getBorderColor(border);
    }

    public static void setBorderColor(Border border, Color color) {
        BorderUtils.setBorderColor(border, color);
    }

    public static Insets getBorderInsets(Border border) {
        return BorderUtils.getBorderInsets(border);
    }

    public static void setBorderInsets(Border border, Insets insets) {
        BorderUtils.setBorderInsets(border, insets);
    }
}
