/*
* $Id: ShapeExtractor.java,v 1.1 2007/03/13 15:00:55 pottsj Exp $
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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * ShapeExtractor
 *
 * @author Created by Jasper Potts (Jul 7, 2007)
 * @version 1.0
 */
public class ShapeExtractor {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton loadButton;
    private JList shapesList;
    private JScrollPane srcScrollPane;
    private JLabel fileLabel;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;

    public ShapeExtractor() {
        frame = new JFrame("SVG Shape Extractor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        shapesList.setFixedCellHeight(200);
        shapesList.setCellRenderer(new ShapeCellRenerer());
        final JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        srcScrollPane.setViewportView(textArea);
        shapesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                SvgPath selectedSvgPath = (SvgPath)shapesList.getSelectedValue();
                if (selectedSvgPath!=null){
                    widthSpinner.setValue(selectedSvgPath.getWidth());
                    heightSpinner.setValue(selectedSvgPath.getHeight());
                    textArea.setText(selectedSvgPath.getJava2DCode());
                }
            }
        });
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                SvgPath selectedSvgPath = (SvgPath)shapesList.getSelectedValue();
                if (selectedSvgPath!=null){
                    selectedSvgPath.setWidth(((Integer)widthSpinner.getValue()).intValue());
                    selectedSvgPath.setHeight(((Integer)heightSpinner.getValue()).intValue());
                    textArea.setText(selectedSvgPath.getJava2DCode());
                }
            }
        };
        widthSpinner.setModel(new SpinnerNumberModel(100,0,Integer.MAX_VALUE,10));
        widthSpinner.addChangeListener(changeListener);
        heightSpinner.setModel(new SpinnerNumberModel(100,0,Integer.MAX_VALUE,10));
        heightSpinner.addChangeListener(changeListener);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(frame, "Choose SVG File", FileDialog.LOAD);
                fileDialog.setFilenameFilter(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".svg");
                    }
                });
                fileDialog.setVisible(true);
                if (fileDialog.getFile() != null) {
                    final File svgFile = new File(new File(fileDialog.getDirectory()), fileDialog.getFile());
                    fileLabel.setText(svgFile.getName());
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    new Thread() {
                        public void run() {
                            final DefaultListModel model = new DefaultListModel();
                            // parse svg extracting paths
                            try {
                                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                                SAXParser parser = parserFactory.newSAXParser();
                                parser.parse(svgFile, new DefaultHandler() {
                                    public void startElement(String uri, String localName, String qName,
                                                             Attributes attributes)
                                            throws SAXException {
                                        if (qName.equalsIgnoreCase("path")) {
                                            try {
                                                model.addElement(new SvgPath(attributes.getValue("d")));
                                            } catch (IOException e1) {
                                                JOptionPane.showMessageDialog(mainPanel,
                                                        "Could not parse path \"" + attributes.getValue("d") +
                                                                "\" from svg bacause \"" + e1.getMessage() + "\"");
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(mainPanel,
                                        "Could not read SVG file \"" + svgFile.getName() +
                                                "\" bacause \"" + e1.getMessage() + "\"");
                                e1.printStackTrace();
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    shapesList.setModel(model);
                                    frame.setCursor(Cursor.getDefaultCursor());
                                }
                            });
                        }
                    }.start();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ShapeExtractor shapeExtractor = new ShapeExtractor();
                shapeExtractor.frame.pack();
                shapeExtractor.frame.setSize(800, 600);
                shapeExtractor.frame.setLocationRelativeTo(null);
                shapeExtractor.frame.setVisible(true);
            }
        });
    }

}
