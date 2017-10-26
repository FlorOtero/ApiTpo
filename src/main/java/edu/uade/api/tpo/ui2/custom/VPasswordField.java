package edu.uade.api.tpo.ui2.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VPasswordField extends JPasswordField implements FocusListener, KeyListener {

    private JComponent[] components;
    private final static Color RED = new Color(255, 91, 91);

    public VPasswordField(JComponent... components) {
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

    public VPasswordField() {
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
        VPasswordField textField = (VPasswordField) c;
        boolean enabled = textField.getText() != null && !textField.getText().isEmpty();
        for (Component component : components) {
            component.setEnabled(enabled);
            this.setBackground(enabled ? Color.WHITE : RED);
        }
    }
}
