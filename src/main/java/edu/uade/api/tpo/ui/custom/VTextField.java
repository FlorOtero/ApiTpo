package edu.uade.api.tpo.ui.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VTextField extends JTextField implements FocusListener, KeyListener, Validable {

    private JComponent[] components;
    private final static Color RED = new Color(255, 91, 91);

    public VTextField(JComponent... components) {
        super();
        this.components = components;
        for (Component c : components) {
            if (c == null) {
                throw new IllegalArgumentException("Listener component has not been initialized");
            }
        }
        this.addFocusListener(this);
        this.addKeyListener(this);
    }

    public VTextField() {
        super();
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        this.checkContent(e.getComponent());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.checkContent(e.getComponent());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.checkContent(e.getComponent());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.checkContent(e.getComponent());
    }

    private void checkContent(Component c) {
        boolean enabled = hasValidContent();
        if (enabled) {
            Container container = this.getParent();
            for (Component component : container.getComponents()) {
                if (component instanceof Validable) {
                    Validable v = (Validable) component;
                    if (!v.hasValidContent()) {
                        enabled = false;
                    }
                }
            }
        }
        for (Component component : components) {
            component.setEnabled(enabled);
            //this.setBackground(hasValidContent() ? Color.WHITE : RED);
        }
    }

    @Override
    public boolean hasValidContent() {
        return this.getText() != null && !this.getText().isEmpty();
    }
}
