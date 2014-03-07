/*
* $Id: ShapeCellRenerer.java,v 1.1 2007/03/13 15:00:55 pottsj Exp $
*
* Copyright 2007 Sun Microsystems, Inc., 4150 Network Circle,
* Santa Clara, California 95054, U.S.A. All rights reserved.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
package com.jasperpotts.svgshapeextractor;

import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * ShapeCellRenerer
 *
 * @author Created by Jasper Potts (Jul 7, 2007)
 * @version 1.0
 */
public class ShapeCellRenerer extends JComponent implements ListCellRenderer {
    private Shape shape = null;
    private boolean isSelected = false;

    /**
     * Return a component that has been configured to display the specified value. That component's <code>paint</code>
     * method is then called to "render" the cell.  If it is necessary to compute the dimensions of a list because the
     * list cells do not have a fixed size, this method is called to generate a component on which
     * <code>getPreferredSize</code> can be invoked.
     *
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     * @see javax.swing.JList
     * @see javax.swing.ListSelectionModel
     * @see javax.swing.ListModel
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        shape = ((SvgPath)value).getShape();
        this.isSelected = isSelected;
        return this;
    }


    /**
     * Calls the UI delegate's paint method, if the UI delegate is non-<code>null</code>.  We pass the delegate a copy
     * of the <code>Graphics</code> object to protect the rest of the paint code from irrevocable changes (for example,
     * <code>Graphics.translate</code>).
     * <p/>
     * If you override this in a subclass you should not make permanent changes to the passed in <code>Graphics</code>.
     * For example, you should not alter the clip <code>Rectangle</code> or modify the transform. If you need to do
     * these operations you may find it easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not invoker super's implementation you must honor the
     * opaque property, that is if this component is opaque, you must completely fill in the background in a non-opaque
     * color. If you do not honor the opaque property you will likely see visual artifacts.
     * <p/>
     * The passed in <code>Graphics</code> object might have a transform other than the identify transform installed on
     * it.  In this case, you might get unexpected results if you cumulatively apply another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     * @see javax.swing.plaf.ComponentUI
     */
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected){
            g.setColor(Color.RED);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.RED);
        }
        Rectangle2D bounds = shape.getBounds2D();
        AffineTransform oldTransform = g2.getTransform();

        double scale = Math.min(
                (getWidth()-10)/bounds.getWidth(),
                (getHeight()-10)/bounds.getHeight()
        );
        double newW = bounds.getWidth() * scale;
        double newH = bounds.getHeight() * scale;
        g2.setStroke(new BasicStroke((float)(1/scale)));
        g2.translate(5,5);
        g2.scale(scale,scale);
        g2.translate(-bounds.getX(),-bounds.getY());
        g2.draw(shape);
        
        g2.setTransform(oldTransform);
    }
}
