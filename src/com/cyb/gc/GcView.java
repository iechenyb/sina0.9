package com.cyb.gc;

import java.awt.Dimension;

import gchisto.Main;
import gchisto.gui.MainFrame;
import gchisto.utils.errorchecking.ErrorReporting;

public class GcView {
	 public static void main(String[] args)
	  {
	    ErrorReporting.setShowWarnings(true);
	    MainFrame frame = new MainFrame();
	    frame.setSize(new Dimension(1024, 680));
	    frame.setVisible(true);
	    frame.loadGCTraces(args);
	  }
}
