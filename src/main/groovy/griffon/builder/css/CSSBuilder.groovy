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

package griffon.builder.css

import com.feature50.clarity.css.CSSPropertyHandlers
import com.feature50.util.SwingUtils

import javax.swing.JComponent
import java.awt.Container

/**
 * @author Andres Almiray
 */
public class CSSBuilder extends FactoryBuilderSupport {
    static {
        CSSPropertyHandlers.getInstance().addHandler(SwingCSSPropertyHandler.getInstance());
    }

    public CSSBuilder(boolean init = true) {
        super(init)
    }

    public void registerCSS() {
        registerExplicitMethod("\$", CSSBuilder.&$)
        registerExplicitMethod("\$s", CSSBuilder.&$s)
        registerExplicitMethod("\$\$", CSSBuilder.&$$)
    }

    static JComponent $(Container target, String name) {
        SwingUtils.getComponentByName(target, name)
    }

    static JComponent[] $s(Container target, String... names) {
        List<JComponent> components = new ArrayList<JComponent>()

        for (int i = 0; i < names.length; i++) {
            JComponent c = SwingUtils.getComponentByName(target, names[i])
            if (c) components << c
        }

        components.toArray(new JComponent[components.size()])
    }

    static JComponent[] $$(Container target, String selector) {
        SwingUtils.parseSelector(selector, SwingUtils.getAllJComponents(target))
    }
}