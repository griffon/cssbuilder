/*
 * Copyright 2007-2013 The original author or authors.
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.join;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.minus;

/**
 * @author Andres Almiray
 */
public class CssClass {
    private static final String KEY_VALUE = "value";
    protected PropertyChangeSupport pcs;
    private List<String> classes = new ArrayList<String>();

    public CssClass() {
        this(new String[0]);
    }

    public CssClass(String... cssclasses) {
        pcs = new PropertyChangeSupport(this);
        Collections.addAll(classes, cssclasses);
    }

    public boolean contains(String cssclass) {
        return classes.contains(cssclass);
    }

    public String toggle(String cssclass) {
        String oldValue = join(classes, " ");
        if (contains(cssclass)) {
            classes = minus(classes, cssclass);
            firePropertyChange(KEY_VALUE, oldValue, join(classes, " "));
        } else {
            classes.add(cssclass);
            firePropertyChange(KEY_VALUE, oldValue, join(classes, " "));
        }
        return join(classes, " ");
    }

    public String addAll(String... cssclasses) {
        String oldValue = join(classes, " ");
        boolean added = false;

        for (String cssclass : cssclasses) {
            if (!contains(cssclass)) {
                added = true;
                classes.add(cssclass);
            }
        }

        if (added) {
            firePropertyChange(KEY_VALUE, oldValue, join(classes, " "));
        }

        return join(classes, " ");
    }

    public String removeAll(String... cssclasses) {
        String oldValue = join(classes, " ");
        boolean removed = false;

        for (String cssclass : cssclasses) {
            if (contains(cssclass)) {
                removed = true;
                classes.remove(cssclass);
            }
        }

        if (removed) {
            firePropertyChange(KEY_VALUE, oldValue, join(classes, " "));
        }

        return join(classes, " ");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return pcs.getPropertyChangeListeners();
    }

    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return pcs.getPropertyChangeListeners(propertyName);
    }

    protected void firePropertyChange(PropertyChangeEvent event) {
        pcs.firePropertyChange(event);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CssClass cssClass = (CssClass) o;

        if (!classes.equals(cssClass.classes)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return classes.hashCode();
    }

    public String toString() {
        return join(classes, " ");
    }
}
