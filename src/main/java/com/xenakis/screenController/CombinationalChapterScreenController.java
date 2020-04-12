/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.databaseService.CategoryUtil;
import com.xenakis.service.TestService;
import com.xenakis.view.TestView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

public class CombinationalChapterScreenController extends ChapterController {
    
    public void start(MouseEvent e){

    	Ellipse el = (Ellipse)e.getSource();
    	String category = el.getId();

        new TestService("combinational",category,"Συσχετιζόμενες Έννοιες", CategoryUtil.getCategoryGreekName(category),"CombinationalChapterScreen");
    }
}
