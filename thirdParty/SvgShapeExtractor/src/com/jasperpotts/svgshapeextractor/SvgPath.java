/*
* $Id: SvgPath.java,v 1.1 2007/03/13 15:00:55 pottsj Exp $
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

import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
import org.apache.batik.parser.AWTPathProducer;

import java.io.StringReader;
import java.io.IOException;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.Shape;
import java.text.DecimalFormat;

/**
 * SvgPath
 *
 * @author Created by Jasper Potts (Jul 7, 2007)
 * @version 1.0
 */
public class SvgPath {
    private String pathString;
    private Shape shape;
    private int width = 100, height = 100;

    public SvgPath(String pathString) throws IOException {
        this.pathString = pathString;
        shape = AWTPathProducer.createShape(new StringReader(pathString), GeneralPath.WIND_EVEN_ODD);
        width = shape.getBounds().width;
        height = shape.getBounds().height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getJava2DCode(){
        StringBuilder buf = new StringBuilder("GeneralPath path = new GeneralPath();\n");
        AffineTransform transform = new AffineTransform();
//                transform.translate(-0.052312747,-0.052242965);
        transform.scale((double)width/shape.getBounds2D().getWidth(),(double)height/shape.getBounds2D().getHeight());
        transform.translate(-shape.getBounds2D().getX(),-shape.getBounds2D().getY());
        PathIterator i = shape.getPathIterator(transform);
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2);
        double[] pointsD = new double[6];
        while (!i.isDone()){
            int type = i.currentSegment(pointsD);
            String[] points = new String[6];
            for (int j = 0; j < points.length; j++) {
                points[j] = format.format(pointsD[j]);
            }
            switch(type){
                case PathIterator.SEG_MOVETO:
                    buf.append("path.moveTo("+points[0]+","+points[1]+");\n");
                    break;
                case PathIterator.SEG_LINETO:
                    buf.append("path.lineTo("+points[0]+","+points[1]+");\n");
                    break;
                case PathIterator.SEG_QUADTO:
                    buf.append("path.quadTo("+points[0]+","+points[1]+","+points[2]+","+points[3]+");\n");
                    break;
                case PathIterator.SEG_CUBICTO:
                    buf.append("path.curveTo("+points[0]+","+points[1]+","+points[2]+","+points[3]+","+points[4]+","+points[5]+");\n");
                    break;
                case PathIterator.SEG_CLOSE:
                    buf.append("close()\n");
                    break;
                default:
                    buf.append("ERROR\n");
                    break;
            }
            i.next();
        }
        return buf.toString();
    }

    public Shape getShape() {
        return shape;
    }

    public String toString() {
        return pathString;
    }
}
